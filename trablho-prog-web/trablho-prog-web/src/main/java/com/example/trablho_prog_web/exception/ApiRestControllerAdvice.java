package com.example.trablho_prog_web.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiRestControllerAdvice extends ResponseEntityExceptionHandler {

    private final HttpRequestHandlerAdapter httpRequestHandlerAdapter;

    public ApiRestControllerAdvice(HttpRequestHandlerAdapter httpRequestHandlerAdapter) {
        this.httpRequestHandlerAdapter = httpRequestHandlerAdapter;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleDefaultException(Exception ex) {
        ProblemDetail erroInfo = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404),ex.getLocalizedMessage());
        erroInfo.setTitle("Erro padrao");
        return erroInfo;
    }

}