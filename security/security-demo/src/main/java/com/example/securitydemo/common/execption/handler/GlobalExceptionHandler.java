package com.example.securitydemo.common.execption.handler;

import com.example.securitydemo.common.execption.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e){
        // 打印堆栈信息
        e.printStackTrace();
        log.error(e.getLocalizedMessage());
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }
//
//    /**
//     * 处理 接口无权访问异常AccessDeniedException
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
//        // 打印堆栈信息
//        e.printStackTrace();
//        log.error(e.getLocalizedMessage());
//        ApiError apiError = new ApiError(FORBIDDEN.value(),e.getMessage());
//        return buildResponseEntity(apiError);
//    }
//
//    /**
//     * 处理自定义异常
//     * @param e
//     * @return
//     */
//	@ExceptionHandler(value = BadRequestException.class)
//	public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
//        // 打印堆栈信息
//        e.printStackTrace();
//        log.error(e.getLocalizedMessage());
//        ApiError apiError = new ApiError(e.getHttpStatus().value(),e.getMessage());
//        return buildResponseEntity(apiError);
//	}

    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnknownHostException.class)
    public ResponseEntity<ApiError> entityExistException(UnknownHostException e) {
        // 打印堆栈信息
        e.printStackTrace();
        log.error(e.getLocalizedMessage());
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

//    /**
//     * 处理 EntityNotFound
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = EntityNotFoundException.class)
//    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
//        // 打印堆栈信息
//        log.error(ThrowableUtil.getStackTrace(e));
//        ApiError apiError = new ApiError(NOT_FOUND.value(),e.getMessage());
//        return buildResponseEntity(apiError);
//    }

//    /**
//     * 处理所有接口数据验证异常
//     * @param e
//     * @returns
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
//        // 打印堆栈信息
//        log.error(ThrowableUtil.getStackTrace(e));
//        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
//        ApiError apiError = new ApiError(BAD_REQUEST.value(), str[1] + ":" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        return buildResponseEntity(apiError);
//    }

    /**
     * 统一返回
     * @param apiError
     * @return
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
