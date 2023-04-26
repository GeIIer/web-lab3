package com.ssau.study.exceptions;

public class StudentExistsException extends RuntimeException {
    public String attrName;
    public StudentExistsException(String message) {
        super("Студент уже существует: " + message);
        attrName = message;
    }
}
