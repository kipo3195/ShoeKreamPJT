package ShoeKream.communityBoard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ShoeKream.communityBoard.VO.communityBoardVO;


public interface communityBoardService {

	List<communityBoardVO> selectboardList() throws Exception;

	Integer createNotice(communityBoardVO cbvo) throws Exception;

	communityBoardVO noticeDetailRequest(int cbNo)throws Exception;

	void deleteNotice(int cbNo)throws Exception;

	Integer updateNotice(communityBoardVO cbv)throws Exception;

	void addnoticeHitCnt(int cbNo)throws Exception;

}
