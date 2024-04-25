package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import review.model.Reservations;


public class ReservationsDao {
	protected ConnectionManager connectionManager;
	private static ReservationsDao instance = null;
	
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	
	public Reservations create(Reservations reservation) throws SQLException {
		String insertReview =
				"INSERT INTO Reservations(Start, End, Size, UserName, RestaurantId) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setTimestamp(1, Timestamp.valueOf(reservation.getStart()));
				insertStmt.setTimestamp(2, Timestamp.valueOf(reservation.getEnd()));
				insertStmt.setInt(3, reservation.getSize());
				insertStmt.setString(4, reservation.getUserName());
				insertStmt.setInt(5, reservation.getRestaurantId());
	
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int reservationId = -1;
				if(resultKey.next()) {
					reservationId = resultKey.getInt(1);
					reservation.setReservationId(reservationId);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}

				return reservation;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} 
	}
	
	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectReservation =
				"SELECT ReservationId,Start, End, Size, UserName, RestaurantId " +
				"FROM Reservations " +
				"WHERE reservationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setInt(1, reservationId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
				LocalDateTime end =  results.getTimestamp("End").toLocalDateTime();
				int size = results.getInt("Size");
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
	
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, restaurantId); 
				return reservation;
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
	
	public List<Reservations> getReservationsByUserName(String userName) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservation =
				"SELECT ReservationId,Start, End, Size, UserName, RestaurantId " +
				"FROM Reservations " +
				"WHERE userName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int reservationId = results.getInt("ReservationId");
				LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
				LocalDateTime end =  results.getTimestamp("End").toLocalDateTime();
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");
				
	
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, restaurantId); 
				reservations.add(reservation);
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
		return reservations;
	}
	
	public List<Reservations> getReservationsBySitDownRestaurantId(int sitDownRestaurantId) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservation =
				"SELECT Reservations.ReservationId,Start, End, Size, UserName, Reservations.RestaurantId AS RestaurantId " +
				"FROM Reservations INNER JOIN SitDownRestaurant " +
				"ON Reservations.restaurantId = SitDownRestaurant.restaurantId " +
				"WHERE Reservations.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setInt(1, sitDownRestaurantId);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int reservationId = results.getInt("ReservationId");
				LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
				LocalDateTime end =  results.getTimestamp("End").toLocalDateTime();
				int size = results.getInt("Size");
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, restaurantId); 
				reservations.add(reservation);
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
		return reservations;
	}
	
	public Reservations delete(Reservations reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
