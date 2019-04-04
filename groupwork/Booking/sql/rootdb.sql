use `rootdb`;
CREATE USER 'ziqi'@'localhost' IDENTIFIED BY '123456';
grant all privileges on rootdb.* to 'ziqi'@'localhost';
flush privileges;

CREATE TABLE IF NOT EXISTS `location`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `city` VARCHAR(50),
   PRIMARY KEY ( `id` ));
INSERT INTO location(`city`) VALUES ('Chicago');
select * from location;

create table if not exists `Hotel` (
	`id` INT UNSIGNED AUTO_INCREMENT not null,
    `name` varchar(100),
    `addr` varchar(100),
    `pic` varchar(200),
    primary key (`id`),
    CONSTRAINT name_addr UNIQUE (`name`,`addr`)
);
ALTER TABLE Hotel
ADD COLUMN total_people int NOT NULL;

create table if not exists `Roomtype` (
		`id` INT UNSIGNED AUTO_INCREMENT not null,
        `name` varchar(20),
        `available` int not null,
        primary key (`id`)
);
ALTER TABLE Roomtype
ADD COLUMN url varchar(200);

create table if not exists `Room`(
        `hotel_id` int unsigned not null,
        `room_type` int unsigned,
        `total` int not null,
        `price` decimal(18, 2),
        primary key (`hotel_id`, `room_type`),
        constraint fk_Room_Type foreign key (room_type) references Roomtype(`id`),
        constraint fk_Room_Hotel foreign key (hotel_id) references Hotel(`id`)
);
create table if not exists `Orders` (
	`id` INT UNSIGNED AUTO_INCREMENT not null,
    `card_id` char(10),
    `when` datetime,
    `hotel_id` int unsigned,
    `checkin` date,
    `checkout` date,
    `room_type` INT unsigned,
    `checkedin` boolean default false,
    `chekedout` boolean default false,
    primary key (`id`),
    constraint fk_Room_Order FOREIGN KEY (hotel_id, room_type) REFERENCES Room(hotel_id, room_type)
);
ALTER TABLE Orders
ADD COLUMN fname varchar(30);
ALTER TABLE Orders
ADD COLUMN lname varchar(30);


drop procedure get_available_hotel
**********************************PROCEDURE FOR FIND AVAILABLE HOTELS**********************************
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


drop procedure get_available_roomtype
-- **********************************PROCEDURE FOR FIND AVAILABLE ROOMS IN AVAILABLE HOTELS**********************************
DELIMITER $$
CREATE PROCEDURE get_available_roomtype(in hotelid_temp int, in checkin_temp date, in checkout_temp date)
BEGIN
select rt.`name`, (total_room.total - coalesce(ordered_room.ordered_room, 0)) as rest, total_room.price as price from
	((select r.room_type as total_room_type , r.total as total, r.price as price from Room r 
		where 
		r.hotel_id = hotelid_temp)
		as total_room
	left join 
	(select rt.`id` as ordered_room_type, count(rt.`id`) as ordered_room from Orders as ord, Roomtype as rt
				where
					ord.hotel_id = hotelid_temp
					and
					ord.room_type = rt.id
					and 
					not (checkin_temp >= ord.checkout or checkout_temp <= ord.checkin)
				group by rt.`id` )
				as ordered_room
	on total_room.total_room_type = ordered_room.ordered_room_type), Roomtype rt
    where rt.id = total_room.total_room_type;

END$$
DELIMITER ;

call get_available_roomtype(1, '20190328', '20190330');


drop procedure insertOrder
-- **********************************PROCEDURE FOR INSERT AN ORDER**********************************
DELIMITER $$
CREATE PROCEDURE insertOrder (in hotelid_temp int, in roomtype_temp int, in checkin_temp date, in checkout_temp date, in fname_temp varchar(30), in lname_temp varchar(30))
BEGIN
	insert into Orders(`when`, `hotel_id`, `room_type`, `checkin`, `checkout`, `fname`, `lname`) values
		(now(), hotelid_temp, roomtype_temp, checkin_temp, checkout_temp, fname_temp, lname_temp);
        
END$$
DELIMITER ;

call insertOrder(3, 3, '20190403', '20190404', 'Ziqi', 'Tang');
select * from Orders
