package ShoeKream.communityBoard.VO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class bulletinBoardVO {
	
	private int bbNo;
	private String bbTitle;
	private String bbCategory;
	private String bbContents;
	private String creatorId;
	private String creatorname;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
	private int bbhitCnt;
	private int likeCnt;
	private Boolean cbdeletedyn;
	

}
