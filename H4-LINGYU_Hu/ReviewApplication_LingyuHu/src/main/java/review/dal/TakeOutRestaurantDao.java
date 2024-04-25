package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Restaurants;
import review.model.TakeOutRestaurant;

public class TakeOutRestaurantDao extends RestaurantsDao{

	
	private static TakeOutRestaurantDao instance = null;
	

	protected TakeOutRestaurantDao(){
		super();
	}

	
	public static TakeOutRestaurantDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantDao();
		}
		return instance;
	}
	
	
	public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		Restaurants newRestaurant = super.create(new Restaurants(takeOutRestaurant.getName(), takeOutRestaurant.getDescription(), 
				takeOutRestaurant.getMenu(), takeOutRestaurant.getHours(), takeOutRestaurant.isActive(), 
				takeOutRestaurant.getCuisineType(), takeOutRestaurant.getStreet1(), takeOutRestaurant.getStreet2(), 
				takeOutRestaurant.getCity(), takeOutRestaurant.getState(), takeOutRestaurant.getZip(), takeOutRestaurant.getCompanyName()));

		int restaurantId = newRestaurant.getRestaurantId();
		String insertRestaurant = "INSERT INTO TakeOutRestaurant(RestaurantId, MaxWaitTime) VALUES(?,?);";
	
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);
			
			insertStmt.setInt(1, restaurantId);
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
	
			insertStmt.executeUpdate();
			takeOutRestaurant.setRestaurantId(restaurantId);
			return takeOutRestaurant;
			
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
	
	public TakeOutRestaurant getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRestaurant = "SELECT Name AS Name, Description, Menu, Hours, Active, CuisineType, Street1, street2,City, State, Zip, CompanyName, MaxWaitTime " +
				"FROM TakeOutRestaurant INNER JOIN Restaurants " +
				"ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE TakeOutRestaurant.RestaurantId=?;";
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, takeOutRestaurantId);
			
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
				int maxWaitTime = results.getInt("MaxWaitTime");
				
				TakeOutRestaurant restaurant = new TakeOutRestaurant(takeOutRestaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, maxWaitTime);
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
	
	public List<TakeOutRestaurant> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException{
		List<TakeOutRestaurant> restaurants = new ArrayList<TakeOutRestaurant>();
		String selectRestaurants =
				"SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, street2, City, State, Zip, CompanyName, MaxWaitTime " +
				"FROM TakeOutRestaurant INNER JOIN Restaurants " +
				"ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
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
				int maxWaitTime = results.getInt("MaxWaitTime");

				TakeOutRestaurant restaurant = new TakeOutRestaurant(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, maxWaitTime);
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
	
	public TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteRestaurant = "DELETE FROM TakeOutRestaurant WHERE  RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + takeOutRestaurant.getRestaurantId());
			}
			super.delete(takeOutRestaurant);
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
