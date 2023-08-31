package com.it.canguan.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName food
 */
@Data
public class Food implements Serializable {
    private Integer id;

    private String foodName;

    private Integer foodAmount;

    private String foodStatus;

    private static final long serialVersionUID = 1L;
}