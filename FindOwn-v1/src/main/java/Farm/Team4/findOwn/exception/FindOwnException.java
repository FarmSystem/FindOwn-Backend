package Farm.Team4.findOwn.exception;

import lombok.Getter;

@Getter
public class FindOwnException extends RuntimeException{
    private final CustomErrorCode code;
    public FindOwnException (CustomErrorCode code){
        this.code = code;
    }
}
