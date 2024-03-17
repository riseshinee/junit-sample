package com.example.test.controller;

import com.example.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.test.controller.userDto.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping()
    public ResponseWrapper<UserCreateResDTO> saveUser(@RequestBody UserCraeteReqDTO user) {
        ResponseWrapper<UserCreateResDTO> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setData(userService.saveUser(user));
        return responseWrapper;
    }

    @PutMapping("/{no}")
    public ResponseWrapper<Integer> putUser(@PathVariable("no") Integer no, @RequestBody UserUpdateReqDTO user) {
        ResponseWrapper<Integer> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setData(userService.putUser(no, user));
        return responseWrapper;
    }

    @GetMapping("/{no}")
    public ResponseWrapper<UserGetResDTO> getUser(@PathVariable("no") int no) {
        ResponseWrapper<UserGetResDTO> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setData(userService.getUser(no));
        return responseWrapper;
    }
}
