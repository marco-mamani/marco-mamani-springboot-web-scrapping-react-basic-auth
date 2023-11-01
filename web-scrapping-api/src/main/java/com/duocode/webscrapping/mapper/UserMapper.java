package com.duocode.webscrapping.mapper;

import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
}