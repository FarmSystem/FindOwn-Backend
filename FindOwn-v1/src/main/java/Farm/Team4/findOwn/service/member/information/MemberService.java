package Farm.Team4.findOwn.service.member.information;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.member.information.DeleteMemberRequest;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequest;
import Farm.Team4.findOwn.dto.member.login.MemberLoginRequest;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member saveMember(SaveMemberRequest tempMember){
        return memberRepository.save(tempMember.toMember(new Date()));
    }
    public Member findById(String id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public Member findByNickname(String nickname){
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_NICKNAME));
    }
    @Transactional
    public Member changeEmail(String oldEmail, String newEmail){
        Member member = findByEmail(oldEmail);
        return member.changeEmail(newEmail);
    }
    @Transactional
    public String changeTempPassword(String id, String tempPassword){
        return findById(id).changePassword(tempPassword).getPassword();
    }
    @Transactional
    public String changeNewPassword(String memberId, String originPassword, String newPassword){
        if (!findById(memberId).getPassword().equals(originPassword))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_PASSWORD);
        log.info("기존 비밀번호와 일치 여부 확인 완료");

        return findById(memberId).changePassword(newPassword).getPassword();
    }
    @Transactional
    public void deleteMember (DeleteMemberRequest request){
        Member findMember = findById(request.getId());
        log.info("회원 조회 성공");
        if (!findMember.getPassword().equals(request.getPassword()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_PASSWORD);
        log.info("비밀번호 일치 확인");
        memberRepository.delete(findMember);
        log.info("회원 삭제 완료");
    }
    public void makeSessionId(HttpServletRequest httpRequest, HttpServletResponse httpResponse, MemberLoginRequest loginRequest){
        // 회원 조회 로직

        Member findMember = memberRepository.findById(loginRequest.getMemberId())
                .filter(member -> member.getPassword().equals(loginRequest.getPassword()))
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_INFORMATION));
        log.info("회원 조회 성공");

        // 로그인 성공 처리
        HttpSession session = httpRequest.getSession(); //  세션 키 생성 (있으면 조회, 없으면 생성)
        log.info("세션 생성 성공");
        session.setAttribute("loginMember", findMember);
        log.info("세션 저장 성공");
        return ;
    }

    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        HttpSession session = httpRequest.getSession(false);
        if (session != null){
            log.info("세션 조회 성공");
            session.invalidate();
            log.info("세션 삭제 성공");
        } else log.info("세션 조회 실패");
        return ;
    }
}
