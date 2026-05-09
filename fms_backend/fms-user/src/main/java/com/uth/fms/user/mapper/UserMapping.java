package com.uth.fms.user.mapper;

import com.uth.fms.common.mapper.MapperConfig;
import com.uth.fms.user.dto.reponse.LoginResponse;
import com.uth.fms.user.dto.request.RegisterRequest;
import com.uth.fms.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapping {
    @Mapping(target = "password", ignore = true) // Chặn vì phía service sẽ lưu mk mã hóa
    @Mapping(target = "isActive", constant = "true")
    User toEntity(RegisterRequest request);

    @Mapping(target = "accessToken", ignore = true )
    @Mapping(target = "refreshToken", ignore = true )
    @Mapping(target = "roles" , expression = "java(user.getRole().name())") // Chuyển đổi từ Role (enum) thành String
    LoginResponse toLoginResponse(User user);
}
