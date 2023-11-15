package Farm.Team4.FindOwnv2.exception.response;

import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class ErrorResponseEntity {
    private HttpStatus status;
    private String code;
    private String message;
    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(CustomErrorCode e){
        return ResponseEntity
                .status(e.getCode())
                .body(ErrorResponseEntity.builder()
                        .status(e.getCode()) // http code -> ex) 500, 400, 200 ...
                        .code(e.name()) // 내가 설정한 에러의 이름 ex) NOT_FOUND_MEMBER
                        .message(e.getErrorMessage()) // 내가 설정한 에러의 설명 ex) "등록된 멤버가 없습니다"
                        .build()
                );
    }
}
