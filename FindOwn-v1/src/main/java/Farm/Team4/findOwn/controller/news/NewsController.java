package Farm.Team4.findOwn.controller.news;

import Farm.Team4.findOwn.dto.news.news.request.SaveNewsRequest;
import Farm.Team4.findOwn.dto.news.news.response.SaveNewsResponse;
import Farm.Team4.findOwn.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {
    private final NewsService newsService;
    @PostMapping("/news")
    public SaveNewsResponse saveNews(@RequestBody SaveNewsRequest request){
        return newsService.saveNews(request);
    }
}
