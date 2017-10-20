package myobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seller {
	
	@JsonProperty("seller")
	private SellerDetail sellerDetail;

	/**
	 * Gets the sellerDetail.
	 * <p>
	 * @return Returns the sellerDetail.
	 */
	public SellerDetail getSellerDetail() {
		return sellerDetail;
	} 
}
