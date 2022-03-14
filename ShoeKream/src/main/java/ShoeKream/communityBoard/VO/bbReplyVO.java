package ShoeKream.communityBoard.VO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class bbReplyVO {

	private int bcNo;
	private int bbNo;
	private String bcContents;
	private String creatorName;
	private String deleteYn;
	private LocalDateTime updatedDatetime;
	
}
