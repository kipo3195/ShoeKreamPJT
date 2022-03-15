package ShoeKream.admin.VO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductVO {
	
	private int pNo;
	private String pBrand;
	private String pProduct;
	private String pIntro;
	private int pPrice;
	private String pCategory;
	private String pClassCode;
	private LocalDateTime createdDatetime;
	private String onSale; //판매 유무

}
