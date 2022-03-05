package ShoeKream.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;

@Mapper
public interface userMapper {

	void insertUser(joinVO joinvo) throws Exception;

	memberVO selectUser(loginVO loginvo);

	memberVO findUserid(@Param("username")String username,@Param("userphone")String userphone);
	
}
