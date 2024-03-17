package com.example.test.controller.userDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserCreateResDTO {

    String id;
    String name;
}
