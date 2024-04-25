# Lingyu Hu

CREATE SCHEMA IF NOT EXISTS RestaurantApplication;
USE RestaurantApplication;

DROP TABLE IF EXISTS SitDown;
DROP TABLE IF EXISTS TakeOut;
DROP TABLE IF EXISTS FoodCart;
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS Recommendation;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS CardInformation;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Restaurant;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Users;



CREATE TABLE Address (
RestaurantID INT AUTO_INCREMENT PRIMARY KEY,
Street1 VARCHAR(255),
Street2 VARCHAR(255),
City VARCHAR(255),
State VARCHAR(255),
Zip INT
);

CREATE TABLE Company(
CompanyID INT AUTO_INCREMENT PRIMARY KEY,
CompanyName VARCHAR(255),
Descriptions VARCHAR(255)
);

CREATE TABLE CardInformation (
UserID INT PRIMARY KEY,
CardNumber BIGINT,
ExpirationDate TIMESTAMP
);



CREATE TABLE Users (
	UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(255) ,
    Passwords VARCHAR(255),
    FirstName VARCHAR(255) ,
    LastName VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    PhoneNumber INT,
    CONSTRAINT fk_CardInformation_UserID 
		FOREIGN KEY (UserID)
	    REFERENCES CardInformation (UserID)
	    ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Review_UserID 
		FOREIGN KEY (UserID) 
        REFERENCES Review (UserID)
        ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Recommendation_UserID 
		FOREIGN KEY (UserID) 
        REFERENCES Recommendation (UserID)
        ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Users_UserID 
		FOREIGN KEY (UserID) 
        REFERENCES Reservation (UserID)
        ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Restaurant (
    RestaurantID INT PRIMARY KEY AUTO_INCREMENT ,
    RestaurantName VARCHAR(255),
    Descriptions VARCHAR(255),
    Menu TEXT,
    Availability ENUM('OPEN', 'CLOSE'),
    CuisineType ENUM('African', 'American', 'Asian', 'European', 'Hispanic'),
    CompanyID INT,
    CONSTRAINT fk_Company_CompanyID FOREIGN KEY (CompanyID)
        REFERENCES  Company (CompanyID)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE SitDown (
    RestaurantID INT PRIMARY KEY,
    CuisineTypeID INT,
    Capacity INT,
	CONSTRAINT fk_SitDown_RestaurantID FOREIGN KEY (RestaurantID)
        REFERENCES  Restaurant (RestaurantID)
        ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE TakeOut (
    RestaurantID INT PRIMARY KEY,
	CuisineTypeID INT,
    MaxWaitTime TIME,
	CONSTRAINT fk_TakeOut_RestaurantID FOREIGN KEY (RestaurantID)
        REFERENCES  Restaurant (RestaurantID)
        ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE FoodCart (
    RestaurantID INT PRIMARY KEY NOT NULL,
	CuisineTypeID INT,
    Licensed BOOLEAN,
	CONSTRAINT fk_FoodCart_RestaurantID FOREIGN KEY (RestaurantID)
        REFERENCES  Restaurant (RestaurantID)
        ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Review (
    UserID INT AUTO_INCREMENT KEY,
    RestaurantID INT NOT NULL,
    CreatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ReviewContent VARCHAR(255),
    Rating FLOAT,
    CONSTRAINT fk_Review_RestaurantID 
		FOREIGN KEY (RestaurantID) 
		REFERENCES Restaurant(RestaurantID),
	CONSTRAINT uc_User_Restaurant UNIQUE (UserID, RestaurantID)
);

CREATE TABLE Recommendation (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    RestaurantID INT NOT NULL,
    CreatedTime TIMESTAMP,
	CONSTRAINT fk_Restaurant_RestaurantID 
		FOREIGN KEY (RestaurantID) 
		REFERENCES Restaurant(RestaurantID)
);

CREATE TABLE Reservation (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    RestaurantID INT NOT NULL,
    CreatedTime TIMESTAMP,
    StartTime TIMESTAMP,
    EndTime TIMESTAMP,
    PartySize INT,
	CONSTRAINT fk_Restaurant_RestaurantID FOREIGN KEY (RestaurantID) 
		REFERENCES Restaurant(RestaurantID)
);

