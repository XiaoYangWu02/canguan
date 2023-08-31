package com.it.canguan.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_account
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String hobby;


    private static final long serialVersionUID = 1L;
}