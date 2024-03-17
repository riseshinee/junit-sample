package com.example.test.controller;

import com.example.test.controller.userDto.UserGetResDTO;
import com.example.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean //가짜 객체를 생성해서 주입, 실제 객체가 아님
  UserService userService;

  @Test
  void getUserTest() throws Exception {
    given(userService.getUser(1)).willReturn(
    UserGetResDTO.builder()
            .id("1")
            .name("test")
            .createdAt(LocalDateTime.parse(""))
            .build()
    );

    int userId = 1;

    mockMvc.perform(
            get("/api/user/" + userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.createdAt").exists())
            .andDo(print());

    verify(userService.getUser(userId));

  }


}
