package com.example.kvjp.controller;

import com.example.kvjp.dto.request.LoginRequestDto;
import com.example.kvjp.dto.response.LoginResponseDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.security.impl.UserDetailServiceImpl;
import com.example.kvjp.security.impl.UserDetailsImpl;
import com.example.kvjp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController extends ResponseController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserDetailServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> authenticateUser (@Valid @RequestBody LoginRequestDto loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        LoginResponseDto loginResponseDto = new LoginResponseDto(
                jwt, userDetails.getUsername());



        return responseUtil.getSuccessResponse(loginResponseDto);
    }
}
