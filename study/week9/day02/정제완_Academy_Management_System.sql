-- DROP DATABASE 정제완_Academy_Management_System;
CREATE DATABASE 정제완_Academy_Management_System;
use 정제완_Academy_Management_System;
-- 학과
CREATE TABLE Departments (
	Department_Id INT primary key,
    Office_Id INT,
    Department_Name varchar(50),
    Department_Established_YEAER YEAR
);

-- 교수
CREATE TABLE Professors (
	Professor_Id INT primary key,
    Office_Id INT,
    Professor_Name varchar(50),
    Professor_email varchar(50),
    Professor_Major varchar(50),
    Professor_Phone varchar(20),
    Department_Id INT
);

-- 학생
CREATE TABLE Students(
	Student_Id INT primary key,
    Student_Name varchar(50),
    Studnet_Major varchar(50),
    Student_Gender enum('M', 'F'),
    Student_Phone varchar(20),
    Student_Email varchar(50),
    Student_Birth_Year YEAR,
    Student_Birth_Place varchar(50),
    Student_Parent_Name varchar(30),
    Department_Id INT
);

-- 지도 교수
CREATE TABLE Advisor (
    Student_Id INT,
    Professor_Id INT,
    constraint pk_Advisor_Stu_Pro primary key (Student_Id, Professor_Id)
);

-- 강의실
CREATE TABLE Classroom (
	Classroom_Id INT,
    Building_Id INT,
    classroom_Name varchar(50),
    classroom_Location varchar(50),
    
    constraint pk_Clssroom_Building primary key (Classroom_Id, Building_Id)
);

-- 강의
CREATE TABLE Lectures (
	Lecture_Id INT,
    Lecture_Name varchar(50),
    Lecture_Description TEXT,
    Professor_Id INT,
    Subject_Id INT,
    Classroom_Id INT,
     
    constraint pk_Lectures_Pro primary key (Lecture_Id, Subject_Id, Professor_Id)
);

-- 과목
CREATE TABLE Subjects (
	Subject_Id INT primary key,
    Subject_Name varchar(50),
    Subject_Credit varchar(5)
);

-- 수강
CREATE TABLE Courses (
	Courses_Id INT,
	Student_Id INT,
    Subject_Id INT,
    Courses_Date date,
    Grade varchar(3),
    WithDarw ENUM('Y', 'N'),
    
    constraint pk_Courses_Stu_Lec primary key (Courses_Id, Student_Id, Subject_Id)
);

-- 사무실
CREATE TABLE Offices (
	Office_Id INT,
    Building_Id INT,
    Office_Name varchar(50),
    Office_Location varchar(50),
    
    constraint pf_Offices_Building primary key (Office_Id, Building_Id)
);

-- 직원
CREATE TABLE Employee (
	Employee_Id INT primary key,
    Employee_Name varchar(30),
    Office_Id INT
);

-- 근무
CREATE TABLE Works (
    Employee_Id INT primary key,
    Office_Id INT,
    Work_Start_Date Date,
    Work_End_Date Date
);

-- 건물
CREATE TABLE Building (
	Building_Id INT primary key,
    Building_Name varchar(30),
    Building_location varchar(5)
);

ALTER TABLE Advisor add constraint fk_Advisor_Students foreign key (Student_Id) references Students(Student_Id) on delete cascade;
ALTER TABLE Advisor add constraint fk_Advisor_Professors foreign key (Professor_Id) references Professors(Professor_Id) on delete cascade;

ALTER TABLE Professors add constraint fk_Professor_Departments foreign key (Department_Id) references Departments(Department_Id) on delete cascade;

ALTER TABLE Students add constraint fk_Students_Departments foreign key (Department_Id) references Departments(Department_Id) on delete cascade;

ALTER TABLE Lectures add constraint fk_Lectures_Classroom foreign key (Classroom_Id) references Classroom(Classroom_Id);
ALTER TABLE Lectures add constraint fk_Lectures_Professor foreign key (Professor_Id) references Professors(Professor_Id);
ALTER TABLE Lectures add constraint fk_Lectures_Subjects foreign key (Subject_Id) references Subjects(Subject_Id);

ALTER TABLE Courses add constraint fk_Courses_Students foreign key (Student_Id) references Students(Student_Id);
ALTER TABLE Courses add constraint fk_Courses_Lecture foreign key (Subject_Id) references Subjects(Subject_Id);

ALTER TABLE Departments add constraint fk_Departments_Office foreign key (Office_Id) references Offices(Office_Id);
ALTER TABLE Professors add constraint fk_Professors_Office foreign key (Office_Id) references Offices(Office_Id);
 
ALTER TABLE Offices add constraint fk_Offices_Building foreign key (Building_Id) references Building(Building_Id);
ALTER TABLE Classroom add constraint fk_Classroom_Building foreign key (Building_Id) references Building(Building_Id);
 
ALTER TABLE Works add constraint fk_Work_Employee foreign key (Employee_Id) references Employee(Employee_Id);
ALTER TABLE Works add constraint fk_Work_Offices foreign key (Office_Id) references Offices(Office_Id);




























