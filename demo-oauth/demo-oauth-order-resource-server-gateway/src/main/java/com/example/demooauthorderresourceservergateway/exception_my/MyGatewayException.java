package com.example.demooauthorderresourceservergateway.exception_my;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/28
 * @Description:
 */
public class MyGatewayException extends Exception {
//    private int code;
//    private String message;

//    public MyGatewayException(int code, String message) {
//        super(message);
//        this.code = code;
//        this.message = message;
//    }

    public MyGatewayException(String message) {
        super(message);
    }

   /* public MyGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyGatewayException(Throwable cause) {
        super(cause);
    }*/
}
