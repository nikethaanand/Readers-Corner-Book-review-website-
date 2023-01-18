DROP SCHEMA IF EXISTS HW3_Restaurants;
CREATE SCHEMA HW3_Restaurants;
USE HW3_Restaurants;

CREATE TABLE Users (
  username VARCHAR(255),
  password VARCHAR(255),
  firstName VARCHAR(255),
  lastName VARCHAR(255),
  email VARCHAR(255),
  phoneNumber VARCHAR(30),
  CONSTRAINT pk_Users_username PRIMARY KEY (username)
);

CREATE TABLE CreditCards (
  cardNumber VARCHAR(16),
  expirationDate DATE,
  username VARCHAR(255),
  CONSTRAINT pk_CreditCards_cardNumber PRIMARY KEY (cardNumber),
  CONSTRAINT fk_CreditCards_username FOREIGN KEY (username)
    REFERENCES Users(username)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Companies (
  companyName VARCHAR(255),
  description TEXT,
  CONSTRAINT pk_Companies_companyName PRIMARY KEY (companyName)
);

CREATE TABLE Restaurants (
  restaurantId INT AUTO_INCREMENT,
  name VARCHAR(255),
  description TEXT,
  menu TEXT,
  hours TEXT,
  isActive BOOLEAN NOT NULL,
  street1 VARCHAR(255),
  street2 VARCHAR(255),
  city VARCHAR(255),
  state CHAR(2),
  zip CHAR(5),
  cuisineType ENUM('african', 'american', 'asian', 'european', 'hispanic'),
  companyName VARCHAR(255),
  CONSTRAINT pk_Restaurants_reaturantId PRIMARY KEY (restaurantId),
  CONSTRAINT fk_Restaurants_companyName FOREIGN KEY (companyName)
    REFERENCES Companies(companyName)
    ON UPDATE CASCADE
    ON DELETE SET NULL    
);

create table SitDownRestaurants (
  restaurantId INT,
  capacity int,
  CONSTRAINT pk_SitDownRestaurants_restaurantId PRIMARY KEY (restaurantId),
  CONSTRAINT fk_SitDownRestaurants_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES Restaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

create table TakeOutRestaurants (
  restaurantId INT,
  maxWaitTime TIME,
  CONSTRAINT pk_TakeOutRestaurants_restaurantId PRIMARY KEY (restaurantId),
  CONSTRAINT fk_TakeOutRestaurants_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES Restaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

create table FoodCartRestaurants (
  restaurantId INT,
  isLicensed BOOLEAN,
  CONSTRAINT pk_FoodCartRestaurants_restaurantId PRIMARY KEY (restaurantId),
  CONSTRAINT fk_FoodCartRestaurants_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES Restaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

create table Recommendations (
  recommendationId int AUTO_INCREMENT,
  username VARCHAR(255),
  restaurantId INT,
  CONSTRAINT pk_Recommendations_recommendationId PRIMARY KEY (recommendationId),
  CONSTRAINT fk_Recommendations_username FOREIGN KEY (username)
    REFERENCES Users(username)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
  CONSTRAINT fk_Recommendations_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES Restaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
  CONSTRAINT uq_Recommendations_username_restaurantId
    UNIQUE (username, restaurantId)
);

create table Reviews (
  reviewId int AUTO_INCREMENT,
  username varchar(255),
  restaurantId INT,
  created timestamp default current_timestamp,
  review text,
  rating decimal(2,1),
  CONSTRAINT pk_Reviews_reviewId PRIMARY KEY (reviewId),
  CONSTRAINT fk_Reviews_username FOREIGN KEY (username)
    REFERENCES Users(username)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES Restaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

create table Reservations (
  reservationId int AUTO_INCREMENT,
  username varchar(255),
  restaurantId INT,
  startTime timestamp,
  endTime timestamp,
  partySize int,
  CONSTRAINT pk_Reservations_reservationId PRIMARY KEY (reservationId),
  CONSTRAINT fk_Reservations_username FOREIGN KEY (username)
    REFERENCES Users(username)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT fk_Reservations_restaurantId FOREIGN KEY (restaurantId)
    REFERENCES SitDownRestaurants(restaurantId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


USE HW3_Restaurants;

INSERT INTO Users (username)
  VALUES ('user1'),
         ('user2'),
         ('user3'),
         ('user4'),
         ('user5');

INSERT INTO Restaurants (restaurantId, name, isActive)
  VALUES (1, 'a', true),
         (2, 'b', false),
         (3, 'c', true),
         (4, 'd', true),
         (5, 'e', true),
         (6, 'f', true),
         (7, 'g', true),
         (8, 'h', true),
         (9, 'i', true),
         (10, 'j', true),
         (11, 'k', false),
         (12, 'l', true),
         (13, 'm', true);

INSERT INTO SitDownRestaurants (restaurantId, capacity)
  VALUES (1, 48), (2, 56), (3, 60), (4, 12), (5, 20);

INSERT INTO TakeOutRestaurants (restaurantId, maxWaitTime)
  VALUES (6, '00:30'), (7, '00:15'), (8, '00:20'), (9, '00:15');

INSERT INTO FoodCartRestaurants (restaurantId, isLicensed)
  VALUES (10, true), (11, false), (12, true), (13, true);

INSERT INTO Reviews (username, restaurantId, rating)
  VALUES ('user1', 1, 3.5),
         ('user1', 2, 5.0),
         ('user1', 5, 0.5),
         ('user2', 7, 1.3),
         ('user3', 3, 4.8),
         ('user3', 12, 2.6);

INSERT INTO Reservations (username, restaurantId, startTime, endTime, partySize)
  VALUES ('user1', 1, '2022-10-15 18:00', '2022-10-15 20:00', 3),
         ('user1', 1, '2022-10-22 18:30', '2022-10-22 20:00', 4),
         ('user1', 2, '2022-10-23 18:00', '2022-10-23 20:00', 8),
         ('user1', 2, '2022-10-24 18:00', '2022-10-24 20:00', 8),
         ('user2', 1, '2022-10-20 18:45', '2022-10-20 22:00', 6),
         ('user2', 2, '2022-10-19 18:00', '2022-10-19 19:00', 3),
         ('user3', 1, '2022-10-21 19:00', '2022-10-21 22:00', 2),
         ('user3', 4, '2022-10-21 19:00', '2022-10-21 22:00', 4);

INSERT INTO CreditCards (cardNumber, username)
  VALUES ('345612341234', 'user1'),
         ('348912341234', 'user2'),
         ('6011876587658765', 'user1'),
         ('6443123412341234', 'user3'),
         ('6400000000000000', 'user3'),
         ('5200000000000000', 'user2'),
         ('4000000000000000', 'user3');

INSERT INTO Recommendations (username, restaurantId)
  VALUES ('user1', 2),
         ('user3', 3),
         ('user3', 6),
         ('user3', 8),
         ('user4', 10);
         
--  1   
 SELECT Restaurants.restaurantId,Restaurants.name,AVG(Reviews.rating) FROM Restaurants INNER JOIN Reviews ON 
 Reviews.restaurantId = Restaurants.restaurantId GROUP BY Restaurants.restaurantId LIMIT 10;   
 
 -- 2
 SELECT COUNT(*) FROM (SELECT COUNT(ReviewId) C FROM Reviews GROUP BY username HAVING COUNT(ReviewId) > 1)TEMP;  
 
 -- 3

 SELECT DAYOFWEEK(StartTime) AS MOST_POPULAR_DAY FROM Reservations GROUP BY DAYOFWEEK(StartTime) LIMIT 1; 
 
-- 4
SELECT * FROM Reservations;
SELECT DISTINCT(UserName) FROM Reservations INNER JOIN SitDownRestaurants ON SitDownRestaurants.RestaurantId = Reservations.RestaurantId 
GROUP BY Reservations.UserName HAVING COUNT(ReservationID) >1;

-- 5
SELECT COUNT(cardNumber),'American Express' AS CARDPROVIDER FROM CreditCards WHERE  cardNumber LIKE '34%' OR cardNumber LIKE '37%'  UNION
SELECT COUNT(cardNumber),'DISCOVER' AS CARDPROVIDER FROM CreditCards WHERE  cardNumber LIKE '6011%'OR cardNumber LIKE "644%" OR cardNumber LIKE '645%' OR cardNumber LIKE '646%' 
OR cardNumber LIKE '647%' OR cardNumber LIKE '648%' OR cardNumber LIKE '649%' OR cardNumber LIKE '65%' UNION

SELECT COUNT(cardNumber),'MasterCard' AS CARDPROVIDER FROM CreditCards WHERE cardNumber LIKE "51%" OR cardNumber LIKE "52%" OR cardNumber LIKE "53%" OR cardNumber LIKE "54%" 
OR cardNumber LIKE "55%"  UNION

SELECT COUNT(cardNumber),'Visa' AS CARDPROVIDER FROM CreditCards WHERE cardNumber LIKE '4%'  UNION

SELECT COUNT(cardNumber),'NA' AS CARDPROVIDER FROM CreditCards WHERE NOT cardNumber LIKE '34%' AND NOT cardNumber LIKE '37%' AND NOT cardNumber LIKE '6011%' AND NOT cardNumber LIKE '644%'
AND NOT cardNumber LIKE '645%' AND NOT cardNumber LIKE '646%' AND NOT cardNumber LIKE '647%' AND NOT cardNumber LIKE '648%' AND NOT cardNumber LIKE '649%' AND NOT cardNumber LIKE '65%'
AND NOT cardNumber LIKE '51%' AND NOT cardNumber LIKE '52%' AND NOT cardNumber LIKE '53%' AND NOT cardNumber LIKE '54%' AND NOT cardNumber LIKE '55%' AND NOT cardNumber LIKE '4%';

-- 6
SELECT COUNT(Restaurants.restaurantId),'Sitdown Restaurant' AS  restaurantType FROM Restaurants,SitDownRestaurants WHERE Restaurants.restaurantId = SitDownRestaurants.restaurantId AND isActive = true union
SELECT COUNT(Restaurants.restaurantId),'Take out restaurant' AS  restaurantType FROM Restaurants,TakeOutRestaurants WHERE Restaurants.restaurantId = TakeOutRestaurants.restaurantId AND isActive = true union
SELECT COUNT(Restaurants.restaurantId),'Food cart Restaurant ' AS  restaurantType FROM Restaurants,FoodCartRestaurants WHERE Restaurants.restaurantId = FoodCartRestaurants.restaurantId AND isActive = true;

-- 7 
SELECT username FROM Users WHERE username NOT IN (SELECT username FROM Reviews) AND
username NOT IN (SELECT username FROM Recommendations) AND
username NOT IN (SELECT username FROM Reservations);

-- 8
SELECT ( SELECT COUNT(*) FROM Recommendations) / (SELECT COUNT(*) FROM Reviews) ;

-- 9 

SELECT Restaurants.name, ROUND(percentage,2)
	FROM Restaurants
    JOIN(
	SELECT temp.restaurantId, temp.total, temp2.recs, (temp2.recs/temp.total*100) as percentage
		FROM (SELECT restaurantId, COUNT(username) AS total
		FROM Reservations
		GROUP BY RestaurantId) temp
		LEFT JOIN (SELECT restaurantID, COUNT(username) as recs
		FROM (SELECT restaurantID, username
		FROM Recommendations) as recommendation
		WHERE username in (select username from Reservations)
		GROUP BY restaurantId) temp2
		ON temp.restaurantId = temp2.restaurantId) as percent
        ON Restaurants.restaurantId = percent.restaurantId;
        
-- 10
SELECT temp1.username FROM (SELECT username, count(username) as recommmendationCount FROM Recommendations  Group By username) as temp1
LEFT JOIN (SELECT username, count(username) as reviewCount FROM Reviews  Group By username) as temp2 ON temp1.username = temp2.username
WHERE recommmendationCount > reviewCount;

