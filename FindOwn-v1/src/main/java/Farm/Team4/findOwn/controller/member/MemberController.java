package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.news.News;
import Farm.Team4.findOwn.dto.board.post.response.SimplePostDTO;
import Farm.Team4.findOwn.dto.member.MemberDTO;
import Farm.Team4.findOwn.dto.member.information.ChangeEmailRequest;
import Farm.Team4.findOwn.dto.member.information.ChangePasswordRequest;
import Farm.Team4.findOwn.dto.member.information.DeleteMemberRequest;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequest;
import Farm.Team4.findOwn.dto.member.login.MemberLoginRequest;
import Farm.Team4.findOwn.dto.news.news.NewsDTO;
import Farm.Team4.findOwn.service.member.information.MemberUtils;
import Farm.Team4.findOwn.service.member.information.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final MemberUtils memberUtils;

    /**
     * 회원 CRUD
     */
    @PostMapping("/member")
    public MemberDTO saveMember(@Valid @RequestBody SaveMemberRequest request){
        Member saveMember = memberService.saveMember(request);
        return new MemberDTO(saveMember.getId(), saveMember.getEmail(), saveMember.getName(), saveMember.getMembershipDate());
    }
    @GetMapping("/member/find/id")
    public String findMyId(@RequestParam String email){
        return memberService.findByEmail(email).getId();
    }
    @GetMapping("/member/find/password")
    public String findMyPassword(@RequestParam String id){
        return memberService.changeTempPassword(id, memberUtils.createTempPassword());
    }
    @PatchMapping("/member/change/password")
    public String changeMyPassword(@RequestBody ChangePasswordRequest request){
        return memberService.changeNewPassword(request.getId(), request.getOldPassword(), request.getNewPassword()); // 비밀번호 변경 지점
    }
    @PatchMapping("/member/change/email")
    public MemberDTO changeMyEmail(@RequestBody ChangeEmailRequest request){
        Member member = memberService.changeEmail(request.getOldEmail(), request.getNewEmail());
        return new MemberDTO(member.getId(), member.getEmail(), member.getName(), member.getMembershipDate());
    }
    @DeleteMapping("/member/delete")
    public String deleteMember(@RequestBody DeleteMemberRequest request){
        memberService.deleteMember(request);
        return "delete complete";
    }
    /**
     *  회원 로그인, 로그아웃
     */
    @PostMapping("/login")
    public MemberDTO login(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                           @RequestBody MemberLoginRequest loginRequest){
        memberService.makeSessionId(httpRequest, httpResponse, loginRequest); // 회원에 대한 session id 생성 및 저장
        HttpSession session = httpRequest.getSession(false);
        if (session == null) return null;

        Member loginMember = (Member) session.getAttribute("loginMember");
        return new MemberDTO(loginMember.getId(), loginMember.getEmail(), loginMember.getName(), loginMember.getMembershipDate());
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        memberService.logout(httpRequest, httpResponse);
    }
    /**
     *  게시글, 스크랩
     */
    @GetMapping("/members/{memberId}/posts")
    public List<SimplePostDTO> myPosts(@PathVariable String memberId){
        return memberService.findById(memberId).getMyPosts().stream()
                .map(post -> new SimplePostDTO(post.getId(), post.getMember().getNickname(), post.getTitle(),
                        post.getPostWithTags().stream()
                                .map(postWithTag -> postWithTag.getTag().getName()).toList(), post.getCreatedAt()))
                .toList();
    }
    @GetMapping("/members/{memberId}/scraps")
    public List<NewsDTO> myNewsScrap(@PathVariable String memberId){
        return memberService.findById(memberId).getScraps().stream()
                .map(scrap -> new NewsDTO(scrap.getNews().getId(), scrap.getNews().getTitle(), String.valueOf(scrap.getNews().getCategory()), scrap.getNews().getReporter()))
                .toList();
    }

}
