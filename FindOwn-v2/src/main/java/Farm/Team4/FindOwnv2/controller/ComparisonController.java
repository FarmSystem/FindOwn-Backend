package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.comparison.response.ComparisonResultDto;
import Farm.Team4.FindOwnv2.dto.comparison.response.ComparisonTrademarkDto;
import Farm.Team4.FindOwnv2.service.comparison.ComparisonService;
import Farm.Team4.FindOwnv2.service.comparison.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v2/users/comparison")
@RequiredArgsConstructor
public class ComparisonController {
    private final ImageService imageService;
    private final ComparisonService comparisonService;
    @PostMapping
    public List<ComparisonTrademarkDto> startComparison(@RequestParam("image")MultipartFile file) throws IOException, URISyntaxException {
        log.info("file origin name = {}", file.getOriginalFilename());
        return comparisonService.confirmComparisonResult(imageService.uploadImage(file));
    }
}
