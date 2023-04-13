create database cyber_cafe1;
use cyber_cafe1;
create table customers(
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(255) not null,
    last_name varchar (255) NOT NULL,
    phone_num varchar(255) not null,
    email varchar(255) not null,
    number_people int not null,
    day int not null,
    month int not null,
    year int not null,
    time_of_day varchar(50) not null,
    message varchar(255),
    PRIMARY KEY (id)
);
insert into customers 
values(1,'Smith', 'Peter','0867662254',"petey@gmail.com",2,12,4,2023,"Breakfast",'Anniversary'),
(2,'David', 'Jones','0867755235',"davy.jones.locker@gmail.com",4,12,4,2023,"Breakfast",'Hangout'),
(3,'Murphy', 'Joe','0876875542',"joe.murphy@gmail.com",8,13,4,2023,"Lunch",'Birthday Party'),
(4,'Corcoran', 'Sheila', '0876889543',"sheila@yahoo.com",2,13,4,2023,"Lunch",'Date'),
(5,'Ryan','Ann', '0876856633',"ann@compuserve.net",5,13,4,2023,"Breakfast",'Hangout');
select * from customers;