-- DROP DATABASE Certificate;
CREATE DATABASE Certificate;
Use Certificate;

CREATE TABLE People(
	Person_name varchar(20) not null,
    Person_residentNum varchar(300) primary key,
    Person_gender enum('남','여') not null,
    Person_birth DATETIME,
    Person_place varchar(20),
    Person_familyOrigin varchar(100),
    Person_DeathDt DATETIME,
    Person_DeathPlace varchar(20),
    Person_DeathAddress varchar(100)
);

CREATE TABLE Family_Relation(
	Person_residentNum varchar(30),
    Family_residentNum varchar(30),
    Relation_type_id integer,
	constraint pk_FamilyRelation primary key(Person_residentNum, Family_residentNum)
);

CREATE TABLE Relation_type(
	Relation_type_id integer primary key,
    Relation_type_name varchar(20)
);


CREATE TABLE Generation_Host(
    Person_residentNum varchar(30),
    HouseHolder_residentNum varchar(30),
	constraint pk_FamilyRelation primary key(Person_residentNum, HouseHolder_residentNum)
);

CREATE TABLE Generation_Member_Relation(
	HouseHolder_residentNum varchar(30),
    Person_residentNum varchar(30),
    Register_date date,
    Reason varchar(30),
    Relation_type_id integer,
    constraint pk_GenerationMember primary key(HouseHolder_residentNum, Person_residentNum)
);

CREATE TABLE Address_info (
	Address_info  varchar(100),
	Person_residentNum varchar(30),
	Address_UpdateDt date,
    constraint pk_Address primary key(Address_info, Person_residentNum)
);

-- 증명성 발급
CREATE TABLE Certificate_Issuance (
	Certificate_Issiance_id integer,
    Person_residentNum varchar(300),
    Certificate_name varchar(30),
    Isuua_date date,
    Certificate_checkNum varchar(30),
    constraint pk_Certificate_Issuance primary key(Certificate_Issiance_id, Person_residentNum)
);

-- 신고인
CREATE TABLE Declaration_Person (
	Person_residentNum varchar(300),
    Declaration_personName varchar(30),
    Declaration_personResidentNum varchar(100),
    Declaration_personEmail varchar(30),
    Declaration_personPhone varchar(30),
    Declaration_date date,
    Qualification_typeId INT, 
    Declaration_typeId INT,
    constraint pk_Declaration_Qualification primary key(Person_residentNum, Qualification_typeId, Declaration_typeId)
);

-- 신고인 자격
CREATE TABLE Qualification_Type (
	Qualification_typeId INT primary key,
    Qualification_typeName varchar(30)
);

-- 사망자 신고서 타입
CREATE TABLE Declaration_Type (
	Declaration_typeId INT primary key,
    Declaration_typeName varchar(30)
);



Insert into Address_info values('경기도 성남시 분당구 대왕판교로 645번','790510-*******','2013-03-05');
Insert into Address_info values('경기도 성남시 분당구 불정로 90번길','790510-*******','2009-10-31');
Insert into Address_info values('서울시 동작구 상도로 940번길 ','790510-*******','2007-10-31');

Insert into Generation_Host values('820821-*******','790510-*******');
Insert into Generation_Host values('120315-*******','790510-*******');
Insert into Generation_Host values('851205-*******','790510-*******');
Insert into Generation_Host values('790510-*******','790510-*******');

Insert into Generation_Member_Relation values('790510-*******', '120315-*******','20120317','출생등록',4);
Insert into Generation_Member_Relation values('790510-*******', '790510-*******','20091002','세대분리',0);
	-- 세대주 남기준,
INSERT INTO Generation_Member_Relation values ('790510-*******', '820821-*******', '20100215', '전입', 3);
Insert into Generation_Member_Relation values ('790510-*******', '851205-*******', '20151129', '전입', 5);

Insert into Relation_type values(0,"본인");
Insert into Relation_type values(1,"부");
Insert into Relation_type values(2,"모");
Insert into Relation_type values(3,"배우자");
Insert into Relation_type values(4,"자녀");
Insert into Relation_type values(5,"동거인");

