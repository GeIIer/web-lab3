package com.ssau.study.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public String attrName;
    public GroupNotFoundException(String message) {
        super("Группа с данным id не найдена: " + message);
        attrName = message;
    }
}
