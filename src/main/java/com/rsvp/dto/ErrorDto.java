package com.rsvp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private String object;
    private String message;
    private Object value;

    public ErrorDto(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
