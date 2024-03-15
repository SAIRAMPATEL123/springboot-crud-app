package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> methodArgumentNotValidException(MethodArgumentNotValidException notValidException){
        Map<String,String> errorMap=new HashMap<>();
        notValidException.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),error.getDefaultMessage()));
        return errorMap;
    }
    @ExceptionHandler(IdProofNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> idProofNotFoundException(IdProofNotFoundException idProofNotFoundException){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Message",idProofNotFoundException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> personNotFoundException(PersonNotFoundException personNotFoundException){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Message",personNotFoundException.getMessage());
        return errorMap;
    }
    @ExceptionHandler(BaseExcepiton.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> personNotFoundException(BaseExcepiton baseExcepiton){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Message",baseExcepiton.getMessage());
        return errorMap;
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> projectNotFoundException(ProjectNotFoundException projectNotFoundException){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Message",projectNotFoundException.getMessage());
        return errorMap;
    }
}
