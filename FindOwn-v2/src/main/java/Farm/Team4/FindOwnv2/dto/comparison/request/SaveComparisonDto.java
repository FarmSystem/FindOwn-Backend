package Farm.Team4.FindOwnv2.dto.comparison.request;

import Farm.Team4.FindOwnv2.dto.comparison.response.client.ComparisonTrademarkDto;

import java.util.List;

public record SaveComparisonDto (
        String originImage,
        boolean open,
        List<ComparisonTrademarkDto> trademarks

){
}
