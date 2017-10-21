package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Profile model of a merchant. For the being, we use seller information only 
 * <p>
 * @version $Date$<br>
 *          $Revision$<br>
 *          $Author$<br>
 *          $HeadURL$
 */
public class Profile {
	
	@JsonProperty("seller")
	private Seller seller;

	/**
	 * Gets seller information of a profile.
	 * <p>
	 * @return Returns the seller.
	 */
	public Seller getSeller() {
		return seller;
	}

}
