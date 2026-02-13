package com.tianxiawushuang.handler;

import cn.dev33.satoken.exception.*;
import com.tianxiawushuang.common.Result;
import io.lettuce.core.RedisException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.stream.Collectors;

import static com.tianxiawushuang.constants.CodeErrorConstants.PARAMETER_NOT_NULL;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public Result<String> handlerException(NotLoginException e) {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 返回给前端
        return Result.code(401 , e.getMessage());
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public Result<String> handlerException(NotPermissionException e) {
        e.printStackTrace();
        return Result.code(403 , "权限不足");
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public Result<String> handlerException(NotRoleException e) {
        e.printStackTrace();
        return Result.code(405,"当前用户没有此接口所需的角色，无法访问");
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public Result handlerException(NotSafeException e) {
        e.printStackTrace();
        return Result.error("二级认证校验失败");
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public Result<String> handlerException(DisableServiceException e) {
        e.printStackTrace();
        return Result.error("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
    }

    // 拦截：Http Basic 校验失败异常
    @ExceptionHandler(NotBasicAuthException.class)
    public Result<String> handlerException(NotBasicAuthException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    // 拦截：Redis异常
    @ExceptionHandler(RedisException.class)
    public Result<String> handlerRedisException(RedisException e) {
        e.printStackTrace();
        return Result.error("缓存加载异常，可能是网络问题，请稍后再试");
    }

    // 分页参数不能为空
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handlerException(MissingServletRequestParameterException e){
        e.printStackTrace();
        return Result.error(PARAMETER_NOT_NULL);
    }

    // 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidException(MethodArgumentNotValidException e) {
        // 只提取所有字段的 defaultMessage，用逗号连接
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return Result.error(msg);
    }

    @ExceptionHandler(BindException.class)
    public Result<String> handleValidException(BindException e) {
        // 只提取所有字段的 defaultMessage，用逗号连接
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return Result.error(msg);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<String> handleFileSizeExceed(MaxUploadSizeExceededException e) {
        return Result.error("文件大小超出限制，请上传10MB以内的文件");
    }

    // 拦截：其它所有异常
    @ExceptionHandler(Exception.class)
    public Result<String> handlerException(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

}
