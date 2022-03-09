package ShoeKream.communityBoard.paging;

import lombok.Data;

@Data
public class Criteria {
	
private int page;
private int perPageNum;

	public Criteria() {
		this(1,10);
	}
	
	// 1번 페이지를요청하고, 10개의 게시물을 보여주는것이 기본값.
	public Criteria(int page, int perPageNum) {
		setPage(page);
		this.perPageNum = perPageNum;
	}

	//페이지를 음수로  요청한다면 1페이지 보여줌
	private void setPage(int page) {
		
		if(page <= 0) {
			this.page = 1;
		}
		this.page = page;
		
	}
	// 게시물의 수가 100개를 넘기거나, 음수 개수라면 10개의 게시물을 보여줌.
	public void setPerPageNum(int perPageNum) {
		if(perPageNum < 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}//그렇지 않다면 지정한 99개 이내의 게시물을 보여주는 것.
		
		this.perPageNum = perPageNum;
	}
	
	//시작하는 페이지를 얻는 방법. 만약 1페이지 요청, 10개씩보여달라! -> 0 * 10이니까 시작페이지는 0-> 쿼리에서 limit 0,10이니까 0부터 10개를 가져온다.
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}

}
