package ShoeKream.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;

@Mapper
public interface userMapper {

	void insertUser(joinVO joinvo) throws Exception;

	memberVO selectUser(loginVO loginvo);

	memberVO findUserid(@Param("username")String username,@Param("userphone")String userphone);

	memberVO findUserpass(joinVO joinvo);

	void changePassword(@Param("userid")String userid,@Param("passwordCode") String passwordCode);

	joinVO selectMyInfo(@Param("userId")String userId)throws Exception;

	loginVO selectMyPass(@Param("userId")String userId)throws Exception;

	int changePassRequets(joinVO joinvo)throws Exception;

	int withDrawRequest(@Param("myId")String myId,@Param("myPass") String myPass)throws Exception;

	int addMyCart(@Param("userId")String userId, @Param("luxbNo")String luxbNo)throws Exception;

	int selectMyCart(@Param("userId")String userId, @Param("luxbNo")String luxbNo)throws Exception;

	List<String> selectUserCart(@Param("userId")String userId)throws Exception;

	luxBoardVO selectUserCartList(@Param("luxbNo")String luxbNo)throws Exception;

	int deleteMyCart(@Param("userId")String userId, @Param("luxbNo")String luxbNo)throws Exception;

	void PassCodeInsert(@Param("userId")String userId,@Param("passCode") String passwordCode)throws Exception;

	int PassCodeDelete(@Param("userId")String userId,@Param("passCode") String passwordCode)throws Exception;

	String selectPassCode(@Param("userId")String userid)throws Exception;

	int checkPassCode(@Param("userId") String userId,@Param("passCode") String passCode)throws Exception;

	int updatePass(@Param("userId")String userId,@Param("userPass")String userPass,@Param("userPass1")String userPass1 )throws Exception;
	
}
