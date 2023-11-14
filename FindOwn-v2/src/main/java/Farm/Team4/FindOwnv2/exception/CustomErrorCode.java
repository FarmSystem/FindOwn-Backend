package Farm.Team4.FindOwnv2.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {
    //400



    //401


    //403


    //404


    //405
    ;
    private final int code;
    private final String errorMessage;

    CustomErrorCode(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
