create database book_mh;

use book_mh;

create table books(
	bookId int AUTO_INCREMENT PRIMARY KEY,   
	title nvarchar(255) not null,
	author nvarchar(50) not null,
	price float,
	imgPath varchar(255)
);