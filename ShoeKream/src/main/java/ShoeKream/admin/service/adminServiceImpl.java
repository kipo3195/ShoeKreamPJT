package ShoeKream.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.admin.mapper.adminMapper;

@Service
public class adminServiceImpl implements adminService{

	@Autowired
	private adminMapper am;
	
	@Override
	public int productRegist(ProductVO vo) throws Exception {
		
		
		return am.productRegist(vo);
	}

	@Override
	public ProductVO searchProduct(int pno) throws Exception {
		
		ProductVO pvo = am.searchProduct(pno);
		
		return pvo;
	}

	@Override
	public int pBoardRegistRequest(luxBoardVO lbvo) throws Exception {
		
		int result = am.pBoardRegistRequest(lbvo);
		
		
		
		return result;
	}
	
	
	
	
	
	

}
