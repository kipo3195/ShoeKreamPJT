package ShoeKream.communityBoard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ShoeKream.communityBoard.VO.bbReplyVO;
import ShoeKream.communityBoard.VO.bulletinBoardVO;
import ShoeKream.communityBoard.VO.communityBoardVO;
import ShoeKream.communityBoard.mapper.communityBoardMapper;

@Service
public class communityBoardServiceImpl implements communityBoardService {



	@Autowired
	private communityBoardMapper cbm;
	
	
	@Override
	public List<communityBoardVO> selectboardList(int startPage)throws Exception {

		List<communityBoardVO> list = cbm.selectBoardList(startPage);
		
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



	@Override
	public int totalCommunityBoardCount() throws Exception {
		int count = cbm.totalCommunityBoardCount();
		return count;
	}


	@Override
	public Integer createBulletin(bulletinBoardVO bbvo) throws Exception {
		Integer result = cbm.insertBulletin(bbvo);
		return result;
	}


	@Override
	public List<bulletinBoardVO> selectBulletinList(int startPage) throws Exception {
		List<bulletinBoardVO> list = cbm.selectBulletin(startPage);
		return list;
	}


	@Override
	public bulletinBoardVO bulletinDetailRequest(int bbNo) throws Exception {
		bulletinBoardVO bbvo = cbm.selectBulletinDetail(bbNo);
		return bbvo;
	}


	@Override
	public int deleteBulletin(int bbNo) throws Exception {
		
		return cbm.deleteBulletin(bbNo);
		
	}


	@Override
	public Integer updateBulletin(bulletinBoardVO bbvo) throws Exception {
		
		
		return cbm.updateBulletin(bbvo);
	}


	@Override
	public void addBulletinHitCnt(int bbNo) throws Exception {
		
		cbm.addBulletinHitCnt(bbNo);
		
	}
	


	@Override
	public String clickLikeimg(int bbNo, String userId) throws Exception {
		
	int result	= cbm.likeState(bbNo,userId);
	
	//처음으로 좋아요 이미지클릭
	if(result == 0) {
		int rst = cbm.firstClick(bbNo,userId);
		cbm.addlikecount(bbNo);
		System.out.println("현재 y니까 count 증가");
		return "y";
	}
	
	String rst = cbm.nClick(bbNo,userId);

	if(rst.equals("y")) {
		System.out.println("현재 y니까 n으로변경 하면서 count 감소");
		cbm.changeYtoN(bbNo,userId);
		cbm.minuslikecount(bbNo);
		
	}else {
		System.out.println("현재 n니까 y으로변경 count 증가");
		cbm.changeNtoY(bbNo,userId);
		cbm.addlikecount(bbNo);
	}
	
	String returnValue =  cbm.nClick(bbNo, userId);
	return 	returnValue;
	
	}


	@Override
	public int totalBulletinBoardCount() throws Exception {

		
		return cbm.totalBulletinBoardCount();
	}


	@Override
	public int writeReply(bbReplyVO bbrvo) throws Exception {

		return cbm.writeReply(bbrvo);
		
	}


	@Override
	public List<bbReplyVO> selectReplyList(int StartPage,int bbNo) throws Exception {

		List<bbReplyVO> ReplyList = cbm.selectBReplyList(StartPage,bbNo);
		
		return ReplyList;
	}


	@Override
	public int deleteReply(int bcNo) throws Exception {
		
		return cbm.deleteReply(bcNo);
	}


	@Override
	public bbReplyVO selectReply(int bcNo) throws Exception {
		
		return cbm.selectReply(bcNo);
	}


	@Override
	public int updateReplyRequest(bbReplyVO bbrvo) throws Exception {
		
		return cbm.updateReplyRequest(bbrvo);
	}


	@Override
	public int totalBboardReplyCount(int bbNo) throws Exception {
		
		return cbm.totalBboardReplyCount(bbNo);
	}


	@Override
	public List<Integer> checkLike(String userId) throws Exception {
		
		int count = cbm.checkLikeCount(userId);
		
		List<Integer> likeBoardList = new ArrayList<Integer>();
		for (int i=0; i<count;i++) {
			likeBoardList.add(cbm.checkLike(userId,i));
		}
		
		System.out.println(likeBoardList);
		
		
		
		
		return likeBoardList;
	}
	
	
	
	
	
	

	
	
	
	


	
	
	
	

	
	
	
	
	

	
	
	
	

}
