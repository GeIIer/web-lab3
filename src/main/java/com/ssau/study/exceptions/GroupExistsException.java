package com.ssau.study.exceptions;

public class GroupExistsException extends RuntimeException {
    public String attrName;
    public GroupExistsException(String message) {
        super("Группа уже существует: " + message);
        attrName = message;
    }
}
