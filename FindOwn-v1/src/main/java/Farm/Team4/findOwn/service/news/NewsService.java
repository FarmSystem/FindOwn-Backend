package Farm.Team4.findOwn.service.news;

import Farm.Team4.findOwn.domain.news.News;
import Farm.Team4.findOwn.dto.news.news.request.SaveNewsRequest;
import Farm.Team4.findOwn.dto.news.news.response.SaveNewsResponse;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.news.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    @Transactional
    public SaveNewsResponse saveNews(SaveNewsRequest request){
        News savedNews = newsRepository.save(new News(request.getTitle(), request.getCategory(), request.getContent(), request.getSource(), request.getReporter()));
        return new SaveNewsResponse(savedNews.getId(), savedNews);
    }
    public News findById(Long newsId){
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_NEWS));
    }
}
