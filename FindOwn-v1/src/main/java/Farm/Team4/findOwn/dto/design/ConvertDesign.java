package Farm.Team4.findOwn.dto.design;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvertDesign {
    private Long designId; // 출원번호, '-'을 제외한 출원번호 전체
    private String image; // 디자인권 이미지, BLOB으로 지정하긴 했으나 URL이나 파일 경로로 이미지를 불러올 수 있다면 VARCHAR로 수정
    private String applicant; // 출원인, 특수문자를 포함 최소 8자 이상
    private String designClass; //  한국분류(디자인 분류에는 국제분류도 있고 한국 분류도 있다), 국제분류를 따르는 경우 한국분류 값이 없는 디자인권들이 존재
    private Long registerNum; // 등록 번호, '-'을 제외한 출원번호 전체
    private String state; // 법적 상태
    private String classification; // 디자인 구분(시각 디자인, 편집 디자인 등등), 하나의 디자인은 여러 구분을 가질 수 있음
}
