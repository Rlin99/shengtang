use db1;
SET SQL_SAFE_UPDATES = 0;

create table user(
  id int primary key auto_increment,
  uesrname varchar(50),
  password varchar(50)
);

create table product(
 id int primary key auto_increment,
 name varchar(50),
 description varchar(1024),
 price int
);

create table porder(
  id int auto_increment primary key,
  userId int,
  loginName varchar(50),
  userAddress varchar(50),
  createTime datetime,
  cost int
);
create table porder_detail(
  id int auto_increment primary key,
  porderId int,
  productId int,
  quantity int,
  cost int
);

insert into user values(null,'aaa','123');
insert into user values(null,'bbb','123');
insert into user values(null,'ccc','123');


insert into product values(null,'华为2566','','3999');
insert into product values(null,'华为3555','','2999');
insert into product values(null,'Af1-Jdi','','799');
insert into product values(null,'AJ11-黑红','','1199');
insert into product values(null,'香奈儿230','','799');
insert into product values(null,'爱马仕555','','8889');
