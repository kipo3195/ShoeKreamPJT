package ShoeKream.communityBoard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ShoeKream.communityBoard.VO.bulletinBoardVO;
import ShoeKream.communityBoard.VO.communityBoardVO;


public interface communityBoardService {

	List<communityBoardVO> selectboardList(int startPage) throws Exception;

	Integer createNotice(communityBoardVO cbvo) throws Exception;

	communityBoardVO noticeDetailRequest(int cbNo)throws Exception;

	void deleteNotice(int cbNo)throws Exception;

	Integer updateNotice(communityBoardVO cbv)throws Exception;

	void addnoticeHitCnt(int cbNo)throws Exception;

	int totalCommunityBoardCount()throws Exception;

	Integer createBulletin(bulletinBoardVO bbvo) throws Exception;

	List<bulletinBoardVO> selectBulletinList(int startPage) throws Exception;

	bulletinBoardVO bulletinDetailRequest(int bbNo)throws Exception;

	int deleteBulletin(int bbNo)throws Exception;

	Integer updateBulletin(bulletinBoardVO bbvo)throws Exception;

	void addBulletinHitCnt(int bbNo)throws Exception;

	int totalBulletinBoardCount()throws Exception;


}
