package Farm.Team4.FindOwnv2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    //400
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, "NOT FOUND MEMBER"),


    //401
    INVALID_INFORMATION(HttpStatus.UNAUTHORIZED, "INVALID ID OR PASSWORD"),
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "LOGIN REQUIRED"),


    //403
    ACCESS_DENIED_MEMBER(HttpStatus.FORBIDDEN, "ACCESS DENIED MEMBER");

    //404


    //405
    ;
    private final HttpStatus code;
    private final String errorMessage;

    CustomErrorCode(HttpStatus code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
