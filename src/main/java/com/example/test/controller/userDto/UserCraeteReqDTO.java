package com.example.test.controller.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserCraeteReqDTO {
    String id;
    String name;
    String password;
}
