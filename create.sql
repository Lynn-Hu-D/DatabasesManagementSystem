

-- CREATE TABLE Records(
-- RecordId INT auto_increment PRIMARY KEY,
-- UserName VARCHAR(50),
-- Grade VARCHAR(255),
-- CurrentRank INT
-- );


CREATE TABLE Records(
UserName VARCHAR(50) ,
Grade VARCHAR(255),
CurrentRank INT,
PRIMARY KEY (UserName, CurrentRank)
);


-- INSERT INTO Records(UserName, Grade, CurrentRank) VALUES("hello", 90, 1);
-- INSERT INTO Records(UserName, Grade, CurrentRank) VALUES("WPRLD", 23, 10);
