create table members as select * from csvread('src/main/resources/members.csv');
create table books as select * from csvread('src/main/resources/books.csv');
create table borrowings as select * from csvread('src/main/resources/borrowings.csv');
