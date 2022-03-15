package ShoeKream.admin.VO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class luxBoardVO {
	
	private int luxbNo;
	private String luxbBrand; //브랜드
	private String luxbProduct;	//제품명
	private String luxbIntro; //소개
	private int luxbPrice;
	private String luxbCategory; //카테고리
	private String luxbClassCode; //분류코드
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
	private String luxbThumbnailImg;
	private String luxbImg1;
	private String luxbImg2;
	private String deleteyn;
	private String pNo;
	private String onSale;

}
