package review.model;

public class SitDownRestaurant extends Restaurants {
	protected int capacity;



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
	 * @param capacity 
	 */
	public SitDownRestaurant(int restaurantId, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, int capacity) {
		super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip,
				companyName);
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
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
	 * @param capacity 
	 */
	public SitDownRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String city, String state, int zip, String companyName, int capacity) {
		super(name, description, menu, hours, active, cuisineType, street1, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
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
	 * @param capacity 
	 */
	public SitDownRestaurant(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName, int capacity) {
		super(name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "SitDownRestaurant [capacity=" + capacity + "]";
	}
	

}
