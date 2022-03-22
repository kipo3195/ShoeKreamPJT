package ShoeKream.admin.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class luxBoardDto {

	private int fileNo;
	private String luxbNo;
	private String creatorId;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
	
	
}
