package com.chequer.ax5.api.demo.controllers;

import com.chequer.ax5.api.demo.response.ApiException;
import com.chequer.ax5.api.demo.response.ApiResponse;
import com.chequer.ax5.api.demo.response.ApiStatus;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@ControllerAdvice
public class BaseController {

    protected static final String APPLICATION_JSON = "application/json; charset=UTF-8";

    protected static final String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public ApiResponse ok() {
        return ApiResponse.of(ApiStatus.SUCCESS, "SUCCESS");
    }

    public ApiResponse ok(String message) {
        return ApiResponse.of(ApiStatus.SUCCESS, message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse handleForbidden(Exception e) {
        return ApiResponse.error(ApiStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ApiResponse handleBadRequestException(Exception e) {
        errorLogging(e);
        return ApiResponse.error(ApiStatus.BAD_REQUEST, "BAD_REQUEST");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse handleRequestParameterException(MissingServletRequestParameterException e) {
        errorLogging(e);
        return ApiResponse.error(ApiStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ApiResponse handleApiException(ApiException apiException) {
        return ApiResponse.error(ApiStatus.getApiStatus(apiException.getCode()), apiException.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ApiResponse handleException(Throwable throwable) {
        errorLogging(throwable);

        ApiResponse apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, throwable.getMessage());
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);

        if (rootCause != null) {
            if (rootCause instanceof SQLException) {
                String message = String.format("데이터 처리중 에러가 발생하였습니다.\n시스템 관리자에게 문의하세요.\n\n에러내용 : %s", rootCause.getLocalizedMessage());
                apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, message);
            }
        }
        return apiResponse;
    }

    protected void errorLogging(Throwable throwable) {
        if (logger.isErrorEnabled()) {

            Throwable rootCause = ExceptionUtils.getRootCause(throwable);

            if (rootCause != null) {
                throwable = rootCause;
            }

            if (throwable.getMessage() != null) {
                logger.error(throwable.getMessage(), throwable);
            } else {
                logger.error("ERROR", throwable);
            }
        }
    }
}
