CREATE SCHEMA IF NOT EXISTS SeattleTravelKit;
USE SeattleTravelKit;


DROP TABLE IF EXISTS HotelReviews;
DROP TABLE IF EXISTS Hotels;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS RestaurantReviews;
DROP TABLE IF EXISTS AttractionReviews;
DROP TABLE IF EXISTS Attractions;
DROP TABLE IF EXISTS Crimes;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;


CREATE TABLE Hotels (
    HotelId INT NOT NULL auto_increment,
    HotelName VARCHAR(255),
    Rating DECIMAL(3, 2),
    Website VARCHAR(255),
    Phone VARCHAR(20),
    Details TEXT,
    Address TEXT,
    PostalCode VARCHAR(20),
    City VARCHAR(255),
    CONSTRAINT pk_Hotels_HotelId PRIMARY KEY (HotelId)
);


  
  CREATE TABLE HotelReviews (
    ReviewId INT PRIMARY KEY,
    HotelId INT,
    Service FLOAT,
    Cleanliness FLOAT,
    Location FLOAT,
    SleepQuality FLOAT,
    CONSTRAINT fk_HotelReviews_HotelId FOREIGN KEY (HotelId)
		REFERENCES  Hotels (HotelId)
		ON UPDATE CASCADE ON DELETE CASCADE
);





CREATE TABLE Attractions (
    AttractionId INT AUTO_INCREMENT PRIMARY KEY,
    AttractionsName VARCHAR(255),
    Phone VARCHAR(50),
    Website VARCHAR(2048),
    PostalCode VARCHAR(20),
    Area VARCHAR(255)
);






CREATE TABLE Reviews (
    ReviewId INT PRIMARY KEY,
    UserName VARCHAR(255) NOT NULL,
    CreatedTime TIMESTAMP NOT NULL,
    Content TEXT NOT NULL,
    Rating DECIMAL(3, 1) NOT NULL
);



CREATE TABLE RestaurantReviews (
    reviewID INT PRIMARY KEY,
    RestaurantID INT NOT NULL,
    service DECIMAL(2,1) NOT NULL,
    food_quality DECIMAL(2,1) NOT NULL,
    Operation_time VARCHAR(11) NOT NULL
);


CREATE TABLE AttractionReviews (
    reviewID INT NOT NULL,
    AttractionID INT NOT NULL,
    duration VARCHAR(15) NOT NULL,
    PRIMARY KEY (reviewID, AttractionID)
);


CREATE TABLE Crimes (
    CaseNumber VARCHAR(50),
    CreatedDateTime DATETIME,
    Address VARCHAR(255),
    ZipCode VARCHAR(10),
   CONSTRAINT pk_Crimes_CaseNumber PRIMARY KEY (CaseNumber)
);


CREATE TABLE Restaurants (
    RestaurantID INT NOT NULL AUTO_INCREMENT,
    RestaurantName VARCHAR(255),
    Address TEXT,
    Rating DECIMAL(3, 2),
    Area VARCHAR(255),
    Category VARCHAR(255), -- Changed from ENUM to VARCHAR for broader compatibility
    Service TEXT,
    ZipCode VARCHAR(10),
    CONSTRAINT pk_Restaurants_RestaurantID PRIMARY KEY (RestaurantID)
);


CREATE TABLE IF NOT EXISTS Users (
    UserName VARCHAR(50) UNIQUE,
    Email VARCHAR(100) UNIQUE,
    Password VARCHAR(255),
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone VARCHAR(20),
    CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);



CREATE TABLE IF NOT EXISTS Address (
    UserName VARCHAR(255),
    City VARCHAR(255),
    Street1 VARCHAR(255),
    Street2 VARCHAR(255),
    State VARCHAR(255),
    ZipCode VARCHAR(20),
    Country VARCHAR(255),
    CONSTRAINT pk_Address_UserName PRIMARY KEY (UserName),
    CONSTRAINT fk_Address_UserName FOREIGN KEY (UserName)
        REFERENCES Users (UserName)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CreditCards (
   UserName VARCHAR(255),
    CardNumber BIGINT NOT NULL,
    Expiration VARCHAR(5),
    CONSTRAINT pk_CreditCard_CardNumber PRIMARY KEY (CardNumber),
    CONSTRAINT fk_CreditCard_UserName FOREIGN KEY (UserName)
        REFERENCES Users (UserName)
        ON UPDATE CASCADE ON DELETE CASCADE
);
