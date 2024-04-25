package review.model;

import java.time.LocalDateTime;

public class Reservations {
	protected int reservationId;
	protected LocalDateTime start;
	protected LocalDateTime end;
	protected int size;
	protected String userName;
	protected int restaurantId;
	
	/**
	 * @param reservationId
	 * @param start
	 * @param end
	 * @param size
	 * @param userName
	 * @param restaurantId
	 */
	public Reservations(int reservationId, LocalDateTime start, LocalDateTime end, int size, String userName,
			int restaurantId) {
		this.reservationId = reservationId;
		this.start = start;
		this.end = end;
		this.size = size;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	/**
	 * @param start
	 * @param end
	 * @param size
	 * @param userName
	 * @param restaurantId
	 */
	public Reservations(LocalDateTime start, LocalDateTime end, int size, String userName, int restaurantId) {
		this.start = start;
		this.end = end;
		this.size = size;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the reservationId
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * @return the start
	 */
	public LocalDateTime getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public LocalDateTime getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Reservations [reservationId=" + reservationId + ", start=" + start + ", end=" + end + ", size=" + size
				+ ", userName=" + userName + ", restaurantId=" + restaurantId + "]";
	}
	
	

}
