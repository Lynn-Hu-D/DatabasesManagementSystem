CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS RestaurantZipCode;

CREATE TABLE Restaurants (
	RestaurantName VARCHAR(255),
    Star VARCHAR(255),
    Stars_count INT,
    Price DECIMAL(10, 2),
    Area DECIMAL(10, 2),
    Category VARCHAR(255),
    Services VARCHAR(255),
    SearchedCity VARCHAR(255)
);


CREATE TABLE RestaurantZipCode (
    RestaurantName VARCHAR(255),
    Address VARCHAR(255),
    Zipcode VARCHAR(10),
    Mon VARCHAR(50),
    Tue VARCHAR(50),
    Wed VARCHAR(50),
    Thu VARCHAR(50),
    Fri VARCHAR(50),
    Sat VARCHAR(50),
    Sun VARCHAR(50)
);


LOAD DATA LOCAL INFILE "/Users/a123/Downloads/RestaurantsZipCode.csv" INTO TABLE RestaurantZipCode
  # Fields are not quoted.
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

  
LOAD DATA LOCAL INFILE "/Users/a123/Downloads/Restaurants.csv" INTO TABLE Restaurants
  # Fields are not quoted.
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;


CREATE TABLE NewRestaurantTable AS 
SELECT 
    ROW_NUMBER() OVER (ORDER BY R.Star) AS RestaurantID,
    Z.RestaurantName AS RestaurantName,
    R.Star AS Rating,
    Z.Address,
    R.Area,
    R.Category,
    R.Services AS Service,
    Z.ZipCode
FROM (
    SELECT DISTINCT RestaurantName, Address, ZipCode
    FROM RestaurantZipCode
) Z
INNER JOIN Restaurants R ON R.RestaurantName = Z.RestaurantName;


DROP TABLE IF EXISTS Crimes;
CREATE TABLE Crimes (
    CaseNumber VARCHAR(50),
    CreatedDateTime DATETIME,
    Address VARCHAR(255),
    ZipCode VARCHAR(10),
CONSTRAINT pk_Crimes_CaseNumber PRIMARY KEY (CaseNumber)
);

LOAD DATA LOCAL INFILE "/Users/a123/Downloads/Crimes.csv" INTO TABLE Crimes
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;





