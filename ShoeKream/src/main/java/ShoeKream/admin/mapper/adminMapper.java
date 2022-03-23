package ShoeKream.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;

@Mapper
public interface adminMapper {

	int productRegist(ProductVO vo)throws Exception;

	ProductVO searchProduct(int pno)throws Exception;

	int pBoardRegistRequest(luxBoardVO lbvo)throws Exception;

	ProductVO selectProductName(@Param("searchWord")String searchWord,@Param("flag")int flag)throws Exception;

	ProductVO selectProductNo(@Param("searchNo")int searchNo)throws Exception;

	ProductVO selectProductBrand(@Param("searchWord")String searchWord,@Param("flag")int flag)throws Exception;

	int deleteProduct(@Param("pNo")int pNo)throws Exception;

	ProductVO selectProduct(@Param("pno")int pno)throws Exception;

	int updateProductRequest(ProductVO pvo)throws Exception;


}
