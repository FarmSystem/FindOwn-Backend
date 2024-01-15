package Farm.Team4.FindOwnv2.service.member;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.auth.response.ResultPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.ChangeMemberIdDTO;
import Farm.Team4.FindOwnv2.dto.member.request.ChangeMemberPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.SaveMemberDTO;
import Farm.Team4.FindOwnv2.dto.member.response.CheckIdDuplicatedDTO;
import Farm.Team4.FindOwnv2.dto.member.response.UserInfoDto;
import Farm.Team4.FindOwnv2.dto.member.response.UserScrapInfoDto;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.MemberRepository;
import Farm.Team4.FindOwnv2.service.community.scrap.ScrapService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void saveMember(SaveMemberDTO saveMemberDTO){
        if (memberRepository.existsByUsername(saveMemberDTO.getId()))
            throw new FindOwnException(CustomErrorCode.DUPLICATED_ID);
        Member requestMember = saveMemberDTO.toMember();
        log.info(passwordEncoder.encode(saveMemberDTO.getPassword()));
        requestMember.changeEncoded(passwordEncoder.encode(saveMemberDTO.getPassword()));
        memberRepository.save(requestMember);
    }
    public UserInfoDto showMyInfo(){
        Member loginMember = getMember();
        return UserInfoDto.builder()
                .email(loginMember.getEmail())
                .passwordUpdateDate(loginMember.getPasswordUpdateDate())
                .build();
    }
    public List<UserScrapInfoDto> showMyScrap() {
        Member loginMember = getMember();
        return loginMember.getMyScraps().stream()
                .map(scrap -> UserScrapInfoDto.builder()
                        .issueId(scrap.getIssue().getId())
                        .issueTitle(scrap.getIssue().getTitle())
                        .category(scrap.getIssue().getCategory().toString())
                        .year(scrap.getIssue().getCreatedAt().getYear())
                        .month(scrap.getIssue().getCreatedAt().getMonthValue())
                        .day(scrap.getIssue().getCreatedAt().getDayOfMonth())
                        .scrapCnt(scrap.getIssue().getScrapCnt())
                        .viewCnt(scrap.getIssue().getViewCnt())
                        .build()
                ).toList();
    }
    public Member findByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public CheckIdDuplicatedDTO isDuplicatedId(String username){
        return new CheckIdDuplicatedDTO(!memberRepository.existsByUsername(username));
    }
    public ResultPasswordDTO isMatchPassword(String rawPassword){
        Member member = getMember();
        return new ResultPasswordDTO(passwordEncoder.matches(rawPassword, member.getPassword()));
    }
    @Transactional
    public void changeUsername(ChangeMemberIdDTO changeMemberIdDTO) {
        Member member = findByUsername(changeMemberIdDTO.getOriginMemberId());
        if (!member.getUsername().equals(changeMemberIdDTO.getOriginMemberId()))
            throw new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER);
        member.changeUsername(changeMemberIdDTO.getNewMemberId());
        log.info("회원 아이디 변경 완료");
    }
    @Transactional
    public void changePassword(ChangeMemberPasswordDTO changeMemberPasswordDTO) {
        Member member = getMember();
        if (!passwordEncoder.matches(changeMemberPasswordDTO.getOriginMemberPw(), member.getPassword()))
            throw new FindOwnException(CustomErrorCode.WRONG_PASSWORD);
        member.changeEncoded(passwordEncoder.encode(changeMemberPasswordDTO.getNewMemberPw()));
        log.info("비밀번호 변경 완료, 암호화 완료");
    }
    @Transactional
    public void deleteMember() {
        Member member = getMember();
        memberRepository.delete(member);
        log.info("회원 삭제 완료");
    }
    public Member getMember(){
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
