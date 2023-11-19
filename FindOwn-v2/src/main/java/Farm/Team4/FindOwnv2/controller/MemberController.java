package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.auth.request.VerifyPasswordDTO;
import Farm.Team4.FindOwnv2.dto.auth.response.ResultPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.ChangeMemberIdDTO;
import Farm.Team4.FindOwnv2.dto.member.request.ChangeMemberPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.SaveMemberDTO;
import Farm.Team4.FindOwnv2.dto.member.response.CheckIdDuplicatedDTO;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        return memberService.isDuplicatedId(id);
    }
    @PostMapping("/users/auth/password")
    public ResultPasswordDTO isMatchPassword(@RequestBody VerifyPasswordDTO passwordDTO){
        return memberService.isMatchPassword(passwordDTO.getRawPassword());
    }
    @PatchMapping("/users/my-page/change/id")
    public void changeMemberId(@RequestBody ChangeMemberIdDTO changeMemberIdDTO, HttpServletResponse response) throws IOException {
        memberService.changeUsername(changeMemberIdDTO);
        response.sendRedirect("http://localhost:8080/api/v2/users/logout");
    }
    @PatchMapping("/users/my-page/change/pw")
    public void changeMemberPassword(@RequestBody ChangeMemberPasswordDTO changeMemberPasswordDTO, HttpServletResponse response) throws IOException {
        memberService.changePassword(changeMemberPasswordDTO);
        response.sendRedirect("http://localhost:8080/api/v2/users/logout");
    }
    @DeleteMapping("/users/my-page/delete")
    public void deleteMember(HttpServletResponse response) throws IOException {
        memberService.deleteMember();
        response.sendRedirect("http://localhost:8080/api/v2/users/logout");
    }
}
