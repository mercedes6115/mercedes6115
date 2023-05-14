package com.example.demo.Controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody UserEntity userEntity){
        String success = " ";
        userService.signIn(userEntity);
        if(userService.signIn(userEntity) != null){
            success = "Success!!";
        }else{
            success = "Failure!!";
        }
        return success;
    }

    /*
    @PostMapping("/logIn")
    public void logIn(HttpServletRequest servletRequest)throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        builder.
        log.info("results"+ builder.toString());
       // return userService.logIn(email,password);
    }

     */

    @PostMapping("/logIn")
    //JSON으로 넘어온 내용은 Request Body에 담겨져 오므로 @RequestBody 어노테이션을 붙여서 받고
    //원하는 형태의 객체로 받으면 된다.
    public Long logIn(@RequestBody Map<String,String> logInForm){

        String email = logInForm.get("email");
        String password = logInForm.get("password");
        log.info("results"+ email+password);
        long confirmation = userService.logIn(email,password);
        log.info("==323123====" + confirmation);

        return confirmation;
    }
}
