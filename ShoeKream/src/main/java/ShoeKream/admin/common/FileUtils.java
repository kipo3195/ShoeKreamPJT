package ShoeKream.admin.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ShoeKream.admin.VO.luxBoardVO;

//ShoeKream
@Component
public class FileUtils {
	
	//따로 해당 파일에 대한 정보를 저장하는 방법을 luxboardDto를 통해 쿼리문에 접근하면 될거같고
	//하나의 dto객체를만든 다음, 리턴으로 넘겨줘서 service단에서 3개의 이미지를 삽입하는방식으로 하던지
	//아니면 매개변수로 같이vo를 받아서 이미지 3개를 다 set으로 세팅하고 리턴을 luxBoardVO로 하던지 하면될듯
	
	public luxBoardVO parseFile(luxBoardVO lbvo, MultipartHttpServletRequest mhsr) throws Exception {
		
		int count =0; //썸네일, 이미지1, 이미지2 구분
		 
		if(ObjectUtils.isEmpty(mhsr)) {
			return lbvo;
		}
		
		//서버에 저장할 파일 폴더 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		
		File file = new File(path);
		if(file.exists() ==false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = mhsr.getFileNames();
		String newFileName, originalFileExtension,contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list =mhsr.getFiles(iterator.next());
				for(MultipartFile multipartFile : list) {
					if(multipartFile.isEmpty() == false) {
						contentType = multipartFile.getContentType(); //파일의 속성중에 컨텐츠타입이 뭔지 확인을 하는
						if(ObjectUtils.isEmpty(contentType)) {//컨텐츠 타입이 비어있으면 넘어온게 없으니까 break하는듯;
							break;
						}else {
							//아니면 작업시작 - 확장자를 따로 빼내는 작업
							
							if(contentType.contains("image/jpeg")) {
								originalFileExtension =".jpg";
							}else if(contentType.contains("image/png")) {
								originalFileExtension =".png";
							}else if(contentType.contains("image/gif")) {
								originalFileExtension =".gif";
							}else {
								break;
							}
					}	
						
					//빼낸 확장자를 활용하여 새로운 파일이름 생성  : 현재 시간+ 확장자로 하는듯
					newFileName = Long.toString(System.nanoTime()) +
							originalFileExtension;
				
					if(count == 0) {
							lbvo.setLuxbThumbnailImg(path+"/"+newFileName);
							count ++;
					}else if(count == 1) {
							lbvo.setLuxbImg1(path+"/"+newFileName);
							count ++;
					}else if(count == 2) {
							lbvo.setLuxbImg2(path+"/"+newFileName);
					}
					
					file = new File(path+"/"+newFileName);
					multipartFile.transferTo(file);
					
					}
					
					
		}
		
	}
		return lbvo;
}
	
	
}
