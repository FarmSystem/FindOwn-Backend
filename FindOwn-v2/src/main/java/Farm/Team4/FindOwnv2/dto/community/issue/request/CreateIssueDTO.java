package Farm.Team4.FindOwnv2.dto.community.issue.request;

import Farm.Team4.FindOwnv2.domain.community.issue.Category;
import Farm.Team4.FindOwnv2.domain.community.issue.Issue;
import lombok.Data;

@Data
public class CreateIssueDTO {
    private String title;
    private String content;
    private String category;
    private String source;
    private String reporter;
    public Issue toIssue(){
        return new Issue(this.category, this.title, this.content, this.source, this.reporter);
    }
}
