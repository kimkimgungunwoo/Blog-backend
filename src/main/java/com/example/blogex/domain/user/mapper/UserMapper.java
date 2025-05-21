package com.example.blogex.domain.user.mapper;

import com.example.blogex.domain.user.dto.UserCreateRequest;
import com.example.blogex.domain.user.dto.UserInfo;
import com.example.blogex.domain.user.dto.UserProfile;
import com.example.blogex.domain.user.dto.UserSimpleInfo;
import com.example.blogex.domain.user.entitiy.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest request);

    @Mapping(target = "profileImageUrl",source="profileImage")
    UserInfo toUserInfo(User user);

    @Mapping(target = "profileImageUrl",source="profileImage")
    UserSimpleInfo toUserSimpleInfo(User user);

    @Mapping(target = "profileImageUrl",source="profileImage")
    UserProfile toUserProfile(User user);


}
