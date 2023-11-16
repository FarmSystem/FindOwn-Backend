package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.auth.request.VerifyPasswordDTO;
import Farm.Team4.FindOwnv2.dto.auth.response.ResultPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.SaveMemberDTO;
import Farm.Team4.FindOwnv2.dto.member.response.CheckIdDuplicatedDTO;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/no-auth/register")
    public void saveMember(@RequestBody SaveMemberDTO saveMemberDTO){
        memberService.saveMember(saveMemberDTO);
    }

    @GetMapping("/no-auth/isDuplicated")
    public CheckIdDuplicatedDTO idDuplicatedId(@RequestParam String id){
        return memberService.idDuplicated(id);
    }
    @PostMapping("/users/auth/password")
    public ResultPasswordDTO isMatchPassword(@RequestBody VerifyPasswordDTO passwordDTO){
        return memberService.isMatchPassword(passwordDTO.getRawPassword());
    }
}
