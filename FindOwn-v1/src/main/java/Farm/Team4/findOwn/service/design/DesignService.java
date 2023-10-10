package Farm.Team4.findOwn.service.design;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.dto.design.ConvertDesign;
import Farm.Team4.findOwn.dto.design.parsing.Response;
import Farm.Team4.findOwn.dto.design.parsing.body.Item;
import Farm.Team4.findOwn.dto.member.right.design.request.UpdateMemberDesignRequest;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.design.DesignRepository;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${DESIGN_SERVICE_KEY}")
    private String dataServiceKey;
    @Value("${SEARCH_DESIGN_URL}")
    private String searchDesignUrl;
    public List<Item> findDesign(String articleName) throws IOException {
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // Xml response

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        log.info("Request Message 생성완료");

        Response response = restTemplate.exchange(
                searchDesignUrl + "?serviceKey=" + dataServiceKey + "&articleName=" + articleName,
                HttpMethod.GET,
                request,
                Response.class
        ).getBody();
        log.info("공공데이터포털 api 정보 수신 완료");
        return response.getBody().getItems();
    }
    public List<Design> selectRegisteredTrademark(List<Item> apiResult){
        log.info("dto -> domain 변환 진입");
        return apiResult.stream()
                .filter(item -> item.getApplicationStatus().equals("등록"))
                .map(item -> new Design(
                        item.getApplicationNumber(),
                        item.getImagePathLarge(),
                        item.getApplicantName(),
                        item.getDesignNumber(),
                        item.getRegistrationNumber(),
                        item.getApplicationStatus(),
                        item.getDesignMainClassification()
                ))
                .collect(Collectors.toList());
    }
    public Design findAndSelectOne(String articleName) throws IOException {
        Design design = findDesign(articleName).stream()
                .filter(item -> item.getApplicationStatus().equals("등록"))
                .findFirst()
                .map(item -> new Design(
                        item.getApplicationNumber(),
                        item.getImagePathLarge(),
                        item.getApplicantName(),
                        item.getDesignNumber(),
                        item.getRegistrationNumber(),
                        item.getApplicationStatus(),
                        item.getDesignMainClassification()
                ))
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_REGISTERED));
        log.info("정확한 한 개의 디자인으로 변환 성공");
        saveDesign(design);
        log.info("해당 디자인 저장 성공");
        return design;
    }
    @Transactional
    public Long saveDesign(Design design){
        Design saveDesign = designRepository.save(design);
        return saveDesign.getId();
    }
    public Design findById(Long designId){
        return designRepository.findById(designId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_DESIGN));
    }
    @Transactional
    public Design updateDesign(Design findDesign, UpdateMemberDesignRequest request){
        ConvertDesign convertDesign = new ConvertDesign(
                request.getDesignId(),
                request.getImage(),
                request.getApplicant(),
                request.getDesignClass(),
                request.getRegisterNum(),
                request.getState(),
                request.getClassification()
        );
        return findDesign.update(convertDesign);
    }
}
