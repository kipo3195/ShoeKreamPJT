package ShoeKream.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;

public interface adminService {

	int productRegist(ProductVO vo)throws Exception;

	ProductVO searchProduct(int pno)throws Exception;

	int pBoardRegistRequest(luxBoardVO lbvo)throws Exception;

	List<ProductVO> selectProductList(String searchAttr, String searchWord)throws Exception;

	int deleteProduct(String pno)throws Exception;

	ProductVO selectProduct(int pno)throws Exception;

	int updateProductRequest(ProductVO pvo)throws Exception;

	
	

}