Insert into Family_Relation values('790510-*******','790510-*******',0);
Insert into Family_Relation values('790510-*******','540514-*******',1);
Insert into Family_Relation values('790510-*******','551022-*******',2);
Insert into Family_Relation values('790510-*******','120315-*******',4);
Insert into Family_Relation values('790510-*******','820821-*******',3);
Insert into Family_Relation values('551022-*******','540514-*******',3);
Insert into Family_Relation values('551022-*******','790510-*******',4);
Insert into Family_Relation values('820821-*******','790510-*******',3);
Insert into Family_Relation values('820821-*******','120315-*******',4);
Insert into Family_Relation values('120315-*******','790510-*******',1);
Insert into Family_Relation values('120315-*******','820821-*******',2);
-- 남석환 가족
INSERT INTO Family_Relation values("540514-*******","130914-*******",1);
INSERT INTO Family_Relation values("540514-*******","551022-*******",3);
INSERT INTO Family_Relation values("540514-*******","120315-*******",4);
-- 남길동 가족
INSERT INTO Family_Relation values("130914-*******","540514-*******",3);

Insert into People values('이주은','820821-*******','여','19820821012800','병원','경기도 수원시 팔달구 효원로 1번길',null,null,null);
Insert into People values('이선미','851205-*******','여','19851205220100','병원','경기도 수원시 팔달구 효원로 1번길',null,null,null);
Insert into People values('남기석','120315-*******','남','20120315145900','병원','경기도 성남시 분당구 대왕판교로645번길',null,null,null);
INSERT INTO People values("남길동", "130914-*******", "남",'19130914072200', "자택", "경기도 성남시 분당구 대왕판교로645번길", "20210429090300", "주택", "강원도 고성군 금강산로 290번길");
INSERT INTO People values("남석환", "540514-*******", "남", "19540514173000", "병원", "경기도 성남시 분당구 대왕판교로645번길", null, null, null);
Insert into People values ('박한나', '551022-*******', '여', '19551022111500', '병원', '서울특별시 중구 세종대로 110번길', null, null, null);
Insert into People values ('남기준', '790510-*******', '남', '19790510204500', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);

Insert into Qualification_Type values(1,'부');
Insert into Qualification_Type values(2,'모');
Insert into Qualification_Type values(3,'호주');
Insert into Qualification_Type values(4,'동거친족');
Insert into Qualification_Type values(5,'비동거친족');
Insert into Qualification_Type values(6,'동거자');
Insert into Qualification_Type values(7,'기타');

Insert into Declaration_Type values(0,'출생신고서');
Insert into Declaration_Type values(1,'사망신고서');

-- 출생신고서
Insert into Declaration_Person values ('120315-*******', '남기준', '790510-*******', 'nam@nhnad.co.kr', '010-1234-5678', '20120317', 1, 0);
-- 사망신고서
Insert into Declaration_Person values ('130914-*******', '남석환', '540514-*******', null, '010-1234-5678', '20200502',5, 1);

Insert into Certificate_Issuance values(0,'790510-*******','가족관계증명서','2021-10-25','12345678-91011121');
Insert into Certificate_Issuance values(1,'790510-*******','주민등록등본','2021-10-25','198765432-10987654');

Alter table Family_Relation add constraint fk_FamilyRelation_People foreign key(Person_residentNum) references People(Person_residentNum);
Alter table Family_Relation add constraint fk_FamilyRelation_Family foreign key(Family_residentNum) references People(Person_residentNum);
Alter table Family_Relation add constraint fk_FamilyRelation_Type foreign key(Relation_type_id) references Relation_type(Relation_type_id);

Alter table Generation_Host add constraint fk_Host_People foreign key(Person_residentNum) references People(Person_residentNum);
Alter table Generation_Host add constraint fk_Host_Member foreign key(HouseHolder_residentNum) references Generation_Member_Relation(HouseHolder_residentNum);

Alter table Generation_Member_Relation add constraint fk_Member_Type foreign key(Relation_type_id) references Relation_type(Relation_type_id);

