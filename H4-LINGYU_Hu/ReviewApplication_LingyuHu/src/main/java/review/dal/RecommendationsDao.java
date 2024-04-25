package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import review.model.Recommendations;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;
	private static RecommendationsDao instance = null;
	
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendation =
				"INSERT INTO Recommendations(UserName, RestaurantId) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, recommendation.getUserName());
			
		  if (recommendation.getRestaurantId() != null) {
                insertStmt.setInt(2, recommendation.getRestaurantId());
            } else {
                insertStmt.setNull(2, Types.INTEGER); // Handle null RestaurantId
            }
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(reviewId);
			return recommendation;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
		String selectReview =
				"SELECT RecommendationId, UserName, RestaurantId " +
				"FROM Recommendations " +
//				"INNER JOIN Restaurants " +
//				"ON Recommendations.RestaurantId = Restaurants.RestaurantId " +
//				"INNER JOIN Users ON Recommendations.UserName = Users.UserName " +
				"WHERE recommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
	
				Recommendations recommendation = new Recommendations(recommendationId, userName, restaurantId); 
				return recommendation;
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
		return null;
		
	}
	
	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendation =
			"SELECT RecommendationId, UserName, RestaurantId " +
			"FROM Recommendations " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
	
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				int restaurantId = results.getInt("RestaurantId");

				Recommendations recommendation = new Recommendations(recommendationId, userName, restaurantId); 

				recommendations.add(recommendation);
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
		return recommendations;
		
	}
	public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendation =
			"SELECT RecommendationId, UserName, RestaurantId " +
			"FROM Recommendations " +
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
	
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				String userName = results.getString("UserName");

				Recommendations recommendation = new Recommendations(recommendationId, userName, restaurantId); 

				recommendations.add(recommendation);
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
		return recommendations;
		
	}
	public Recommendations delete(Recommendations recommendation) throws SQLException {
		
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();

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
