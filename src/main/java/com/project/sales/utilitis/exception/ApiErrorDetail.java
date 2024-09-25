package com.project.sales.utilitis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDetail {
    private String object;
    private String input;
    private Object rejectedValue;
    private String error;

    public ApiErrorDetail(String object, String input, Object rejectedValue, String error) {
        this.object = object;
        this.input = input;
        this.rejectedValue = rejectedValue;
        this.error = error;
    }
}