-- 증명서 발급 참조키 주민
ALTER TABLE Certificate_Issuance add constraint fk_Certificate_Person foreign key (Person_residentNum) references People(Person_residentNum);

ALTER table Declaration_Person add constraint fk_Declatation_Person foreign key (Person_residentNum) references People(Person_residentNum);

ALTER table Address_Info add constraint fk_Address_Person foreign key (Person_residentNum) references People(Person_residentNum);

-- 가족관계증명서 
SELECT p.Person_name,Certificate_name, Isuua_date, Certificate_checkNum
From People as p join Certificate_Issuance as c on p.person_residentNum = c.Person_residentNum
Where p.person_residentNum = '790510-*******' and Certificate_checkNum = '12345678-91011121';

SELECT Person_familyOrigin
From People
where Person_residentNum = '790510-*******';

SELECT rt.Relation_type_name,p.Person_name,p.Person_birth,p.Person_ResidentNum,p.Person_gender
FROM People p
INNER JOIN Family_Relation fr ON fr.Family_residentNum = p.Person_residentNum
inner join Relation_type as rt on fr.Relation_type_id = rt.Relation_type_id
WHERE fr.Person_residentNum = '790510-*******'
Order by rt.Relation_type_id asc;

-- 주민등록등본
SELECT p.Person_name,Certificate_name, Isuua_date, Certificate_checkNum
From People as p join Certificate_Issuance as c on p.person_residentNum = c.Person_residentNum
Where p.person_residentNum = '790510-*******' and Certificate_checkNum = '198765432-10987654';

SELECT *
From Address_info
Where Person_residentNum = (SELECT g.HouseHolder_residentNum
From People as p inner join Generation_Host as g on p.Person_residentNum = g.Person_residentNum
where g.Person_residentNum = '790510-*******'
);

SELECT rt.Relation_type_Name, p.Person_name, p.Person_residentNum, gmr.Register_date, gmr.Reason
FROM People p
INNER JOIN Generation_Member_Relation gmr ON p.Person_residentNum = gmr.Person_residentNum
INNER JOIN Generation_Host gh ON gh.HouseHolder_residentNum = gmr.HouseHolder_residentNum
INNER JOIN Relation_type rt ON rt.Relation_type_Id = gmr.Relation_type_Id
WHERE gh.Person_residentNum = '790510-*******'
Order by gmr.Register_date asc;

-- 출생 신고서
SELECT rt.Relation_type_name,p.Person_name,p.Person_birth,p.Person_ResidentNum,p.Person_gender
FROM People p
INNER JOIN Family_Relation fr ON fr.Family_residentNum = p.Person_residentNum
inner join Relation_type as rt on fr.Relation_type_id = rt.Relation_type_id
WHERE fr.Person_residentNum = '120315-*******';

SELECT Person_name, Person_gender, Person_birth, Person_place, Person_familyOrigin
FROM People
Where Person_residentNum = '120315-*******';

SELECT Declaration_date,Declaration_personName, Declaration_personResidentNum, Qualification_typeName, Declaration_personEmail,Declaration_personPhone
FROM Declaration_Person as dp join Declaration_Type as dt on dp.Declaration_TypeId = dt.Declaration_TypeId
	inner join Qualification_Type as qt on qt.Qualification_typeId = dp.Qualification_typeId
Where Person_residentNum = '120315-*******' and dp.Declaration_typeId = 0;

-- 사망 신고서
SELECT Person_name, Person_residentNum, Person_DeathDt, Person_DeathPlace, Person_DeathAddress
FROM People
where Person_residentNum = '130914-*******';


SELECT Declaration_date, Declaration_personName, Declaration_personResidentNum, Qualification_typeName, Declaration_personEmail,Declaration_personPhone
FROM Declaration_Person as dp join Declaration_Type as dt on dp.Declaration_TypeId = dt.Declaration_TypeId
	inner join Qualification_Type as qt on qt.Qualification_typeId = dp.Qualification_typeId
Where Person_residentNum = '130914-*******' and dp.Declaration_typeId = 1;






