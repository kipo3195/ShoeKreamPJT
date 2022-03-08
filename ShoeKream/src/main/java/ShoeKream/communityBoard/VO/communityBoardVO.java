package ShoeKream.communityBoard.VO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class communityBoardVO {
	
	private int cbNo;
	private String cbTitle;
	private String cbCategory;
	private String cbContents;
	private String creatorId;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
	private int cbhitCnt;
	private Boolean cbdeletedyn;
	

}
