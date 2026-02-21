package com.codingshuttle.distributed_lovable.account_service.mapper;

import com.codingshuttle.distributed_lovable.account_service.dto.auth.SignupRequest;
import com.codingshuttle.distributed_lovable.account_service.dto.auth.UserProfileResponse;
import com.codingshuttle.distributed_lovable.account_service.entity.User;
import com.codingshuttle.distributed_lovable.common_lib.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

    UserDto toUserDto(User user);

}
