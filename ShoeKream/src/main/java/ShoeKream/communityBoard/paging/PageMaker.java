package ShoeKream.communityBoard.paging;

import lombok.Data;

@Data
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private int displayPageNum;
	private int maxPage;
	
	
	private boolean next;
	private boolean prev;
	private boolean first;
	private boolean last;
	
	Criteria cri;
	
	public PageMaker() {
		this(0);
	}
	public PageMaker(int totalCount) {
		this(new Criteria(),totalCount);
		// mainController에서 던져주면 제일 먼저 동작함.
		
	}
	public PageMaker(Criteria cri, int totalCount) {
		//25line의 메소드가 실행되면,  26 line 이 동작하고, 해당 메소드가 실행됨
		setCri(cri);
		setTotalCount(totalCount);
		setDisplayPageNum(5);
	}
	
	public void calc() {
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum))*displayPageNum;
	
		startPage = (endPage - displayPageNum)+1;
	
		maxPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		prev = (endPage - displayPageNum <= 0) ? false : true;  // 이전으로 이동 할 수 없다.
		
		next = (endPage == maxPage) ? false : true;  //마지막 페이지 블럭과 총 페이지 블럭이 같으면 다음이 존재 할 수 없음
		
		first = startPage > 1 ? true : false; // 시작페이지가 1보다 크다면 당연히 처음으로 갈 수 있음.
		
		last = (cri.getPage() < maxPage) ? true : false; //

	}
	
	//30line의 메소드가 동작하게 되면 해당 메소드의 동작이 아래의 3개의 메소드를 실행시킴
	public void setCri(Criteria cri) {  // PageMaker를 통해서도 page, perPageNum을 정의 할 수 있도록 한다.
		this.cri = cri;                 // 페이지 정보가 다시 요청되는 순간 페이지에 대한 계산도 다시 이뤄진다.
		calc();
	}
	
	public void setTotalCount(int totalCount) {   //총 게시글의 수가 초기화 된다면, 마찬가지로 계산도 다시 이뤄진다.
		this.totalCount = totalCount;
		calc();
	}
	
	public void setDisplayPageNum(int displayPageNum) {  //보여줄 페이지 블럭수가 변해도 페이지 계산이 다시 이뤄진다.
		this.displayPageNum = displayPageNum;
		calc();
	}
	

}
