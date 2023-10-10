package Farm.Team4.findOwn.service.email;

import Farm.Team4.findOwn.dto.member.information.VerifyMemberRequest;
import Farm.Team4.findOwn.service.redis.RedisService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final RedisService redisService;
    private String code = createCode();
    @Value("${MAIL_USERNAME}")
    private String id;

    public String verifyCode(VerifyMemberRequest request){
        String memberEmail = redisService.getCode(request.getCode());
        log.info("이메일 인증 코드를 통한 이메일 가져오기 성공");
        if (memberEmail == null || !memberEmail.equals(request.getEmail())){
            log.info("이메일 인증 불일치 !!");
            redisService.deleteCode(request.getCode());
            log.info("이메일 인증 코드 삭제");
            return "email verification fail";
        }
        log.info("이메일 인증 성공");
        redisService.deleteCode(request.getCode());
        log.info("이메일 인증 코드 삭제");
        return "email verification success";
    }
    public String sendMessage(String dest) throws Exception{
        redisService.deleteCode(code); //  기존에 발급 했던 코드 삭제 (널 가능)
        code = createCode();
        try{
            redisService.setCodeExpire(code, dest, 180 * 1L);
            javaMailSender.send(createMessage(dest));
        }catch (MailException em){
            em.printStackTrace();
            throw new IllegalArgumentException("메일 전송 실패");
        }
        return code;
    }
    public MimeMessage createMessage(String dest) throws MessagingException, UnsupportedEncodingException{
        log.info("받을 사람: " + dest);
        log.info("인증코드: " + code);
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, dest);

        message.setSubject(dest + "님의 FindOwn 회원 가입 인증 코드입니다."); // 회원 가입 메일 제목
        // 메일 내용 기입
        String content = "";
        content += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        content += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        content += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        content += code;
        content += "</td></tr></tbody></table></div>";
        // 메일 내용 기입 완료

        message.setText(content, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"FindOwn")); //보내는 사람의 메일 주소, 보내는 사람 이름

        log.info("message 생성 성공");

        return message;
    }
    private String createCode(){
        String code = "";
        Random generateNum = new Random();
        for(int i=0; i<6; i++){
            code += (char)(generateNum.nextInt(10)+48);
        }
        log.info("인증 코드 생성 성공 code = " + code);
        return code;
    }
}
