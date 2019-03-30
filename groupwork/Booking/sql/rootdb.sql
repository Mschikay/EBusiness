-- use `rootdb`;
-- CREATE USER 'ziqi'@'localhost' IDENTIFIED BY '123456';
-- grant all privileges on rootdb.* to 'ziqi'@'localhost';
-- flush privileges;

-- CREATE TABLE IF NOT EXISTS `location`(
--    `id` INT UNSIGNED AUTO_INCREMENT,
--    `city` VARCHAR(50),
--    PRIMARY KEY ( `id` ));
-- INSERT INTO location(`city`) VALUES ('Chicago');
-- select * from location;

-- create table if not exists `Hotel` (
-- 	`id` INT UNSIGNED AUTO_INCREMENT not null,
--     `name` varchar(100),
--     `addr` varchar(100),
--     `pic` varchar(200),
--     primary key (`id`),
--     CONSTRAINT name_addr UNIQUE (`name`,`addr`)
-- );
-- ALTER TABLE Hotel
-- ADD COLUMN total_people int NOT NULL

-- create table if not exists `Roomtype` (
-- 		`id` INT UNSIGNED AUTO_INCREMENT not null,
--         `name` varchar(20),
--         `available` int not null,
--         primary key (`id`)
-- );
-- ALTER TABLE Roomtype
-- ADD COLUMN url varchar(200)

-- create table if not exists `Room`(
--         `hotel_id` int unsigned not null,
--         `room_type` int unsigned,
--         `total` int not null,
--         `price` decimal(18, 2),
--         primary key (`hotel_id`, `room_type`),
--         constraint fk_Room_Type foreign key (room_type) references Roomtype(`id`),
--         constraint fk_Room_Hotel foreign key (hotel_id) references Hotel(`id`)
-- );
-- create table if not exists `Orders` (
-- 	`id` INT UNSIGNED AUTO_INCREMENT not null,
--     `card_id` char(10),
--     `when` datetime,
--     `hotel_id` int unsigned,
--     `checkin` date,
--     `checkout` date,
--     `room_type` INT unsigned,
--     `checkedin` boolean default false,
--     `chekedout` boolean default false,
--     primary key (`id`),
--     constraint fk_Room_Order FOREIGN KEY (hotel_id, room_type) REFERENCES Room(hotel_id, room_type)
-- );


-- drop procedure get_available_hotel
-- **********************************PROCEDURE FOR FIND AVAILABLE HOTELS**********************************
DELIMITER $$
CREATE PROCEDURE get_available_hotel(IN checkin_temp date, in checkout_temp date, in addr_temp varchar(100), in people_temp int)
BEGIN

select * from Hotel where id in 
	(select a. id from
	(select h.id as id from Hotel h
	where
		h.total_people > people_temp
		and
		h.addr like addr_temp)
	as a

	left join

	(select ordered.ordered_hotel_id as id from
		(select ord.hotel_id as ordered_hotel_id, sum(rt.available) as ordered_people from Orders as ord, Roomtype as rt
			where
				ord.hotel_id in (select h.id from Hotel as h where addr like addr_temp) 
				and
				ord.room_type = rt.id
				and 
				not (checkin_temp >= ord.checkout or checkout_temp <= ord.checkin)
			group by ordered_hotel_id) as ordered,
		Hotel h
		where 
			h.id = ordered.ordered_hotel_id 
			and 
			h.total_people - ordered.ordered_people < people_temp)
	as b

	on a.id = b.id
	where b.id is null);

END$$
DELIMITER ;

call get_available_hotel('20190328', '20190330', 'New York', 24)



-- **********************************PROCEDURE FOR FIND AVAILABLE ROOMS IN AVAILABLE HOTELS**********************************
DELIMITER $$
CREATE PROCEDURE get_available_roomtype(IN checkin_temp date, in checkout_temp date, in addr_temp varchar(100), in people_temp int)
BEGIN

select ord.hotel_id as ordered_hotel_id, rt.`name`, count(rt.`name`) as ordered_people from Orders as ord, Roomtype as rt
			where
				ord.room_type = rt.id
				and 
				not (checkin_temp >= ord.checkout or checkout_temp <= ord.checkin)
			group by ordered_hotel_id, rt.`name` 

END$$
DELIMITER ;

