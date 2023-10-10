package Farm.Team4.findOwn.service.member.information;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class MemberUtils {
    @Value("${RANDOM_LENGTH}")
    private int passwordLength;
    @Value("${RANDOM_BOUND_START}")
    private int start;
    @Value("${RANDOM_BOUND_END}")
    private int end;
    private Random random = new Random();
    //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
    //(특수문자는 정의된 특수문자만 사용 가능)
    // 숫자: 48 ~ 57
    // 소문자: 97 ~ 122
    // 대문자: 65 ~ 90
    public String createTempPassword(){
        String tempPassword = "";
        char[] specialSymbols = {'!', '@', '#', '$', '%', '^', '&', '*'}; // -> 정의된 특수문자
        for(int i=0; i<passwordLength; i++){
            int randomNum = random.nextInt(10);
            tempPassword += (char) (randomNum + separateNumber());
        }
        tempPassword += specialSymbols[random.nextInt(8)];
        return tempPassword;
    }
    private int separateNumber(){
        int separator = random.nextInt(2);
        if (separator == 0) return 48;
        else return 97;
    }
}
