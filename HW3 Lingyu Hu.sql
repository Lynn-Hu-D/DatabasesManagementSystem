# Lingyu Hu

USE ReviewApplication;

/*
Q1:
What are the top 10 average review ratings for restaurants? 
Include the restaurant ID, restaurant name, and average rating in the result set.
*/
SELECT r.RestaurantId, r.Name AS RestaurantName, AVG(re.rating) AS average_rating
FROM Restaurants r INNER JOIN Reviews re ON r.RestaurantId = re.RestaurantId
GROUP BY r.RestaurantId, r.Name
ORDER BY average_rating DESC
LIMIT 10;


# Q2: How many users have created more than one review
SELECT COUNT(*) AS user_with_multi_reviews
FROM (
	SELECT UserName
    FROM Reviews
    GROUP BY UserName
    HAVING COUNT(*) > 1
) AS reviews;


/*
Q3:
What day of the week is most popular for making a reservation? Use the DAYOFWEEK() function on the ‘Start’ column.
*/
SELECT DAYOFWEEK(Reservations.Start) AS most_popular_day, COUNT(*) AS reservation_count
FROM Reservations
GROUP BY DAYOFWEEK(Reservations.Start)
ORDER BY reservation_count DESC
LIMIT 1;

/*
Q4: 
What are the distinct UserNames for users that have made multiple reservations at any given SitDownRestaurant? 
*/

SELECT DISTINCT UserName
FROM Reservations 
INNER JOIN SitDownRestaurant ON Reservations.RestaurantId = SitDownRestaurant.RestaurantId
WHERE SitDownRestaurant.RestaurantId IN (
	SELECT RestaurantId
    FROM Reservations
    GROUP BY RestaurantId
    HAVING COUNT(Distinct UserName) > 1
)
GROUP BY UserName;


/*
Q5:
Identify the number of credit cards per provider. The credit card provider is determined by the leading digit(s) of the CardNumber. 
Use this mapping for card providers:
Provider	CardNumber Starts With
American Express	34, 37
Discover	6011, 644, 645, 646, 647, 648, 649, 65
MasterCard	51, 52, 53, 54, 55
Visa	4
NA	(Everything else)
*/

SELECT 
	CASE 
		WHEN SUBSTRING(CardNumber, 1, 2) IN ("34", "37") THEN "AMERICAN EXPRESS"
        WHEN SUBSTRING(CardNumber, 1, 4) IN ("6011", "644", "645", "646", "647", "648", "649", "65") THEN "DISCOVER"
        WHEN SUBSTRING(CardNumber, 1, 2) IN ("51", "52", "53", "54", "55") THEN "MASTERCARD"
        WHEN SUBSTRING(CardNumber, 1, 1) = "4" THEN "VISA"
        ELSE "NA"
	END AS Provider,
    COUNT(*) AS count_per_provider    
FROM CreditCards
GROUP BY Provider;


/*
Q6:
What is the total number of active restaurants for each type of 
restaurant (SitDownRestaurant, TakeOutRestaurant, FoodCartRestaurant)?
*/
SELECT 
	SUM(CASE WHEN s.RestaurantId IS NOT NULL THEN 1 ELSE 0 END) AS SitDownRestaurant_count,
    SUM(CASE WHEN t.RestaurantId IS NOT NULL THEN 1 ELSE 0 END) AS TakeOutRestaurant_count,
    SUM(CASE WHEN f.RestaurantId IS NOT NULL THEN 1 ELSE 0 END) AS FoodCartRestaurant_count
FROM Restaurants r 
INNER JOIN SitDownRestaurant s ON r.RestaurantId = s.RestaurantId
INNER JOIN TakeOutRestaurant t ON r.RestaurantId = t.RestaurantId
INNER JOIN FoodCartRestaurant f ON r.RestaurantId = f.RestaurantId
WHERE r.Active = 1;

    
/*
Q7:
Which UserNames have not created a review, nor created a recommendation, nor created a reservation? 
In other words, users that have not created any of the following: reviews, recommendations, reservations.
*/
SELECT DISTINCT u.UserName
From Users u
LEFT JOIN Reviews r ON u.UserName = r.UserName
LEFT JOIN Recommendations rec ON u.UserName = rec.UserName
LEFT JOIN Reservations res ON u.UserName = res.UserName
WHERE r.UserName IS NULL
	AND rec.UserName IS NULL
    AND res.UserName IS NULL;


# Q8: What is the ratio of the total number of recommendations to the total number of reviews?

SELECT
(SELECT COUNT(*) FROM Recommendations) / (SELECT COUNT(*) Reviews) AS recommendation_review_ratio;

/*
Q9:
Of all the users that have created a reservation at a SitDownRestaurant, 
what is the percentage that the same user has recommended the same SitDownRestaurant? 
To calculate this percentage, consider {UserName, RestaurantId} reservation tuples as the denominator 
and their intersecting {UserName, RestaurantId} recommendation tuples at the numerator.
*/

SELECT 
	((SELECT COUNT(DISTINCT res.UserName, res.RestaurantId)
		FROM Reservations res 
		INNER JOIN SitDownRestaurant s ON s.RestaurantId = res.RestaurantId) /
	 (SELECT COUNT(DISTINCT res.UserName, res.RestaurantId)
		FROM Reservations res
        INNER JOIN SitDownRestaurant s ON res.RestaurantId = s.RestaurantId
        WHERE EXISTS (
			SELECT 1
            FROM Recommendations rec
            WHERE rec.UserName = res.UserName
				AND rec.RestaurantId = res.RestaurantId))) * 100 AS percentage;
        

/*
Q10:
Which UserNames have created more than twice the number of recommendations than number of reviews? 
Also take into account users that have not created recommendations or reviews -- if a user has created one (or more) recommendation 
but zero reviews, then that user should be included in the result set.
Note: if you want to go above and beyond on the data analysis, 
then consider if we flipped the comparison so that we instead asked: “created more than twice the number of reviews 
than number of recommendations?” What would change in the query?
*/
SELECT rec_count.UserName
FROM (
	SELECT rec.UserName, COUNT(*) AS recommendation_count
    FROM Recommendations rec
    GROUP BY rec.UserName) AS rec_count
LEFT JOIN (
	SELECT rev.UserName, COUNT(*) AS review_count
    FROM Reviews rev
    GROUP BY rev.UserName) AS rev_count
ON rec_count.UserName = rev_count.UserName 
WHERE (recommendation_count > 2 * review_count OR review_count IS NULL);







