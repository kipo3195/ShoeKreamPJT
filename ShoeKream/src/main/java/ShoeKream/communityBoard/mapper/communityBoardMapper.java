package ShoeKream.communityBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ShoeKream.communityBoard.VO.bbReplyVO;
import ShoeKream.communityBoard.VO.bulletinBoardVO;
import ShoeKream.communityBoard.VO.communityBoardVO;

@Mapper
public interface communityBoardMapper {

	List<communityBoardVO> selectBoardList(int startPage) throws Exception;

	Integer insertNotice(communityBoardVO cbvo) throws Exception;

	communityBoardVO selectNoticeDetail(@Param("cbNo") int cbNo) throws Exception;

	void deleteNotice(int cbNo)throws Exception;

	Integer updateNotice(communityBoardVO cbv)throws Exception ;

	void addnoticeHitCnt(int cbNo)throws Exception;

	int totalCommunityBoardCount()throws Exception;

	Integer insertBulletin(bulletinBoardVO bbvo)throws Exception;

	List<bulletinBoardVO> selectBulletin(int startPage)throws Exception;

	bulletinBoardVO selectBulletinDetail(@Param("bbNo")int bbNo)throws Exception;

	int deleteBulletin(int bbNo)throws Exception;

	Integer updateBulletin(bulletinBoardVO bbvo)throws Exception;

	void addBulletinHitCnt(int bbNo) throws Exception;

	int totalBulletinBoardCount() throws Exception;

	int writeReply(bbReplyVO bbrvo) throws Exception;

	List<bbReplyVO> selectBReplyList(@Param("StartPage")int StartPage,@Param("bbNo")int bbNo)throws Exception;

	int deleteReply(@Param("bcNo")int bcNo)throws Exception;

	bbReplyVO selectReply(@Param("bcNo")int bcNo)throws Exception;

	int updateReplyRequest(bbReplyVO bbrvo)throws Exception;

	int totalBboardReplyCount(@Param("bbNo")int bbNo)throws Exception;

	int likeState(@Param("bbno")String bbno,@Param("userid")String userid)throws Exception;

	void firstClickLike(@Param("bbno")String bbno,@Param("userid") String userid)throws Exception;

	String likeStateRequest(@Param("bbno")String bbno,@Param("userid") String userid)throws Exception;

	void changeYtoN(@Param("bbno")String bbno,@Param("userid") String userid)throws Exception;
	
	void changeNtoY(@Param("bbno")String bbno,@Param("userid") String userid)throws Exception;

}
