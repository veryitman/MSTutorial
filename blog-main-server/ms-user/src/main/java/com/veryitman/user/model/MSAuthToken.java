package com.veryitman.user.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MSAuthToken implements Serializable {
    private String authToken;
}
