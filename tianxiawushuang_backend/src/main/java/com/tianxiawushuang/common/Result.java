//package com.smalla.backend.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Result {
//    private Boolean success;
//    private String errorMsg;
//    private Object data;
//    private Long total;
//
//    public static Result ok(){
//        return new Result(true, null, null, null);
//    }
//    public static Result ok(Object data){
//        return new Result(true, null, data, null);
//    }
//    public static Result ok(List<?> data, Long total){
//        return new Result(true, null, data, total);
//    }
//    public static Result fail(String errorMsg){
//        return new Result(false, errorMsg, null, null);
//    }
//}

package com.tianxiawushuang.common;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public Result(T data) {
        this.code = 200;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result<>(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(String errorMsg) {
        return new Result<>(500, errorMsg, null);
    }

    public static <T> Result<T> code(Integer code, String errorMsg) {
        return new Result<>(code, errorMsg, null);
    }

}
