package com.ssau.study.exceptions;

import java.time.LocalDate;

public record ApiError(
        String path,
        String massage,
        int statusCode,
        LocalDate localDate) {

}
