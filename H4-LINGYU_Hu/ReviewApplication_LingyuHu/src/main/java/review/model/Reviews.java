package review.model;
import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Date created;
	protected String content;
	protected double rating;
	protected String userName;
	protected Integer restaurantId;
	


	/**
	 * @param reviewId
	 * @param created
	 * @param content
	 * @param rating
	 * @param userName
	 * @param restaurantId
	 */
	public Reviews(int reviewId, Date created, String content, double rating, String userName, Integer restaurantId) {
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	

	/**
	 * @param reviewId
	 * @param created
	 * @param content
	 * @param rating
	 */
	public Reviews(int reviewId, Date created, String content, double rating) {
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
	}



	/**
	 * @param created
	 * @param content
	 * @param rating
	 * @param userName
	 * @param restaurantId
	 */
	public Reviews(Date created, String content, double rating, String userName, Integer restaurantId) {
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}



	/**
	 * @return the reviewId
	 */
	public int getReviewId() {
		return reviewId;
	}

	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
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
		return "Reviews [reviewId=" + reviewId + ", created=" + created + ", content=" + content + ", rating=" + rating
				+ ", userName=" + userName + ", restaurantId=" + restaurantId + "]";
	}
	
	
	
	

	
	
	
	

}
