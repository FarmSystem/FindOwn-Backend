package Farm.Team4.FindOwnv2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    //400
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, "NOT FOUND MEMBER"),
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "NOT FOUND POST"),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "NOT FOUND COMMENT"),
    NOT_MATCH_WRITER(HttpStatus.BAD_REQUEST, "WRITER AND DELETER IS NOT SAME"),


    //401
    INVALID_INFORMATION(HttpStatus.UNAUTHORIZED, "INVALID ID OR PASSWORD"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "PASSWORD IS INCORRECT"),
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "LOGIN REQUIRED"),
    NOT_MATCH_CODE(HttpStatus.UNAUTHORIZED, "EMAIL CODE IS NOT COINCIDE"),


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
