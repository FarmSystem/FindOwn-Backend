package Farm.Team4.FindOwnv2.service.member;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.auth.response.ResultPasswordDTO;
import Farm.Team4.FindOwnv2.dto.member.request.SaveMemberDTO;
import Farm.Team4.FindOwnv2.dto.member.response.CheckIdDuplicatedDTO;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void saveMember(SaveMemberDTO saveMemberDTO){
        Member requestMember = saveMemberDTO.toMember();
        log.info(passwordEncoder.encode(saveMemberDTO.getPassword()));
        requestMember.changeEncoded(passwordEncoder.encode(saveMemberDTO.getPassword()));
        memberRepository.save(requestMember);
    }
    public Member findById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public Member findByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public CheckIdDuplicatedDTO idDuplicated(String username){
        return new CheckIdDuplicatedDTO(!memberRepository.existsByUsername(username));
    }
    public ResultPasswordDTO isMatchPassword(String rawPassword){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = findByUsername(username);
        return new ResultPasswordDTO(passwordEncoder.matches(rawPassword, member.getPassword()));
    }
}
