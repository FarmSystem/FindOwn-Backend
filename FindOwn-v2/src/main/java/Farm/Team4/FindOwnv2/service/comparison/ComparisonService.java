package Farm.Team4.FindOwnv2.service.comparison;

import Farm.Team4.FindOwnv2.domain.platform.Comparison;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.comparison.request.SaveComparisonDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.client.ComparisonResultDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.django.DjangoDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.client.ComparisonTrademarkDto;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.ComparisonRepository;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComparisonService {
    private final MemberService memberService;
    private final TrademarkService trademarkService;
    private final ImageService imageService;
    private final ComparisonRepository comparisonRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String COMPARISON_PATH = "http://localhost:8000/api/v2/users/imageprocess/?image=";
    public ComparisonResultDto confirmComparisonResult(String imageUrl) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        log.info("헤더 설정 완료");

        String result
                = restTemplate.exchange(COMPARISON_PATH + imageUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
        log.info("인공지능 response 수신 완료");
        ObjectMapper objectMapper = new ObjectMapper();
        DjangoDto djangoDto = objectMapper.readValue(result, DjangoDto.class);
        log.info("ComparisonResultDto로 매핑 성공");

        List<ComparisonTrademarkDto> comparisonTrademarkDtos = djangoDto.trademark().stream()
                .map(judgement -> {
                    try {
                        judgement.updateImagePath(imageService.uploadImageFromUrl(judgement.getImagePath()));
                    } catch (IOException e) {
                        throw new FindOwnException(CustomErrorCode.CANT_GET_IMAGE);
                    }
                    log.info("이미지 url 변환 성공");
                    return new ComparisonTrademarkDto(judgement);
                })
                .toList();
        return new ComparisonResultDto(imageUrl, comparisonTrademarkDtos);
    }
    public Comparison findById(Long comparisonId){
        return comparisonRepository.findById(comparisonId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_COMPARISON));
    }
    @Transactional
    public void saveComparison(SaveComparisonDto saveComparisonDto){
        Comparison comparison = comparisonRepository.save(Comparison.builder()
                .originImage(saveComparisonDto.originImage())
                .open(saveComparisonDto.open())
                .member(memberService.getMember())
                .build());
        log.info("Comparison 저장 완료");

        saveComparisonDto.trademarks().forEach(
                comparisonTrademarkDto -> trademarkService.saveTrademark(comparisonTrademarkDto, comparison)
        );
    }
    @Transactional
    public void deleteComparison(Long comparisonId) {
        comparisonRepository.delete(findById(comparisonId));
        log.info("comparison 삭제 완료");
    }
}
