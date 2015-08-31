drop database if exists guestbook;

create database guestbook;

use guestbook;

create table messages
(
id int not null auto_increment primary key,
author varchar(100) not null,
content varchar(500) not null
);