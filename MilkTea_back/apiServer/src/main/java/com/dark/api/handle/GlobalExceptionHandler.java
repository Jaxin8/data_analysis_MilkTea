package com.dark.api.handle;




import com.dark.api.exception.BusException;
import com.dark.api.exception.ParamException;
import com.dark.api.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ParamException.class)
    public Result handleParamException(ParamException paramException){
        System.out.println(paramException);
        logger.error(paramException.getMessage());
        return Result.failure(paramException.getMessage());
    }

    @ExceptionHandler(BusException.class)
    public Result handleBusException(BusException busException){
        System.out.println(busException);
        logger.error(busException.getMessage());
        return Result.failure(busException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception){
        System.out.println(exception);
        logger.error(exception.getMessage());
        return Result.failure("你的网络好像掉了。。。。");
    }
}
