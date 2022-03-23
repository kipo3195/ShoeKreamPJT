package ShoeKream.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	@Override
	public List<ProductVO> selectProductList(String searchAttr, String searchWord) throws Exception {
		
		List<ProductVO> selectProductList = new ArrayList<ProductVO>();
		
		if(searchAttr.equals("productName")) {
			//productName 5개까지 조회 가능
			for(int i = 0; i < 5; i++) {
				ProductVO pvo = am.selectProductName(searchWord,i);
				selectProductList.add(pvo);
			}
			
			
		}else if(searchAttr.equals("productNo")) {
		
		int searchNo = Integer.parseInt(searchWord);
		ProductVO pvo = am.selectProductNo(searchNo);
		selectProductList.add(pvo);
			
			
		}else {
			//product Brand 5개까지 조회 가능
			for(int i = 0; i < 5; i++) {
				ProductVO pvo = am.selectProductBrand(searchWord,i);
				selectProductList.add(pvo);
			}
			
		}
		
		return selectProductList;
	}

	@Override
	public int deleteProduct(String pno) throws Exception {
		
		 int pNo = Integer.parseInt(pno);
		 int result = am.deleteProduct(pNo);
		return result;
	}

	@Override
	public ProductVO selectProduct(int pno) throws Exception {
		
		ProductVO pvo = am.selectProduct(pno);
		return pvo;
	}

	@Override
	public int updateProductRequest(ProductVO pvo) throws Exception {
		
		int result = am.updateProductRequest(pvo);
		return result;
	}
	
	
	
	
	
	

}
