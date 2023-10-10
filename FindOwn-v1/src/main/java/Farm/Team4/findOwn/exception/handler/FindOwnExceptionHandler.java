package Farm.Team4.findOwn.exception.handler;

import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.exception.response.ErrorResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FindOwnExceptionHandler {
    @ExceptionHandler(FindOwnException.class)
    protected ResponseEntity<ErrorResponseEntity> handleException(FindOwnException e){
        return ErrorResponseEntity.toResponseEntity(e.getCode());
    }
}
