package com.it.canguan.dto;

import lombok.Data;

@Data
public class UpdateUserInfoDto {
    private Integer id;
    private String username, phone, email, hobby;
}
