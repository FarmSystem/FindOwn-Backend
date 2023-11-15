package Farm.Team4.FindOwnv2.service.auth;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Getter
public class PrincipleContext extends User {
    private Member member;
    public PrincipleContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }
}
