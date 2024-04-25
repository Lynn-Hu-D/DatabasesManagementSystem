package review.model;

public class Restaurants {
	protected int restaurantId;
	protected String name;
	protected String description;
	protected String menu;
	protected String hours;
	protected boolean active;
	protected CuisineType cuisineType;
	protected String street1;
	protected String street2;
	protected String city;
	protected String state;
	protected int zip;
	protected String companyName;
	
	public enum CuisineType {
		AMERICAN,
		WMERIVAN,
		ASIAN,
		EUROPEAN,
		HISPANIC
	};
	


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
	 */
	public Restaurants(int restaurantId, String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName) {
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.hours = hours;
		this.active = active;
		this.cuisineType = cuisineType;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.companyName = companyName;
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
	 */
	public Restaurants(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			String companyName) {
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.hours = hours;
		this.active = active;
		this.cuisineType = cuisineType;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.companyName = companyName;
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
	 */
	public Restaurants(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String city, String state, int zip, String companyName) {
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.hours = hours;
		this.active = active;
		this.cuisineType = cuisineType;
		this.street1 = street1;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.companyName = companyName;
	}


	/**
	 * @return the restaurantId
	 */
	public int getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}

	/**
	 * @return the hours
	 */
	public String getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the cuisineType
	 */
	public CuisineType getCuisineType() {
		return cuisineType;
	}

	/**
	 * @param cuisineType the cuisineType to set
	 */
	public void setCuisineType(CuisineType cuisineType) {
		this.cuisineType = cuisineType;
	}



	@Override
	public String toString() {
		return "Restaurants [restaurantId=" + restaurantId + ", name=" + name + ", description=" + description
				+ ", menu=" + menu + ", hours=" + hours + ", active=" + active + ", cuisineType=" + cuisineType
				+ ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", zip="
				+ zip + ", companyName=" + companyName + "]";
	}
	
	
	
	
	

}
