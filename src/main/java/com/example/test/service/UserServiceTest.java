package com.example.test.service;

import com.example.test.controller.userDto.UserCraeteReqDTO;
import com.example.test.controller.userDto.UserCreateResDTO;
import com.example.test.controller.userDto.UserGetResDTO;
import com.example.test.dao.UserRepository;
import com.example.test.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
  //Mock 객체로 repository 주입받음
  private UserRepository userRepository = Mockito.mock(UserRepository.class);
  private UserService userService;

  @BeforeEach
  public void setUpTest() {
    userService = new UserService(userRepository);
  }

  @Test //Given-When-Then 패턴
  void getUserTest() {
    User givenUser = new User();
    givenUser.builder()
            .no(9999)
            .id("test")
            .name("name")
            .passsword("1234")
            .build();

    Mockito.when(userRepository.findFirstByNo(9999))
            .thenReturn(Optional.of(givenUser));

    UserGetResDTO userGetResDto = userService.getUser(9999);
    Assertions.assertEquals(userGetResDto.getId(), givenUser.getId());
    Assertions.assertEquals(userGetResDto.getName(), givenUser.getName());
    Assertions.assertEquals(userGetResDto.getCreatedAt(), givenUser.getCreatedAt());

    verify(userRepository).findFirstByNo(9999);
  }

  @Test
  void saveUserTest() {
    Mockito.when(userRepository.save(any(User.class)))
            .then(returnsFirstArg());

    UserCraeteReqDTO userDto = UserCraeteReqDTO.builder()
            .id("test")
            .name("name")
            .password("1234")
            .build();

    UserCreateResDTO userCreateResDto = userService.saveUser(userDto);

    Assertions.assertEquals(userCreateResDto.getId(), "test");
    Assertions.assertEquals(userCreateResDto.getName(), "name");
    verify(userRepository).save(any());
  }

}
