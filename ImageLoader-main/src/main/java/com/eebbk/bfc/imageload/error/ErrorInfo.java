package com.eebbk.bfc.imageload.error;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * error info for image loader lib.
 */

public class ErrorInfo {
    private static final String MODULE = "0B";


    //httpDNS request error
    public static final String ERROR_HTTPDNS_CODE = MODULE + "1001";
    private static final String ERROR_HTTPDNS_MSG = "httpDNS request fail";

    private static final Map<String, String> errors = new HashMap<>();

    static {
        errors.put(ERROR_HTTPDNS_CODE, ERROR_HTTPDNS_MSG);
    }

    private ErrorInfo() {
    }

    public static String getErrorMsg(@NonNull String errorCode) {
        if (errors.containsKey(errorCode)) {
            return errors.get(errorCode);
        }
        return errorCode;
    }
}
