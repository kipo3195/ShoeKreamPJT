package ShoeKream.admin.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ShoeKream.admin.VO.ProductVO;
import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.admin.common.FileUtils;
import ShoeKream.admin.service.adminService;

@Controller
public class adminController {
	
	@Autowired
	private adminService as;
	
	//관리자 메인
	@GetMapping("/ShoeKream/adminMain")
	public String adminMainPage() throws Exception{
		
		return "admin/adminMain";
	}
	
	//제품 등록 페이지
	@GetMapping("/ShoeKream/admin/ProductRegist")
	public ResponseEntity<Object> productRegistPage() throws Exception{
		ResponseEntity<Object> entity;
		
		try {
			entity = new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//제품 등록 요청
	@PostMapping("/ShoeKream/admin/productRegistRequest")
	public String productRegistRequest(ProductVO vo,RedirectAttributes rttr)throws Exception{
		
		String msg;
		int result = as.productRegist(vo);
	
		if(result == 1){
			msg = "정상적으로 등록이 완료되었습니다.";
		}else {
			msg = "얘기치 못한 이유로 등록이 실패 하였습니다. ";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/ShoeKream/adminMain";
	}
	
	//게시판 등록 전 상품 코드 검색 -ajax
	@GetMapping("/ShoeKream/admin/SearchProduct")
	public ResponseEntity<Object> searchProduct(
			@RequestParam("pno")int pno) throws Exception{
		ResponseEntity<Object> entity;
		
		ProductVO pvo = as.searchProduct(pno);
		
		try {
			entity = new ResponseEntity<>(pvo,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//lux 게시판 등록 처리
	@PostMapping("/ShoeKream/admin/pBoardRegistRequest")
	public ModelAndView pBoardRegistRequest(luxBoardVO lbvo,RedirectAttributes rttr,MultipartHttpServletRequest mhsr)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/ShoeKream/adminMain");
		String msg ;
		
		//이미지 파일 넘겨받기 작업
		FileUtils fileUtils = new FileUtils();
		int result = as.pBoardRegistRequest(fileUtils.parseFile(lbvo, mhsr));
		
		
		if(result == 1) {
			msg ="게시글 등록에 성공했습니다.";
		}else {
			msg ="알수 없는 이유로 게시글 등록에 실패 했습니다.";
			
		}
		rttr.addFlashAttribute("msg", msg);
			
		return mv;
	}
	
	//luxury Product Board
	@GetMapping("/ShoeKream/admin/luxuryBoard")
	public ResponseEntity<Object> luxuryProductPage()throws Exception{
		
		ResponseEntity<Object> entity;
		
		try {
			entity = new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;

	}
	
	//luxury Product  Board 글 작성
	@GetMapping("/ShoeKream/admin/luxuryBoardWrite")
	public String luxuryProductWritePage()throws Exception{
		
		return "admin/luxuryBoardWrite";
	}
	
	
	//shoes Product Board
	@GetMapping("/ShoeKream/admin/shoesBoard")
	public ModelAndView shoesProductPage()throws Exception{
		
		ModelAndView mv = new ModelAndView("admin/shoesBoard");
		
		return mv;
	}
	
	@PostMapping("/ShoeKream/SearchProduct")
	public ResponseEntity<Object> searchProduct(@RequestParam("searchAttr")String searchAttr,@RequestParam("searchWord")String searchWord) throws Exception{
		ResponseEntity<Object> entity ;
		
		List<ProductVO> productList = as.selectProductList(searchAttr,searchWord);
		
		try {
			entity = new ResponseEntity<>(productList,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
		
	}
	
	@GetMapping("/ShoeKream/deleteProduct/{pno}")
	public String deleteProduct(@PathVariable("pno")String pno,RedirectAttributes rttr) throws Exception {
		
		int result = as.deleteProduct(pno);
		String msg;
		if(result == 1) {
			msg = "해당 제품을 삭제 하였습니다.";
		}else {
			msg ="삭제 요청이 정상적으로 작동하지 않았습니다.";
		}
		
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/ShoeKream/adminMain";
	}
	
	@PostMapping("/ShoeKream/updateProduct")
	public ResponseEntity<Object> updateProduct(@RequestParam("pno")int pno)throws Exception{
		ResponseEntity<Object> entity;
		
		ProductVO pvo = as.selectProduct(pno);
		
		
		try {
			entity = new ResponseEntity<>(pvo,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
		
	}
	
	@PostMapping("/ShoeKream/updateProductRequest")
	public String updateProductRequest(ProductVO pvo,RedirectAttributes rttr)throws Exception{
		
		
		int result = as.updateProductRequest(pvo);
		
		String msg;
		if(result == 1) {
			msg = "해당 제품을 정상적으로 수정 하였습니다.";
		}else {
			msg ="수정 요청이 정상적으로 작동하지 않았습니다.";
		}
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/ShoeKream/adminMain";
	}

	
}
