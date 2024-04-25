package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.FoodCartRestaurant;
import review.model.Restaurants;


public class FoodCartRestaurantDao extends RestaurantsDao {
	private static FoodCartRestaurantDao instance = null;
	

	protected FoodCartRestaurantDao(){
		super();
	}
	
	
	public static FoodCartRestaurantDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantDao();
		}
		return instance;
	}

	public FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		Restaurants newRestaurant = super.create(new Restaurants(foodCartRestaurant.getName(), foodCartRestaurant.getDescription(), 
				foodCartRestaurant.getMenu(), foodCartRestaurant.getHours(), foodCartRestaurant.isActive(), 
				foodCartRestaurant.getCuisineType(), foodCartRestaurant.getStreet1(), foodCartRestaurant.getStreet2(), 
				foodCartRestaurant.getCity(), foodCartRestaurant.getState(), foodCartRestaurant.getZip(), foodCartRestaurant.getCompanyName()));

		String insertRestaurant = "INSERT INTO FoodCartRestaurant(RestaurantId, Licensed) VALUES(?,?)";
		int restaurantId = newRestaurant.getRestaurantId();
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);
			
			insertStmt.setInt(1, restaurantId);
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
	
			insertStmt.executeUpdate();
			foodCartRestaurant.setRestaurantId(restaurantId);
			return foodCartRestaurant;
			
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
	public FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRestaurant = "SELECT Name AS Name, Description, Menu, Hours, Active, CuisineType, Street1, street2,City, State, Zip, CompanyName, Licensed " +
		"FROM FoodCartRestaurant INNER JOIN Restaurants " +
		"ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
		"WHERE FoodCartRestaurant.RestaurantId=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, foodCartRestaurantId);
			
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
				Boolean licensed = results.getBoolean("Licensed");
				
				FoodCartRestaurant restaurant = new FoodCartRestaurant(foodCartRestaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, licensed);
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
	public List<FoodCartRestaurant> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException {
		List<FoodCartRestaurant> restaurants = new ArrayList<FoodCartRestaurant>();
		String selectRestaurants =
			"SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, street2, City, State, Zip, CompanyName, licensed " +
			"FROM FoodCartRestaurant INNER JOIN Restaurants " +
			"ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
			"WHERE Restaurants.companyName=?;";
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
				Boolean licensed = results.getBoolean("Licensed");

				FoodCartRestaurant restaurant = new FoodCartRestaurant(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName, licensed);
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
	public FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteRestaurant = "DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + foodCartRestaurant.getRestaurantId());
			}
			super.delete(foodCartRestaurant);
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
