DROP SCHEMA IF EXISTS BookReview;
CREATE SCHEMA BookReview;
USE BookReview;

DROP TABLE IF EXISTS PUBLISHERS;
DROP TABLE IF EXISTS ADMINS;
DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS REVIEWS;
DROP TABLE IF EXISTS RECOMMENDATIONS;
DROP TABLE IF EXISTS BOOKCOLLECTIONS;
DROP TABLE IF EXISTS READLIST;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS(
userName VARCHAR(255),
password VARCHAR(255),
CONSTRAINT pk_USERS_userName PRIMARY KEY(userName),
CONSTRAINT uk_USERS_userName UNIQUE KEY(userName)
);


CREATE TABLE BOOKS(
title VARCHAR(500),
description text,
authors TEXT,
publisher varchar(255),
categories varchar(500),
publishedDate DATE,
ratingsCount int,
CONSTRAINT pk_BOOKS_title PRIMARY KEY (title)
);


CREATE TABLE PUBLISHERS(
userName VARCHAR(255),
lastLogin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_PUBLISHERS_userName PRIMARY KEY(userName),
CONSTRAINT fk_PUBLISHERS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE ADMINS(
userName VARCHAR(255),
createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_ADMINS_userName PRIMARY KEY(userName),
CONSTRAINT fk_ADMINS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
);


CREATE TABLE RECOMMENDATIONS(
recommendationId INT AUTO_INCREMENT,
title varchar(255),
userName varchar(255),
content VARCHAR(255),
created timestamp DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_RECOMMENDATIONS_recommendationId PRIMARY KEY(recommendationId),
CONSTRAINT fk_RECOMMENDATIONS_title FOREIGN KEY (title) REFERENCES BOOKS(title),
CONSTRAINT fk_RECOMMENDATIONS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE REVIEWS(
reviewId INT NOT NULL AUTO_INCREMENT,
title varchar(255),
userName VARCHAR(255),
score INT,
shortSummary TEXT,
summary TEXT,
helpfulness INT,
CONSTRAINT pk_REVIEWS_reviewId PRIMARY KEY(reviewId),
CONSTRAINT fk_REVIEWS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE COMMENTS(
commentId INT AUTO_INCREMENT,
recommendationId INT,
userName varchar(255),
comment varchar(255),
CONSTRAINT pk_COMMENTS_commentId PRIMARY KEY(commentId),
CONSTRAINT fk_COMMENTS_recommendationId FOREIGN KEY (recommendationId) REFERENCES RECOMMENDATIONS(recommendationId) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_COMMENTS_userName FOREIGN KEY (userName) REFERENCES USERS(userName)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE READLIST(
readListId INT auto_increment,
userName varchar(255),
title varchar(255),
CONSTRAINT pk_READLIST_readListId PRIMARY KEY (readListId),
CONSTRAINT fk_READLIST_userName FOREIGN KEY (userName) REFERENCES
Users(userName) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_READLIST_title FOREIGN KEY (title) REFERENCES
BOOKS(title) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE COLLECTIONS(
collectionId integer AUTO_INCREMENT,
collectionName varchar(255),
userName varchar(255),
CONSTRAINT pk_BOOKCOLLECTIONS_collectionId PRIMARY KEY (collectionId),
CONSTRAINT fk_BOOKCOLLECTIONS_userName FOREIGN KEY (userName) REFERENCES
Users(userName) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE BOOKCOLLECTIONS(
bookcollectionId integer AUTO_INCREMENT,
collectionId int,
title varchar(255),
CONSTRAINT pk_BOOKCOLLECTIONS_bookcollectionId PRIMARY KEY (bookcollectionId),
CONSTRAINT fk_BOOKCOLLECTIONS_title FOREIGN KEY (title) REFERENCES
BOOKS(title) ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_BOOKCOLLECTIONS_collectionId FOREIGN KEY (collectionId) REFERENCES
COLLECTIONS(collectionId) ON UPDATE CASCADE ON DELETE SET NULL
);





LOAD DATA  INFILE '/tmp/BookDataRemovedDuplicates.csv' INTO TABLE BOOKS
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;



