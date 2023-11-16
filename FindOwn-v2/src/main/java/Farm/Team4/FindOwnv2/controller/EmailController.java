package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.auth.request.VerifyEmailCodeDTO;
import Farm.Team4.FindOwnv2.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/no-auth")
public class EmailController {
    private final EmailService emailService;
    @GetMapping("/email/send")
    public String mailConfirm(@RequestParam String address) throws Exception{
        log.info("email: " + address);
        return emailService.sendMessage(address);
    }
    @PostMapping("/email/check")
    public String verifyCode(@RequestBody VerifyEmailCodeDTO request) {
        return emailService.verifyCode(request);
    }

}
