package com.example.test.dao;

import com.example.test.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
/*
JPA와 관련된 설정만 로드해서 테스트를 진행한다. 기본적으로 @Transactional 어노테이션 포함
테스트 코드가 종료되면 자동으로 rollback 진행
 */
@DataJpaTest
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  void saveTest() {
    //given
    User user = User.builder()
            .id("test")
            .name("name")
            .passsword("1234")
            .build();
    //when
    User saveUser = userRepository.save(user);

    //then 값이 일치하는지 확인하는 과정
    Assertions.assertEquals(user.getId(), saveUser.getId());
    Assertions.assertEquals(user.getName(), saveUser.getName());
  }

  @Test
  void getTest() {
    //given
    User user = User.builder()
            .no(9999)
            .id("test")
            .name("name")
            .passsword("1234")
            .build();
    User saveUser = userRepository.save(user);

    //when
    User getUser = userRepository.findFirstByNo(9999).get();

    //then
    Assertions.assertEquals(saveUser.getId(), getUser.getId());
    Assertions.assertEquals(saveUser.getName(), getUser.getName());
    Assertions.assertEquals(saveUser.getCreatedAt(), getUser.getCreatedAt());
  }
}
