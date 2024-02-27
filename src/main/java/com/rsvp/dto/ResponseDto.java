package com.rsvp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> implements Serializable {

    private String message;
    private transient T data;
}
