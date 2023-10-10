package Farm.Team4.findOwn.service.trademark;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.member.right.trademark.request.UpdateMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.trademark.ConvertTrademark;
import Farm.Team4.findOwn.dto.trademark.parsing.Response;
import Farm.Team4.findOwn.dto.trademark.parsing.body.Item;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.trademark.TrademarkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrademarkService {
    private final TrademarkRepository trademarkRepository;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${TRADEMARK_SERVICE_KEY}")
    private String dataServiceKey;
    @Value("${SEARCH_TRADEMARK_URL}")
    private String searchTrademarkUrl;
    public List<Item> findTrademark(String searchString) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity(headers);

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // Xml response UTF-8 encoding

        Response response = restTemplate.exchange(
                searchTrademarkUrl + "?serviceKey=" + dataServiceKey + "&searchString=" + searchString,
                HttpMethod.GET,
                request,
                Response.class
        ).getBody();
        log.info("공공데이터포털 api 데이터 수신 완료");
        return response.getBody().getItems();
    }
    public List<Trademark> selectRegisteredTrademark(List<Item> apiResult){
        List<Trademark> trademarks = apiResult.stream()
                .filter(item -> item.getApplicationStatus().equals("등록"))
                .map(item -> new Trademark(
                        item.getApplicationNumber(),
                        item.getBigDrawing(),
                        item.getApplicantName(),
                        item.getApplicationNumber(),
                        item.getApplicationStatus(),
                        item.getClassificationCode()))
                .collect(Collectors.toList());
        log.info("등록 데이터만 가져오기 성공");
        return trademarks;
    }
    public Trademark findAndSelectOneByApplicant(String applicantName) throws JsonProcessingException {
        Trademark trademark = findTrademark(applicantName).stream()
                .filter(mark -> mark.getApplicantName().equals(applicantName))
                .findFirst()
                .map(mark -> new Trademark(
                        mark.getApplicationNumber(),
                        mark.getBigDrawing(),
                        mark.getApplicantName(),
                        mark.getRegistrationNumber(),
                        mark.getApplicationStatus(),
                        mark.getClassificationCode()))
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_REGISTERED));
        saveTrademark(trademark);
        return trademark;
    }
    public Trademark findAndSelectOneById(String trademarkName, Long trademarkId) throws JsonProcessingException {
        return findTrademark(trademarkName).stream()
                .filter(mark -> mark.getApplicationNumber().equals(trademarkId))
                .findFirst()
                .map(mark -> new Trademark(
                        mark.getApplicationNumber(),
                        mark.getBigDrawing(),
                        mark.getApplicantName(),
                        mark.getRegistrationNumber(),
                        mark.getApplicationStatus(),
                        mark.getClassificationCode()))
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_REGISTERED));
    }
    @Transactional
    public Long saveTrademark(Trademark trademark){
        log.info("tradeService 진입 성공");
        Trademark savedTrademark = trademarkRepository.save(trademark);
        return savedTrademark.getId();
    }
    public Trademark findById(Long trademarkId){
        return trademarkRepository.findById(trademarkId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_TRADEMARK));
    }
    @Transactional
    public Trademark updateTrademark(Trademark findTrademark, UpdateMemberOwnTrademarkRequest request){
        ConvertTrademark updateRequest = new ConvertTrademark(
                request.getTrademarkId(),
                request.getImage(),
                request.getApplicant(),
                request.getRegisterNum(),
                request.getState(),
                request.getClassification()
        );
        return findTrademark.updateTrademark(updateRequest);
    }
}
