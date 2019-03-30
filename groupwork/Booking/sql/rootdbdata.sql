use `rootdb`;

insert into Hotel (`name`, `addr`) values ('Drury Plaza Hotel', 'Pittsburgh');
insert into Hotel (`name`, `addr`) values ('Hampton Inn GreenTree', 'Pittsburgh');
insert into Hotel (`name`, `addr`) values ('The Jewel Facing Rockefeller Center', 'NewYork');
insert into Hotel (`name`, `addr`) values ('The Standard, High Line New York', 'NewYork');

insert into Roomtype(`name`, `available`) values('single', 1);
insert into Roomtype(`name`, `available`) values('double', 2);
insert into Roomtype(`name`, `available`) values('triple', 3);
insert into Roomtype(`name`, `available`) values('quad', 4);
insert into Roomtype(`name`, `available`) values('master_suite', 4);
insert into Roomtype(`name`, `available`) values('mini_suite', 2);
insert into Roomtype(`name`, `available`) values('studio', 1);

insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (1, 1, 20, 150.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (1, 2, 20, 200.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (1, 3, 10, 270.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (1, 4, 10, 300.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (2, 1, 20, 180.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (2, 2, 20, 260.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (2, 5, 5, 700.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (2, 6, 5, 600.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (2, 7, 5, 300.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (3, 1, 20, 300.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (3, 2, 20, 400.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (3, 3, 20, 450.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (4, 1, 5, 450.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (4, 2, 5, 570.00);
insert into Room (`hotel_id`, `room_type`, `total`, `price`) values (4, 5, 2, 800.00);

insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (1, 1, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`hotel_id`, `room_type`, `checkin`, `checkout`) values (3, 2, '20190327', '20190330');
insert into Orders(`when`, `hotel_id`, `room_type`, `checkin`, `checkout`) values (now(), 3, 3, '20190327', '20190330');
insert into Orders(`when`, `hotel_id`, `room_type`, `checkin`, `checkout`) values (now(), 3, 3, '20190325', '20190326');

update Hotel set total_people = 130 where id=1;
update Hotel set total_people = 95 where id=2;
update Hotel set addr = 'New York' where id=3;
update Hotel set addr = 'New York' where id=4;

