package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping("/api/v2/comparison")
@RequiredArgsConstructor
public class ComparisonController {
    private final ImageService imageService;
    @PostMapping
    public String startComparison(@RequestParam("image")MultipartFile file){
        log.info("file origin name = {}", file.getOriginalFilename());
        return imageService.uploadImage(file);
    }
}
