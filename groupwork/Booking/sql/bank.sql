use `bank`;
-- INSERT INTO location(`city`) VALUES ('Chicago');
-- select * from location


-- create table if not exists `Card` (
-- 	`id` char(10)  not null,
--     `balance` decimal(18, 2),
--     `firstName` varchar(30),
--     `lastName` varchar(30),
--     `addr` varchar(50),
--     primary key (`id`)
-- )

create table if not exists `Record` (
	`card_id` char(10)  not null,
    `balance` decimal(18, 2),
    `when` datetime,
    constraint fk_Record_Card FOREIGN KEY (`card_id`) references Card(`id`)
)