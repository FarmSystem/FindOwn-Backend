package Farm.Team4.FindOwnv2.service.comparison;

import Farm.Team4.FindOwnv2.domain.platform.Comparison;
import Farm.Team4.FindOwnv2.domain.platform.Trademark;
import Farm.Team4.FindOwnv2.dto.comparison.response.client.ComparisonTrademarkDto;
import Farm.Team4.FindOwnv2.repository.TrademarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class TrademarkService {
    private final TrademarkRepository trademarkRepository;
    @Transactional
    public void saveTrademark(ComparisonTrademarkDto comparisonTrademarkDto, Comparison comparison){
        trademarkRepository.save(new Trademark(comparisonTrademarkDto, comparison));
        log.info("Trademark 저장 완료");
    }
}
