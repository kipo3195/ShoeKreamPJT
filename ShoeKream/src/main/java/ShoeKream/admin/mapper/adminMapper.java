package ShoeKream.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;

@Mapper
public interface adminMapper {

	int productRegist(ProductVO vo)throws Exception;

	ProductVO searchProduct(int pno)throws Exception;

	int pBoardRegistRequest(luxBoardVO lbvo)throws Exception;

}
