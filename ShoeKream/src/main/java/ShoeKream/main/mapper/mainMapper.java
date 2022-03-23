package ShoeKream.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.user.VO.joinVO;

@Mapper
public interface mainMapper {

	List<luxBoardVO> selectMain()throws Exception;

	luxBoardVO selectLuxBoard(@Param("luxbNo")int luxbNo)throws Exception;

	int deleteBoard(@Param("luxbNo")int luxbNo)throws Exception;

	int updateBoard(luxBoardVO lbvo)throws Exception;

	int totalBoardCount()throws Exception;

	List<luxBoardVO> addLuxBoard(@Param("i")int i)throws Exception;




}
