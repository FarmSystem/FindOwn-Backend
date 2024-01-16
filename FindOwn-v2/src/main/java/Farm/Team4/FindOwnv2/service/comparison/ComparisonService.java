package Farm.Team4.FindOwnv2.service.comparison;

import Farm.Team4.FindOwnv2.dto.comparison.response.ComparisonResultDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.JudgementAIDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
@Slf4j
@Service
@RequiredArgsConstructor
public class ComparisonService {
    private final ImageService imageService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String COMPARISON_PATH = "localhost:8000/api/v2/users/imageprocess/?image=";
    public ComparisonResultDto confirmComparisonResult(String imageUrl) throws IOException  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        log.info("헤더 설정 완료");
        ComparisonResultDto result
                = restTemplate.exchange(COMPARISON_PATH + imageUrl, HttpMethod.GET, new HttpEntity<>(headers), ComparisonResultDto.class).getBody();
        log.info("인공지능 결과 수신 완료");
        result.result()
                .forEach(comparisonTrademarkDto -> {
                    try {
                        comparisonTrademarkDto.updatePath(imageService.uploadImageFromUrl(comparisonTrademarkDto.getImagePath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        log.info("download url -> s3 url");
        return result;
    }
}
