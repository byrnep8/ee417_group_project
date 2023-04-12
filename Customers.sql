create database cyber_cafe1;
use cyber_cafe1;
create table customers(
    Id int NOT NULL AUTO_INCREMENT,
    FirstName varchar(255) not null,
    LastName varchar(255) NOT NULL,
    Phone_no varchar(255) not null,
    No_of_guests int not null,
    Day int not null,
    Month int not null,
    Year int not null,
    Time varchar(50) not null,
    Message varchar(255),
    PRIMARY KEY (Id)
);
insert into customers 
values(1,'Smith', 'Peter',0867662254,2,12,4,2023,1900,'Anniversary'),
(2,'David', 'Jones',0867755235,4,12,4,2023,1400,'Hangout'),
(3,'Murphy', 'Joe',0876875542,8,13,4,2023,2000,'Birthday Party'),
(4,'Corcoran', 'Sheila', 0876889543,2,13,4,2023,1500,'Date'),
(5,'Ryan','Ann', 0876856633,5,13,4,2023,2000,'Hangout');
select * from customers;