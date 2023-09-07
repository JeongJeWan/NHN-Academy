
CREATE DATABASE 정제완_Board;

use 정제완_Board;

create table Users (
	User_id integer primary key,
	User_name varchar(20) not null,
    Role_name varchar(10) not null,
    Created_at timestamp
);

create table Board (
	Board_id integer primary key,
    Board_name varchar(20) not null
);


create table Refly (
	Refly_id integer primary key,
    User_id integer,
    Comment_id integer,
    body text not null
);

create table Posts (
	Post_id integer primary key,
    User_id integer,
    Board_id integer,
    Category_id integer,
    Title varchar(20) not null,
    Body text not null,
    Careted_time timestamp
);

create table Comments (
	Comment_id integer primary key,
    Post_id integer,
    User_id integer,
    body text not null,
    created_at timestamp
);

create table Category (
	category_id integer primary key,
    post_id integer	
);

alter table Refly add constraint fk_Refly_Users foreign key(User_id) references Users(User_id) on delete cascade;
alter table Refly add constraint fk_Refly_Comments foreign key(Comment_id) references Comments(Comment_id) on delete cascade;

alter table Posts add constraint fk_Posts_Users foreign key(User_id) references Users(User_id) on delete cascade;
alter table Posts add constraint fk_Posts_Board foreign key(Board_id) references Board(Board_id) on delete cascade;
alter table Posts add constraint fk_Posts_Category foreign key(Category_id) references Category(Category_id) on delete cascade;

alter table Comments add constraint fk_Commnets_Posts foreign key(Post_id) references Posts(Post_id) on delete cascade;
alter table Comments add constraint fk_Commnets_Users foreign key(User_id) references Users(User_id) on delete cascade;


desc Refly;


