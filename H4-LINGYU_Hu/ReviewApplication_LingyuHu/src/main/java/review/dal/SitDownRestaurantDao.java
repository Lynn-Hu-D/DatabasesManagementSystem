package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Restaurants;
import review.model.SitDownRestaurant;


public class SitDownRestaurantDao extends RestaurantsDao{
	
	private static SitDownRestaurantDao instance = null;
	

	protected SitDownRestaurantDao(){
		super();
	}
	
	
	public static SitDownRestaurantDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantDao();
		}
		return instance;
	}
	
	
	
	public SitDownRestaurant create(SitDownRestaurant sitDownRestaurant) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			
			Restaurants newRestaurant = super.create(new Restaurants(sitDownRestaurant.getName(), sitDownRestaurant.getDescription(), 
					sitDownRestaurant.getMenu(), sitDownRestaurant.getHours(), sitDownRestaurant.isActive(), 
					sitDownRestaurant.getCuisineType(), sitDownRestaurant.getStreet1(), sitDownRestaurant.getStreet2(), 
					sitDownRestaurant.getCity(), sitDownRestaurant.getState(), sitDownRestaurant.getZip(), sitDownRestaurant.getCompanyName()));

	        
			int restaurantId = newRestaurant.getRestaurantId();
			String insertRestaurant = "INSERT INTO SitDownRestaurant(RestaurantId, Capacity) VALUES(?,?);";
			
			insertStmt = connection.prepareStatement(insertRestaurant);
			
			insertStmt.setInt(1, restaurantId);
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
	
			insertStmt.executeUpdate();
			sitDownRestaurant.setRestaurantId(restaurantId);

			return sitDownRestaurant;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
		
	}
	
	
	public SitDownRestaurant getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRestaurant = "SELECT Name, Description, Menu, Hours, Active, CuisineType, Street1, street2,City, State, Zip, CompanyName, Capacity " +
		"FROM SitDownRestaurant INNER JOIN Restaurants " +
		"ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
		"WHERE SitDownRestaurant.RestaurantId=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, sitDownRestaurantId);
			
			results = selectStmt.executeQuery();
			if (results.next()) {
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				Boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String companyName = results.getString("CompanyName");
				int capacity = results.getInt("Capacity");
				
				SitDownRestaurant restaurant = new SitDownRestaurant(sitDownRestaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, capacity);
				return restaurant;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
		
		
	}
	
	public List<SitDownRestaurant> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {
		List<SitDownRestaurant> restaurants = new ArrayList<SitDownRestaurant>();
		String selectRestaurants =
			"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, street2, City, State, Zip, CompanyName, Capacity " +
			"FROM SitDownRestaurant INNER JOIN Restaurants " +
			"ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
			"WHERE companyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				Boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				int capacity = results.getInt("Capacity");

				SitDownRestaurant restaurant = new SitDownRestaurant(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, capacity);
				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return restaurants;	
	}
	
	
	public SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteRestaurant = "DELETE FROM SitDownRestaurant WHERE  RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + sitDownRestaurant.getRestaurantId());
			}
			super.delete(sitDownRestaurant);
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
		
	}

}
