package com.it.canguan.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName food_order
 */
@Data
public class FoodOrder implements Serializable {
    private Integer id;

    private Integer foodId;

    private String foodStatus;

    private Integer orderId;

    private static final long serialVersionUID = 1L;
}