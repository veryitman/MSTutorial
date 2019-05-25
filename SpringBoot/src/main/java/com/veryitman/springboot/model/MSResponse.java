package com.veryitman.springboot.model;

import lombok.Data;

@Data
public class MSResponse<T> {
    private int code;
    private String msg;
    private T results;
}
