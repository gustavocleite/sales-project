package com.project.sales.utilitis.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorMessage {

    private HttpStatus status;
    private Date timestamp;
    private String message;
    private List<ApiErrorDetail> errors;

    public ApiErrorMessage(HttpStatus status, Date timestamp, String message, List<ApiErrorDetail> errors) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorMessage(HttpStatus status, Date timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }
}
