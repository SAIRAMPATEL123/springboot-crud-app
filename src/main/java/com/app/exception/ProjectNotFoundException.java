package com.app.exception;

public class ProjectNotFoundException extends Exception{
    public ProjectNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
