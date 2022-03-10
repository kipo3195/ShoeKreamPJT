package ShoeKream.communityBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	List<bulletinBoardVO> selectBulletin()throws Exception;

	bulletinBoardVO selectBulletinDetail(@Param("bbNo")int bbNo)throws Exception;

	int deleteBulletin(int bbNo)throws Exception;

	Integer updateBulletin(bulletinBoardVO bbvo)throws Exception;

	void addBulletinHitCnt(int bbNo) throws Exception;


}
