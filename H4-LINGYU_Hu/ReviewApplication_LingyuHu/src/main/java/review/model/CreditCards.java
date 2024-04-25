package review.model;

import java.util.Date;

public class CreditCards {
	protected  long cardNumber;
	protected Date expiration;
	protected String userName;
	
	/**
	 * @param cardNumber
	 * @param expiration
	 * @param userName
	 */
	public CreditCards(long cardNumber, Date expiration, String userName) {
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.userName = userName;
	}

	/**
	 * @return the cardNumber
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the expiration
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "CreditCards [cardNumber=" + cardNumber + ", expiration=" + expiration + ", userName=" + userName + "]";
	}
	
	
	
	

}
