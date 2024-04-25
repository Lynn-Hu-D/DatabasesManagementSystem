package review.model;

public class Recommendations {
	protected int recommendationId;
	protected String userName;
	protected int restaurantId;
	
	/**
	 * @param recommendationId
	 * @param userName
	 * @param restaurantId
	 */
	public Recommendations(int recommendationId, String userName, int restaurantId) {
		this.recommendationId = recommendationId;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}

	/**
	 * @param userName
	 * @param restaurantId
	 */
	public Recommendations(String userName, int restaurantId) {
		this.userName = userName;
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the recommendationId
	 */
	public int getRecommendationId() {
		return recommendationId;
	}

	/**
	 * @param recommendationId the recommendationId to set
	 */
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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

	/**
	 * @return the restaurantId
	 */
	public Integer getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Recommendations [recommendationId=" + recommendationId + ", userName=" + userName + ", restaurantId="
				+ restaurantId + "]";
	}
	
	
	

}
