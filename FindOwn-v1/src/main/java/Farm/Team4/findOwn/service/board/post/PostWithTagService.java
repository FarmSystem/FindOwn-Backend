package Farm.Team4.findOwn.service.board.post;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.PostWithTagRepository;
import Farm.Team4.findOwn.service.board.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostWithTagService {
    private final PostWithTagRepository postWithTagRepository;
    private final TagService tagService;
    @Transactional
    public void saveAssociation(Post post, Tag tag){
        postWithTagRepository.save(new PostWithTag(tag, post));
    }
    @Transactional
    public void saveAssociationsInSave(Post newPost, List<String> tagNames){
        List<PostWithTag> associations = tagNames.stream()
                .map(tagName -> new PostWithTag(tagService.findByTagName(tagName), newPost))
                .toList();
        postWithTagRepository.saveAll(associations);
    }
    @Transactional
    public void saveAssociationInUpdate(Post post, List<Tag> tags){
        tags.forEach(tag -> saveAssociation(post, tag));
    }
    public List<PostWithTag> findAssociations(Post post){
        return postWithTagRepository.findPostWithTagsByPost(post);
    }
    public PostWithTag findByPostAndTag(Post post, Tag tag){
        return postWithTagRepository.findByPostAndTag(post, tag)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_TAG)); // 수정 해야함
    }
    private List<Tag> findNewAssociation(List<Tag> originTags, List<Tag> newTags){
        List<Tag> saveTags = new ArrayList<>();
        for (Tag newTag : newTags) {
            int check = 0;
            for (Tag originTag : originTags) {
                if (newTag.equals(originTag)) break;
                check++;
            }
            if (check == originTags.size()) {
                saveTags.add(newTag);
            }
        }
        return saveTags;
    }
    private List<Tag> findDeleteTags(List<Tag> originTags, List<Tag> newTags){
        List<Tag> deleteTags = new ArrayList<>();
        for(Tag originTag : originTags){
            int check = 0;
            for(Tag newTag : newTags){
                if (originTag.equals(newTag)) break;
                check++;
            }
            if (check == newTags.size()){
                deleteTags.add(originTag);
            }
        }
        return deleteTags;
    }
    public void editAssociationInUpdate(Post post, List<Tag> originTags, List<Tag> newTags){
        List<Tag> saveTags = findNewAssociation(originTags, newTags);
        List<Tag> deleteTags = findDeleteTags(originTags, newTags);
        deleteAssociation(post, deleteTags);
        log.info("해당 사항 없는 태그 & 게시글 연관관계 삭제 완료");
        saveAssociationInUpdate(post, saveTags);
        log.info("새로운 태그 & 게시글 연관관계 저장 완료");
    }
    @Transactional
    public void deleteAssociation(Post post, List<Tag> deleteTags){
        deleteTags.forEach(
                deleteTag -> {
                    PostWithTag findAssociation = findByPostAndTag(post, deleteTag);
                    postWithTagRepository.delete(findAssociation);
                }
        );
    }

}
