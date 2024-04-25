package review.model;

public class FoodCartRestaurant extends Restaurants {
	protected boolean licensed;


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
	 * @param licensed 
	 */
	public FoodCartRestaurant(int restaurantId, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, boolean licensed) {
		super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip,
				companyName);
		// TODO Auto-generated constructor stub
		this.licensed = licensed;
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
	 * @param licensed 
	 */
	public FoodCartRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String city, String state, int zip, String companyName, boolean licensed) {
		super(name, description, menu, hours, active, cuisineType, street1, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.licensed = licensed;
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
	 * @param licensed 
	 */
	public FoodCartRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, boolean licensed) {
		super(name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.licensed = licensed;
	}
	

	/**
	 * @return the licensed
	 */
	public boolean isLicensed() {
		return licensed;
	}

	/**
	 * @param licensed the licensed to set
	 */
	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}

	@Override
	public String toString() {
		return "FoodCartRestaurant [licensed=" + licensed + "]";
	}
	
	

}
