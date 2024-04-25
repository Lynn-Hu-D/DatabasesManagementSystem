# PART 2

CREATE SCHEMA IF NOT EXISTS BankApplication;
USE BankApplication;

DROP TABLE IF EXISTS AccountHolder;
DROP TABLE IF EXISTS AccountType;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Savings;
DROP TABLE IF EXISTS Checking;

CREATE TABLE AccountHolder(
AccountHolderID INT AUTO_INCREMENT NOT NULL,
FirstName VarChar(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Email VARCHAR(50) NOT NULL,
Phone INT NOT NULL,
Dob DATE NOT NULL,
CONSTRAINT uq_AccountHolder UNIQUE (firstName, lastName, dob),
CONSTRAINT pk_AccountHolder_acountHolder_id PRIMARY KEY (accountHolderId)
);

CREATE TABLE AccountType(
accountType VARCHAR(50) PRIMARY KEY  NOT NULL
);

INSERT INTO AccountType (accountType) 
VALUES ("DEBIT"), ("CREDIT"), ("CORPORATE"),("OVER_DRAFT");

CREATE TABLE Account (
number VARCHAR(50) NOT NULL,
accountHolderID INT NOT NULL,
Type VARCHAR(50) NOT NULL,
Expiration DATE NOT NULL,
Security VARCHAR(50),
Routing VARCHAR(50),
Bank VARCHAR(50),
CONSTRAINT pk_Account_number PRIMARY KEY (number),
CONSTRAINT fk_AccountType_accountType FOREIGN KEY (Type) 
	REFERENCES AccountType(accountType)
    ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_Account_accountHolderID FOREIGN KEY (accountHolderID)
	REFERENCES AccountHolder (accountHolderID)
    ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE Savings (
number VARCHAR(50) NOT NULL,
maximumWithdraw DOUBLE(7, 2) NOT NULL,
CONSTRAINT pk_Savings_number PRIMARY KEY (number),
CONSTRAINT fk_Savings_number FOREIGN KEY (number)
	REFERENCES Account (number)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Checking (
number VARCHAR(50) NOT NULL,
monthlyFee Double(7, 2) NOT NULL,
CONSTRAINT pk_Checking_number PRIMARY KEY (number),
CONSTRAINT FK_Checking_number FOREIGN KEY (number)
	REFERENCES Account(number)
    ON UPDATE CASCADE ON DELETE CASCADE
);


# PART 3
CREATE TABLE Sponsor (
    username  VARCHAR (50) NOT NULL,
    CONSTRAINT pk_Sponsor PRIMARY KEY (username)
);

CREATE TABLE Sponsored (
    username  VARCHAR (50) NOT NULL,
    CONSTRAINT pk_Sponsored PRIMARY KEY (username)
);

CREATE TABLE Donation (
    sponsorUsername VARCHAR (50),
    sponsoredUsername VARCHAR (50),
    amount double,
    date Date NULL,
    frequency VARCHAR (50),
    CONSTRAINT fk_Donation_sponsorUserName FOREIGN KEY (sponsorUsername)
      REFERENCES Sponsor(username),
    CONSTRAINT fk_Donation_sponsoredUserName FOREIGN KEY (sponsoredUsername)
      REFERENCES Sponsored(username),
    CONSTRAINT fk_Donation_frequency FOREIGN KEY (frequency)
      REFERENCES FrequencyType(type)
);

CREATE TABLE FrequencyType (
    type VARCHAR (50),
    CONSTRAINT pk_FrequencyType PRIMARY KEY (type)
);

INSERT INTO FrequencyType (type) VALUES (‘ONE_TIME’);
INSERT INTO FrequencyType (type) VALUES (‘DAILY’);
INSERT INTO FrequencyType (type) VALUES (‘WEEKLY’);
INSERT INTO FrequencyType (type) VALUES (‘MONTHLY’);
INSERT INTO FrequencyType (type) VALUES (‘YEARLY’);

CREATE TABLE Friend (
    sponsorUsername VARCHAR (50),
    sponsoredUsername VARCHAR (50),
    CONSTRAINT pk_Friend PRIMARY KEY (sponsorUsername, sponsoredUsername),
    CONSTRAINT fk_Friend_sponsorUsername FOREIGN KEY (sponsorUsername)
      REFERENCES Sponsor(username),
    CONSTRAINT fk_Friend_sponsoredUsername FOREIGN KEY (sponsoredUsername)
      REFERENCES Sponsored(username)
);

INSERT INTO Donation(sponserName, sponseredName, amount, data, frequency)
VALUES ("tom", "alice", "10/12/2017", 25.00, "ONE_TIME");

INSERT INTO Donation(sponserName, sponseredName, amount, data, frequency)
values ("bob", "edward", 5.00, DATE.Now(), "MONTHLY");

DELETE FROM Friend
WHERE (sponserName = "charlie" AND sponseredName = "dan")
	OR(sponseredName = "dan" AND sponserName = "charlie");
    
UPDATE Donation
SET amount = 10.00, frequency = "WEEKLY"
Where d.sponserName = "bob" AND d.sponseredName = "edward" 
	AND amount = 5.00 AND frequency = "MONTHLY";
    
SELECT frequency, COUNT(*) AS CNT
FROM Donation
GROUP BY frequency 
ORDER BY CNT DESC
LIMIT 1;
    
SELECT sponserName, COUNT(*) AS CNT
FROM Friend
GROUP BY sponsorName
ORDER BY CNT DESC
LIMIT 10;


SELECT sponseredName, COUNT(*) AS CNT
FROM Donation
WHERE frequency = "ONE_TIME"
GROUP BY sponseredName
ORDER BY CNT DESC
LIMIT 10;


SELECT sponserName, SUM(amount) AS DONATION_TOTAL
FROM Donation
WHERE frequency = "DAILY" AND YEAR(date) = YEAR(curdate())
GROUP BY sponserName
HAVING SUM(amount) > 1000.00





    
