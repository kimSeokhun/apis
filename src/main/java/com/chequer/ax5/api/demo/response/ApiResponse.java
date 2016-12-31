package com.chequer.ax5.api.demo.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;


@Setter
@Getter
public class ApiResponse {
    private int status;

    private ApiError error = null;

    private String message;

    private String redirect = "";

    public ApiResponse() {

    }

    public ApiResponse(ApiStatus apiStatus, String message, String errorMessage) {
        this.status = apiStatus.getCode();
        this.message = message;

        if (!StringUtils.isEmpty(errorMessage)) {
            this.error = ApiError.of(errorMessage);
        }
    }

    public static ApiResponse of(ApiStatus apiStatus, String message) {
        return new ApiResponse(apiStatus, message, null);
    }

    public static ApiResponse error(ApiStatus apiStatus, String errorMessage) {
        return new ApiResponse(apiStatus, "", errorMessage);
    }

    public static ApiResponse redirect(String redirect) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setRedirect(redirect);
        apiResponse.setStatus(ApiStatus.REDIRECT.getCode());
        apiResponse.setMessage("redirect");
        return apiResponse;
    }
}
