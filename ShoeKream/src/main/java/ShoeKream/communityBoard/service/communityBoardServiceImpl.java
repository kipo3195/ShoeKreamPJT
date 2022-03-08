package ShoeKream.communityBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ShoeKream.communityBoard.VO.communityBoardVO;
import ShoeKream.communityBoard.mapper.communityBoardMapper;

@Service
public class communityBoardServiceImpl implements communityBoardService {



	@Autowired
	private communityBoardMapper cbm;
	
	
	@Override
	public List<communityBoardVO> selectboardList()throws Exception {

		List<communityBoardVO> list = cbm.selectBoardList();
		
		return list;
	}





	@Override
	public Integer createNotice(communityBoardVO cbvo) throws Exception {
		
	Integer result	= cbm.insertNotice(cbvo);
	
		return result;
		
	}
	
	
	@Override 
	public communityBoardVO noticeDetailRequest(int cbNo) throws Exception {
		
		communityBoardVO cbv	= cbm.selectNoticeDetail(cbNo);
	
		return cbv;
	}
	
	@Override
	public void deleteNotice(int cbNo) throws Exception {
		
		cbm.deleteNotice(cbNo);
		
		
	}


	@Override
	public Integer updateNotice(communityBoardVO cbv) throws Exception {
		
		Integer rst = cbm.updateNotice(cbv);
		
		return rst;
	}



	@Override
	public void addnoticeHitCnt(int cbNo) throws Exception {
		
		cbm.addnoticeHitCnt(cbNo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
