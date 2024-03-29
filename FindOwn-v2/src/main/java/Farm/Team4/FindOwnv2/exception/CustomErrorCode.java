package Farm.Team4.FindOwnv2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    //400
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, "NOT FOUND MEMBER"),
    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "DUPLICATED USER ID"),
    DUPLICATED_ISSUE(HttpStatus.BAD_REQUEST, "ALREADY SCRAPED ISSUE"),
    NOT_MATCH_WRITER(HttpStatus.BAD_REQUEST, "WRITER AND DELETER IS NOT SAME"),
    NO_IMAGE(HttpStatus.BAD_REQUEST, "INPUT IMAGE PLZ"),


    //401
    INVALID_INFORMATION(HttpStatus.UNAUTHORIZED, "INVALID ID OR PASSWORD"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "PASSWORD IS INCORRECT"),
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "LOGIN REQUIRED"),
    NOT_MATCH_CODE(HttpStatus.UNAUTHORIZED, "EMAIL CODE IS NOT COINCIDE"),


    //403
    ACCESS_DENIED_MEMBER(HttpStatus.FORBIDDEN, "ACCESS DENIED MEMBER"),


    //404
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "NOT FOUND POST"),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "NOT FOUND COMMENT"),
    NOT_FOUND_ISSUE(HttpStatus.NOT_FOUND, "NOT FOUND ISSUE"),
    NOT_FOUND_SCRAP(HttpStatus.NOT_FOUND, "NOT FOUND SCRAP"),
    NOT_FOUND_COMPARISON(HttpStatus.NOT_FOUND, "NOT FOUND COMPARISON"),


    //405



    //500
    CANT_GET_IMAGE(HttpStatus.INTERNAL_SERVER_ERROR, "다운로드 링크에서 이미지를 가져올 수 없습니다."),
    ;
    private final HttpStatus code;
    private final String errorMessage;

    CustomErrorCode(HttpStatus code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
