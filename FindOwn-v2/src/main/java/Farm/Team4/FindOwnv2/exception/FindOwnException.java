package Farm.Team4.FindOwnv2.exception;

import lombok.Getter;

@Getter
public class FindOwnException extends RuntimeException{
    private final CustomErrorCode error;
    public FindOwnException(CustomErrorCode error){
        this.error = error;
    }

}
