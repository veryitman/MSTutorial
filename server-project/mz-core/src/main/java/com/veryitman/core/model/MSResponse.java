package com.veryitman.core.model;

import lombok.Data;

@Data
public class MSResponse<T> {
    private int code;
    private String msg;
    private T results;
}
