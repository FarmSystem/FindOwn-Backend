package Farm.Team4.findOwn.service.board;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    @Transactional
    public Tag saveTag(String tagName){
        return tagRepository.save(new Tag(tagName));
    }
    @Transactional
    public void saveNewTag(List<String> tagNames){
        tagNames.stream()
                .filter(tagName -> !tagRepository.existsByName(tagName))
                .forEach(tagName -> tagRepository.save(new Tag(tagName)));
    }
    public Tag findByTagName(String tagName){
        return tagRepository.findByName(tagName)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_TAG));
    }
    public boolean existByTagName(String tagName){
        return tagRepository.existsByName(tagName);
    }
    public void checkTagInUpdate(List<String> tags){
    }
    @Transactional
    public void deleteTag(Tag tag){
        tagRepository.delete(tag);
    }
}
