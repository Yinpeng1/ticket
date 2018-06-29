package com.yp.ticket.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回类型类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 5366907122763659782L;

    private long code;
    private String message;
    private Object data;

    public ResultEntity() {
    }
}