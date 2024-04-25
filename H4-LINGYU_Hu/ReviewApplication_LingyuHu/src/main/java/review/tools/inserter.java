package review.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import review.dal.CompaniesDao;
import review.dal.CreditCardsDao;
import review.dal.FoodCartRestaurantDao;
import review.dal.RecommendationsDao;
import review.dal.ReservationsDao;
import review.dal.RestaurantsDao;
import review.dal.ReviewsDao;
import review.dal.SitDownRestaurantDao;
import review.dal.TakeOutRestaurantDao;
import review.dal.UsersDao;
import review.model.Companies;
import review.model.CreditCards;
import review.model.FoodCartRestaurant;
import review.model.Recommendations;
import review.model.Reservations;
import review.model.Restaurants;
import review.model.Restaurants.CuisineType;
import review.model.Reviews;
import review.model.SitDownRestaurant;
import review.model.TakeOutRestaurant;
import review.model.Users;

public class inserter {
	public static void main(String[] args) throws SQLException {
		// DAO INSTANCES
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		CompaniesDao companiesDao = CompaniesDao.getInstance();
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
		TakeOutRestaurantDao takeOutRestaurantDao = TakeOutRestaurantDao.getInstance();
		FoodCartRestaurantDao foodCartRestaurantDao = FoodCartRestaurantDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();

		
		// create
		Users user1 = new Users("a1", "123", "Ashly1" , "D1" , "ashly.d1@gmail.com");
		Users user2 = new Users("a2", "123", "Ashly2" , "D2" , "ashly.d2@gmail.com");
		Users user3 = new Users("a3", "123", "Ashly3" , "D3" , "ashly.d3@gmail.com");
		user1 = usersDao.create(user1);
		user2 = usersDao.create(user2);
		user3 = usersDao.create(user3);
		
		
		CreditCards creditCard1 = new CreditCards(123456789, Date.valueOf("2028-08-08"), "a1");
		CreditCards creditCard2 = new CreditCards(234567891, Date.valueOf("2045-02-08"), "a2");
		CreditCards creditCard3 = new CreditCards(345678912, Date.valueOf("2025-01-01"), "a3");
		CreditCards creditCard4 = new CreditCards(345678911, Date.valueOf("2025-06-01"), "a1");
		creditCard1 = creditCardsDao.create(creditCard1);
		creditCard2 = creditCardsDao.create(creditCard2);
		creditCard3 = creditCardsDao.create(creditCard3);
		creditCard4 = creditCardsDao.create(creditCard4);
		
		Companies company1 = new Companies("company1", "First company");
		Companies company2 = new Companies("company2", "Second company");
		Companies company3 = new Companies("company3", "Third company");
		company1 = companiesDao.create(company1);
		company2 = companiesDao.create(company2);
		company3 = companiesDao.create(company3);
		
		Restaurants restaurant1 = new Restaurants("r1", "No. 1 Restaurant", "Pasta, Noodle, Rice", "11:00 AM - 08:00 PM", 
				true, CuisineType.EUROPEAN, "101TH", "1st AVE", "Seattle", "WA", 98109, company1.getCompanyName());
		Restaurants restaurant2 = new Restaurants("r2", "No. 2 Restaurant", "Burger, Fries, Coke", "11:00 AM - 12:00 PM", 
				true, CuisineType.AMERICAN, "111TH", "2nd AVE", "Seattle", "WA", 98109, company2.getCompanyName());
		Restaurants restaurant3 = new Restaurants("r3", "No. 3 Restaurant", "Steak, Seafood", "01:00 PM - 06:00 PM", 
				true, CuisineType.HISPANIC, "112TH", "3rd AVE", "Seattle", "WA", 98109, company3.getCompanyName());
		restaurant1 = restaurantsDao.create(restaurant1);
		restaurant2 = restaurantsDao.create(restaurant2);
		restaurant3 = restaurantsDao.create(restaurant3);
	
		
		SitDownRestaurant sitDownRestaurant1 = new SitDownRestaurant("sitDownRestaurant1", "No. 3 Restaurant", "Steak, Seafood", "01:00 PM - 06:00 PM", 
				true, CuisineType.HISPANIC, "112TH", "3rd AVE", "Seattle", "WA", 98109, company3.getCompanyName(), 4); 
		TakeOutRestaurant takeOutRestaurant1 = new TakeOutRestaurant("takeOutRestaurant1", "No. 2 Restaurant", "Burger, Fries, Coke", "11:00 AM - 12:00 PM", 
				true, CuisineType.AMERICAN, "111TH", "2nd AVE", "Seattle", "WA", 98109, company2.getCompanyName(), 15);
		FoodCartRestaurant foodCartRestaurant1 = new FoodCartRestaurant("foodCartRestaurant1", "No. 1 Restaurant", "Pasta, Noodle, Rice", "11:00 AM - 08:00 PM", 
				true, CuisineType.EUROPEAN, "101TH", "1st AVE", "Seattle", "WA", 98109, company1.getCompanyName(), true);
		sitDownRestaurant1 = sitDownRestaurantDao.create(sitDownRestaurant1);
		takeOutRestaurant1 = takeOutRestaurantDao.create(takeOutRestaurant1);
		foodCartRestaurant1 = foodCartRestaurantDao.create(foodCartRestaurant1);
		
		Reviews review1 = new Reviews(new Date(2024-03-01), "Best restaurant", 4.8, user1.getUserName(), restaurant1.getRestaurantId());
		Reviews review2 = new Reviews(new Date(2024-01-25), "Fast but waiting too long", 3.5, user2.getUserName(), restaurant1.getRestaurantId());
		Reviews review3 = new Reviews(new Date(2022-06-24), "Tasty and expensive", 4.2, user2.getUserName(), restaurant3.getRestaurantId());
		review1 = reviewsDao.create(review1);
		review2 = reviewsDao.create(review2);
		review3 = reviewsDao.create(review3);
		
		Recommendations recommendation1 = new Recommendations(user1.getUserName(), restaurant1.getRestaurantId());
		Recommendations recommendation2 = new Recommendations(user2.getUserName(), restaurant1.getRestaurantId());
		Recommendations recommendation3 = new Recommendations(user1.getUserName(), restaurant3.getRestaurantId());
		recommendation1 = recommendationsDao.create(recommendation1);
		recommendation2 = recommendationsDao.create(recommendation2);
		recommendation3 = recommendationsDao.create(recommendation3);
		
		Reservations reservation1 = new Reservations(LocalDateTime.of(2024, 3, 15, 19, 0), LocalDateTime.of(2024, 3, 15, 21, 0), 2, 
				user1.getUserName(),restaurant1.getRestaurantId());
		Reservations reservation2 = new Reservations(LocalDateTime.of(2024, 3, 24, 12, 0), LocalDateTime.of(2024, 3, 24, 12, 30), 1, 
				user3.getUserName(),restaurant2.getRestaurantId());
		Reservations reservation3 = new Reservations(LocalDateTime.of(2024, 3, 26, 20, 0), LocalDateTime.of(2024, 3, 26, 22, 0), 4, 
				user3.getUserName(),sitDownRestaurant1.getRestaurantId());
		reservation1 = reservationsDao.create(reservation1);
		reservation2 = reservationsDao.create(reservation2);
		reservation3 = reservationsDao.create(reservation3);
		
		
		// read
		System.out.println(" ");
		System.out.println("Start Reading.......");
		Users u1 = usersDao.getUserByUserName("a1");
		System.out.format("Reading user with userName(a1): FirstName:%s LastName:%s Email:%s \n", u1.getFirstName(), u1.getLastName(), u1.getEmail());
		
		CreditCards c1 = creditCardsDao.getCreditCardByCardNumber(creditCard1.getCardNumber());
		System.out.format("Card %d belongs to user %s \n", creditCard1.getCardNumber(), c1.getUserName());
		List<CreditCards> creditCards = creditCardsDao.getCreditCardsByUserName(creditCard1.getUserName());
		System.out.format("User %s has the following credit cards: \n", creditCard1.getUserName());
		for (CreditCards c : creditCards) {
			System.out.format("        CardNumber  %d, Expiration %s \n", c.getCardNumber(), c.getExpiration().toString());
		}
		
		Companies cm1 = companiesDao.getCompanyByCompanyName(company1.getCompanyName());
		System.out.format("The company with the name %s is %s \n", company1.getCompanyName(), cm1.toString());
	
		Restaurants r1 = restaurantsDao.getRestaurantById(restaurant1.getRestaurantId());
		System.out.format("The restaurant with ID %d is %s \n", restaurant1.getRestaurantId(), r1.toString());
		List<Restaurants> restaurants = restaurantsDao.getRestaurantsByCuisine(restaurant1.getCuisineType());
		System.out.format("The %s restaurants are the following: \n", restaurant1.getCuisineType());
		for (Restaurants r : restaurants) {
			System.out.format("        Restaurant %s \n", r.toString());
		}
		List<Restaurants> restaurants1 = restaurantsDao.getRestaurantsByCompanyName(restaurant1.getCompanyName());
		System.out.format("Company %s has the following restaurants: \n", restaurant1.getCompanyName());
		for (Restaurants r : restaurants1) {
			System.out.format("        Restaurant %s \n", r.toString());
		}
		
		SitDownRestaurant t1 = sitDownRestaurantDao.getSitDownRestaurantById(sitDownRestaurant1.getRestaurantId());
		System.out.format("The SitDownRestaurant %d is %s \n", sitDownRestaurant1.getRestaurantId(), t1.toString());
		List<SitDownRestaurant> sitDownRestaurants = sitDownRestaurantDao.getSitDownRestaurantsByCompanyName(company3.getCompanyName());
		System.out.format("Company %s has the following SitDownRestaurants: \n", company3.getCompanyName());
		for (SitDownRestaurant r : sitDownRestaurants) {
			System.out.format("        SitDownRestaurant %s \n", r.toString());
		}

		TakeOutRestaurant to1 = takeOutRestaurantDao.getTakeOutRestaurantById(takeOutRestaurant1.getRestaurantId());
		System.out.format("The TakeOutRestaurant %d is %s \n", takeOutRestaurant1.getRestaurantId(), to1.toString());
		List<TakeOutRestaurant> takeOutRestaurants = takeOutRestaurantDao.getTakeOutRestaurantsByCompanyName(company2.getCompanyName());
		System.out.format("Company %s has the following TakeOutRestaurant: \n", company3.getCompanyName());
		for (TakeOutRestaurant r : takeOutRestaurants) {
			System.out.format("        TakeOutRestaurant %s \n", r.toString());
		}
		
		FoodCartRestaurant fc1 = foodCartRestaurantDao.getFoodCartRestaurantById(foodCartRestaurant1.getRestaurantId());
		System.out.format("The TakeOutRestaurant %d is %s \n", foodCartRestaurant1.getRestaurantId(), fc1.toString());
		List<FoodCartRestaurant> foodCartRestaurants = foodCartRestaurantDao.getFoodCartRestaurantsByCompanyName(company2.getCompanyName());
		System.out.format("Company %s has the following FoodCartRestaurant: \n", company3.getCompanyName());
		for (FoodCartRestaurant r : foodCartRestaurants) {
			System.out.format("        FoodCartRestaurant %s \n", r.toString());
		}
				

		
		Reviews rw1 = reviewsDao.getReviewById(review1.getReviewId());
		System.out.format("Review %d is %s \n", review1.getReviewId(), rw1.toString());
		List<Reviews> reviews = reviewsDao.getReviewsByUserName(user1.getUserName());
		System.out.format("User %s has the following reviews: \n", user1.getUserName());
		for (Reviews r : reviews) {
			System.out.format("        Reviews %s \n", r.toString());
		}
		List<Reviews> reviews1 = reviewsDao.getReviewsByRestaurantId(restaurant1.getRestaurantId());
		System.out.format("Restaruants %s has the following reviews: \n", restaurant1.getRestaurantId());
		for (Reviews r : reviews1) {
			System.out.format("        Reviews %s \n", r.toString());
		}
		
		Recommendations rcmd1 = recommendationsDao.getRecommendationById(recommendation1.getRecommendationId());
		System.out.format("Recommendations %d is %s \n", recommendation1.getRecommendationId(), rcmd1.toString());
		List<Recommendations> recommendations = recommendationsDao.getRecommendationsByUserName(user1.getUserName());
		System.out.format("User %s has the following rommendations: \n", user1.getUserName());
		for (Recommendations r : recommendations) {
			System.out.format("        Recommendations %s \n", r.toString());
		}
		List<Recommendations> recommendations1 = recommendationsDao.getRecommendationsByRestaurantId(restaurant1.getRestaurantId());
		System.out.format("Restaruants %s has the following recommendations: \n", restaurant1.getRestaurantId());
		for (Recommendations r : recommendations1) {
			System.out.format("        Recommendations %s \n", r.toString());
		}
		
		Reservations rs1 = reservationsDao.getReservationById(reservation1.getReservationId());
		System.out.format("Reservations %d is %s \n", reservation1.getReservationId(), rs1.toString());
		List<Reservations> reservations = reservationsDao.getReservationsByUserName(user3.getUserName());
		System.out.format("User %s has the following reservations: \n", user3.getUserName());
		for (Reservations r : reservations) {
			System.out.format("        Reservations %s \n", r.toString());
		}
		List<Reservations> reservations1 = reservationsDao.getReservationsBySitDownRestaurantId(sitDownRestaurant1.getRestaurantId());
		System.out.format("SitDownRestaruants %s has the following reservations: \n", sitDownRestaurant1.getRestaurantId());
		for (Reservations r : reservations1) {
			System.out.format("        Reservations %s \n", r.toString());
		}
		
		
		
		
		// update
		System.out.println(" ");
		System.out.println("Start Updating.......");
		Date newDate = Date.valueOf("2024-12-08");
		CreditCards c2 = creditCardsDao.updateExpiration(creditCard4, newDate);
		
		System.out.format("The expiration date for card %d has been updated from %s to %s \n",creditCard4.getCardNumber(), creditCard4.getExpiration().toString(), c2.getExpiration().toString());
		Companies cm2 = companiesDao.updateAbout(company2, "Updated second company");
		System.out.format("Before updated: %s;After updated: %s \n", company2.toString(), cm2.toString());
		
		
		
		// delete
		System.out.println("");
		System.out.println("Start Deleting.......");
		Users u2 = usersDao.delete(user1);
		System.out.format("Users %s has been deleted, it becomes %s \n", user1.getUserName(), u2);
		
		CreditCards c3 = creditCardsDao.delete(creditCard4);
		System.out.format("CreditCards %d has been deleted, it becomes %s \n", creditCard4.getCardNumber(), c3);
		
		Companies cm3 = companiesDao.delete(company3);
		System.out.format("Companies %s has been deleted, it becomes %s \n", company3.toString(), cm3);
		
		Restaurants r2 = restaurantsDao.delete(restaurant3);
		System.out.format("Restaurants %s has been deleted, it becomes %s \n", restaurant3.toString(), r2);

		Reviews rw2 = reviewsDao.delete(review1);
		System.out.format("Reviews %s has been deleted, it becomes %s \n", review1.toString(), rw2);

		Recommendations rcmd2 = recommendationsDao.delete(recommendation1);
		System.out.format("Recommendations %s has been deleted, it becomes %s \n", recommendation1.toString(), rcmd2);
		
		Reservations rs2 = reservationsDao.delete(reservation1);
		System.out.format("Reservations %s has been deleted, it becomes %s \n", reservation1.toString(), rs2);

		
		
	}
	

}
