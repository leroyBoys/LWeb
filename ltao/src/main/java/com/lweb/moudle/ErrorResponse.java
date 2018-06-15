package com.lweb.moudle;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/15.
 */
public class ErrorResponse {
    private String code = "code";
    private int codeType;

    public static ErrorResponse value(ErrorCode codeType){
        return new ErrorResponse(codeType);
    }

    public ErrorResponse(ErrorCode codeType) {
        this.codeType = codeType.getCode();
    }

    public String getCode() {
        return code;
    }
}
