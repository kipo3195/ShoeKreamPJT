package ShoeKream.admin.service;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;

public interface adminService {

	int productRegist(ProductVO vo)throws Exception;

	ProductVO searchProduct(int pno)throws Exception;

	int pBoardRegistRequest(luxBoardVO lbvo)throws Exception;
	
	

}