package Farm.Team4.findOwn.dto.trademark.parsing.header;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
    private String requestMsgID;
    private String responseTime;
    private String responseMsgID;
    private String successYN;
    private String resultCode;
    private String resultMsg;
}
