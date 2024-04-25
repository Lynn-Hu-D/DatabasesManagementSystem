package review.model;

public class TakeOutRestaurant extends Restaurants {

	@Override
	public String toString() {
		return "TakeOutRestaurant [maxWaitTime=" + maxWaitTime + "]";
	}

	protected int maxWaitTime;
	



	/**
	 * @param restaurantId
	 * @param name
	 * @param description
	 * @param menu
	 * @param hours
	 * @param active
	 * @param cuisineType
	 * @param street1
	 * @param street2
	 * @param city
	 * @param state
	 * @param zip
	 * @param companyName
	 * @param maxWaitTime 
	 */
	public TakeOutRestaurant(int restaurantId, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, int maxWaitTime) {
		super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip,
				companyName);
		// TODO Auto-generated constructor stub
		this.maxWaitTime = maxWaitTime;
	}

	/**
	 * @param name
	 * @param description
	 * @param menu
	 * @param hours
	 * @param active
	 * @param cuisineType
	 * @param street1
	 * @param city
	 * @param state
	 * @param zip
	 * @param companyName
	 * @param maxWaitTime 
	 */
	public TakeOutRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String city, String state, int zip, String companyName, int maxWaitTime) {
		super(name, description, menu, hours, active, cuisineType, street1, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.maxWaitTime = maxWaitTime;
	}

	/**
	 * @param name
	 * @param description
	 * @param menu
	 * @param hours
	 * @param active
	 * @param cuisineType
	 * @param street1
	 * @param street2
	 * @param city
	 * @param state
	 * @param zip
	 * @param companyName
	 * @param maxWaitTime 
	 */
	public TakeOutRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, int maxWaitTime) {
		super(name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.maxWaitTime = maxWaitTime;
	}
	

	/**
	 * @return the maxWaitTime
	 */
	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	/**
	 * @param maxWaitTime the maxWaitTime to set
	 */
	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
	

}
