package com.it.canguan.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName location
 */
@Data
public class Location implements Serializable {
    private Integer id;

    private String name;

    private String status;

    private static final long serialVersionUID = 1L;
}