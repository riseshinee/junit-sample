package com.example.test.service;

import com.example.test.common.exception.CustomException;
import com.example.test.common.utils.CommonUtil;
import com.example.test.controller.userDto.*;
import com.example.test.dao.UserRepository;
import com.example.test.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  /**
   * 회원 정보 생성 (회원가입)
   * @param createUserDto
   * @return
   * @throws Exception
   */
  public UserCreateResDTO saveUser(UserCraeteReqDTO createUserDto) {
    // 아이디 중복체크
    checkSameIdExists(createUserDto.getId());

    //유저 객체 생성, 저장
    User user = createPendingUser(createUserDto);
    userRepository.save(user);

    return UserCreateResDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
  }

  /**
   * 회원 정보 업데이트
   * @param no
   * @param updateUserDto
   * @return
   * @throws Exception
   */
  public int putUser(int no, UserUpdateReqDTO updateUserDto) {
    //유저 조회
    userRepository.findFirstByNo(no).orElseThrow(() ->
            new CustomException("회원 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST));

    //유저 정보 업데이트
    userRepository.updateByNo(no,
            updateUserDto.getName(),
            CommonUtil.dbEncrypt(updateUserDto.getPassword()));

    return no;
  }

  /**
   * 회원 정보 조회
   * @param no
   * @return
   * @throws Exception
   */
  public UserGetResDTO getUser(int no) {
    User user = userRepository.findFirstByNo(no).orElseThrow(() ->
            new CustomException("회원 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST));

    return UserGetResDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .createdAt(user.getCreatedAt())
            .build();

  }


  /**
   * 아이디 중복 체크
   * @param id
   * @return
   */
  private void checkSameIdExists(String id) {
    userRepository.findFirstById(id).ifPresent(user -> {
      throw new CustomException("중복된 아이디가 존재합니다.", HttpStatus.BAD_REQUEST);
    });
  }

  /**
   * user 객체 생성
   * @param createUserDto
   * @return
   */
  protected User createPendingUser(UserCraeteReqDTO createUserDto) {
    return User.builder()
            .id(createUserDto.getId())
            .name(createUserDto.getName())
            .passsword(CommonUtil.dbEncrypt(createUserDto.getPassword()))
            .build();
  }

}

