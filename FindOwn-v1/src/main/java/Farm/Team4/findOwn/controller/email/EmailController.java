package Farm.Team4.findOwn.controller.email;

import Farm.Team4.findOwn.dto.member.information.VerifyMemberRequest;
import Farm.Team4.findOwn.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;
    @GetMapping("/mail")
    public String mailConfirm(@RequestParam String email) throws Exception{
        log.info("email: " + email);
        return emailService.sendMessage(email);
    }
    @PostMapping("/mail")
    public String verifyCode(@RequestBody VerifyMemberRequest request) {
        return emailService.verifyCode(request);
    }

}
