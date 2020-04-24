drop table member;
create table member (
	userid		varchar2(20) primary key,
	userpw		varchar2(60) not null,
	username	varchar2(20) not null,
	jobid	    varchar2(20),
	hobby		varchar2(20),
	info		varchar2(500),
	gender		varchar2(1),
	regdate		date
);

insert into member(userid, userpw, username ) values ('admin','1234','°ü¸®ÀÚ');

commit;