package Farm.Team4.FindOwnv2.service.comparison;

import Farm.Team4.FindOwnv2.dto.comparison.response.ComparisonResultDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.ComparisonTrademarkDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.JudgementAIDto;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComparisonService {
    private final ImageService imageService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String COMPARISON_PATH = "http://localhost:8000/api/v2/users/imageprocess/?image=";
    public List<ComparisonTrademarkDto> confirmComparisonResult(String imageUrl) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        log.info("헤더 설정 완료");
//        String result
//                = restTemplate.exchange(COMPARISON_PATH + imageUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
//        log.info("인공지능 결과 수신 완료");
//        log.info(result);
//        ObjectMapper objectMapper = new ObjectMapper();
//        ComparisonResultDto comparisonResultDto = objectMapper.readValue(result, ComparisonResultDto.class);
        ComparisonResultDto comparisonResultDto
                = restTemplate.exchange(COMPARISON_PATH + imageUrl, HttpMethod.GET, new HttpEntity<>(headers), ComparisonResultDto.class).getBody();
        log.info("인공지능 response 수신 완료");
        return comparisonResultDto.trademark().stream()
                        .map(judgement -> {
                            try {
                                judgement.updateImagePath(imageService.uploadImageFromUrl(judgement.getImagePath()));
                            } catch (IOException e) {
                                throw new FindOwnException(CustomErrorCode.CANT_GET_IMAGE);
                            }
                            log.info("이미지 변환 성공");
                            return new ComparisonTrademarkDto(judgement);
                        })
                        .toList();
//        comparisonResultDto.trademark().forEach(judgement -> {
//            String imagePath = judgement.getImagePath();
//            try {
//                judgement.updateImagePath(imageService.uploadImageFromUrl(imagePath));
//            } catch (IOException e) {
//                throw new FindOwnException(CustomErrorCode.CANT_GET_IMAGE);
//            }
//        });
    }
}
