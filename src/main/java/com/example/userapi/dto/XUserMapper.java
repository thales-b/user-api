package com.example.userapi.dto;

import com.example.userapi.model.XUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface XUserMapper {
    XUser toEntity(XUserDTO xUserDTO);

    XUserDTO toDTO(XUser xUser);
}
