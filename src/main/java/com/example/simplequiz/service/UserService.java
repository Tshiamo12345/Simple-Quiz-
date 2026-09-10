package com.example.simplequiz.service;


import com.example.simplequiz.dto.LoginRequest;
import com.example.simplequiz.dto.SignUpRequest;
import com.example.simplequiz.exception.AlreadyFoundException;
import com.example.simplequiz.exception.NotFoundException;
import com.example.simplequiz.exception.ServerException;
import com.example.simplequiz.model.PendingUser;
import com.example.simplequiz.model.User;
import com.example.simplequiz.model.UserPrincipal;
import com.example.simplequiz.repository.PendingUserRepository;
import com.example.simplequiz.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService implements UserDetailsService {

    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PendingUserRepository pendingUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final String subject = "Quiz System";

    public UserService(JavaMailSender mailSender, PasswordEncoder passwordEncoder,PendingUserRepository pendingUserRepository,JWTService jwtService, UserRepo userRepo, @Lazy AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.pendingUserRepository = pendingUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }


    public String login(LoginRequest loginRequest) throws Exception {
        logger.info("Checking the loginRequest username {}:",loginRequest.getUsername());
        logger.info("Checking the loginRequest password {}",loginRequest.getPassword());
        User user = userRepo.findByUsername(loginRequest.getUsername()).orElse(null);

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            logger.info("User {}: ",user.toString());
            // Reset attempts on success

            return jwtService.generateToken(loginRequest.getUsername());

        } catch (AuthenticationException e) {
            // Increment failures

            throw new BadCredentialsException("invalid Credentials ");

        }
    }
    //this function or method does the singUp business logic
    @Transactional
    public void signup(SignUpRequest signUpRequest) throws ServerException,AlreadyFoundException {

        try {

            //request optional user
            Optional<User> optionalUser = userRepo.findByUsername(signUpRequest.getUsername());

            //checks if user exist in the database if yes throws the exception
            if (!optionalUser.isEmpty()) {
                throw new AlreadyFoundException("User already exist with email:" + signUpRequest.getEmail());
            }

            // Check if already pending
            Optional<PendingUser> existingPending = pendingUserRepository.findByUsername(signUpRequest.getUsername());
            if (existingPending.isPresent()) {
                // Optionally delete old pending record, or reuse it with new OTP
                pendingUserRepository.delete(existingPending.get());
            }

            //declaration
            PendingUser pendingUser = new PendingUser();
            //Random number of four numbers
            int codeInt = ThreadLocalRandom.current().nextInt(1000, 10000);

            String code = String.valueOf(codeInt);
            String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

            //setting all necessary fields to store the pending users
            pendingUser.setVerificationCode(code);
            pendingUser.setUsername(signUpRequest.getUsername());
            pendingUser.setEmail(signUpRequest.getEmail());
            pendingUser.setHashPassword(hashedPassword);
            pendingUser.setExpiryTimestamp(LocalDateTime.now().plusMinutes(5)); // 10 min

            // send Otp for verification
            sendEmail(signUpRequest.getEmail(), subject,"Please kindly find your max life "+code);
            //saving the pending user
            pendingUserRepository.save(pendingUser);
        } catch (Exception serverException) {
            logger.error("Something went wrong with the server ");
            throw new ServerException("Something went wrong with the server ");

        }

    }

    @Transactional
    public void Verify(String email,String verify)throws NotFoundException, ServerException, InputMismatchException {

        logger.info("user info {} ",email,verify);

        try{
            Optional<PendingUser> optionalPendingUser = pendingUserRepository.findByEmail(email);

            if(optionalPendingUser.isEmpty()){

                logger.error("Pending user not found ");
                throw new NotFoundException("User Expired");

            }

            PendingUser pendingUser = optionalPendingUser.get();
            if(!pendingUser.getVerificationCode().equalsIgnoreCase(verify)){
                logger.info("pending user in database code{} ",pendingUser.getVerificationCode());
                logger.info("pending user code entered for verification {}",verify);
                logger.error("The opt is not equal the one in the database");
                throw new InputMismatchException("Invalid Opt");
            }

            User user = new User();
            user.setEmail(pendingUser.getEmail());
            user.setCreatedAt(LocalDateTime.now());

            user.setUsername(pendingUser.getUsername());
            user.setPassword(pendingUser.getHashPassword());
            user.setRole("user");
            userRepo.save(user);


        }catch(Exception exception){
            logger.error("Something went wrong with the server ",exception);
            throw new ServerException("Something went wrong with the server");
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User Not found");
        }

        return new UserPrincipal(userOptional.get());
    }
    private void sendEmail(String emailToSendTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailToSendTo);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }

}
