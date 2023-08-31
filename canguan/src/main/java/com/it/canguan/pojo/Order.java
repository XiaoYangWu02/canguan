package com.it.canguan.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName order
 */
@Data
public class Order implements Serializable {
    private Integer orderId;

    private Integer customerId;

    private Integer amount;

    private Integer locationId;

    private static final long serialVersionUID = 1L;
}