package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import review.model.CreditCards;

public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	private static CreditCardsDao instance = null;
	

	public CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}

	

	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	
	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			
			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName());
			insertStmt.executeUpdate();
			
			return creditCard;
			
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
	
	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
		String selectCreditCard = "SELECT CardNumber, Expiration, UserName FROM CreditCards WHERE CardNumber = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareCall(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				Long resultCardNumber = results.getLong("CardNumber");
				Date resultExpiration = results.getDate("Expiration");
				String resultUserName = results.getString("UserName");
				
				CreditCards creditCard = new CreditCards(resultCardNumber, resultExpiration, resultUserName);
				return creditCard;
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
	
	
	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards = 
			"SELECT CardNumber, Expiration, UserName " +
			"FROM CreditCards " +
			"WHERE UserName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				Long cardNumber = results.getLong("CardNumber");
				Date expiration = results.getDate("Expiration");
				CreditCards creditCard = new CreditCards(cardNumber, expiration, userName);
				creditCards.add(creditCard);
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
		
		return creditCards;
	}
		
					
	
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		String updateCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?; ";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCard);
			
			updateStmt.setDate(1, (java.sql.Date) newExpiration);
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();
			
			creditCard.setExpiration(newExpiration);
			return creditCard;
				
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
		
	}
	
	public CreditCards delete(CreditCards creditCard) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCard);
			
			deleteStmt.setLong(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();
			
			return null;
		}catch (SQLException e) {
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
