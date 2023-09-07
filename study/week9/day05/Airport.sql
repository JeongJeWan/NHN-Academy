-- DROP DATABASE Aerial_System;
CREATE DATABASE Aerial_System;

USE Aerial_System;

-- PNR
CREATE TABLE PNR(
Passenger_id INT,
Airline_tickets_id INT,
CONSTRAINT pk_pnr PRIMARY KEY (Airline_tickets_id, Passenger_id)
);

-- PAX
CREATE TABLE PAX(
Airline_tickets_id INT,
Pax_id INT,
Passenger_name varchar(30), 
Passenger_phone varchar(30),
CONSTRAINT pk_pax PRIMARY KEY (Pax_id, Airline_tickets_id)
);

-- Pax 여권 상세정보
CREATE TABLE PAX_Passport (
	Passport_id INT,
    Pax_id INT,
    English_name varchar(30),
    Korean_name varchar(30),
    Birth_num date,
    Gender enum('M', 'F'),
    Nationality varchar(30),
    Nation_code varchar(30),
    Issue_date date,
    Expiration_date date,
    constraint pk_Customer_Passport primary key (Passport_id, Pax_id)
);

-- SSR
CREATE TABLE SSR(
SEG_id INT,
Pax_id INT,
SSR_content TEXT,
CONSTRAINT pk_ssr PRIMARY KEY (SEG_id, Pax_id)
);

-- 부가서비스 예약
CREATE TABLE Additional_Services_Reservation(
Pax_id INT,
Bundle_product_detail_id INT,
SEG_id INT,
CONSTRAINT pk_additional_services_reservation PRIMARY KEY(Pax_id, Bundle_product_detail_id, SEG_id)
);

-- SEG
CREATE TABLE SEG(
SEG_id INT primary key,
Affiliated_Company_name varchar(20)
);

-- 제휴회사 지원?
CREATE TABLE Affiliated_Company_Supprot(
Affiliated_Company_Supprot_id INT primary key,
SEG_id INT,
Affiliated_Company_Supprot_content varchar(30)
);


-- 티켓
CREATE TABLE Ticket(
Ticket_id INT,
Airline_tickets_id INT,
Pax_id INT,
CONSTRAINT pk_ticket PRIMARY KEY(Ticket_id, Airline_tickets_id, Pax_id)
);

-- 운임
CREATE TABLE Freight_Charge(
Freight_Charge_id INT primary key,
Freight_Charge_cost INT
);

-- 운임과 티켓 관계
CREATE TABLE Ticket_Freight_Charge(
Ticket_id INT,
Freight_Charge_id INT,
CONSTRAINT pk_ticket_freight_charge PRIMARY KEY(Ticket_id, Freight_Charge_id)
);

-- 세금
CREATE TABLE Tax(
Tax_id INT primary key,
Tax_cost INT
);

-- 세금과 티켓 관계
CREATE TABLE Ticket_Tax(
Ticket_id INT,
Tax_id INT,
CONSTRAINT pk_ticket_tax PRIMARY KEY(Ticket_id, Tax_id)
);

-- 결제수단
CREATE TABLE Payment_Means(
Payment_Means_id INT,
Payment_Means_name varchar(20),
Ticket_id INT,
CONSTRAINT pk_payment_means PRIMARY KEY(Payment_Means_id, Ticket_id)
);

-- 결제수단 방법
CREATE TABLE Payment_Means_Method(
Payment_Means_id INT,
Coupon_id INT,
CONSTRAINT pk_payment_means_method PRIMARY KEY(Payment_Means_id, Coupon_id)
);

-- 쿠폰
CREATE TABLE Coupon(
Coupon_id INT,
SEG_id INT,
Coupon_name varchar(30),
Coupon_value INT,
Coupon_start DATETIME,
Coupon_end DATETIME,
CONSTRAINT pk_coupon PRIMARY KEY (Coupon_id, SEG_id)
);

-- 환불
CREATE TABLE Refund(
Ticket_id INT,
Refund_id INT,
CONSTRAINT pk_refund PRIMARY KEY(Refund_id, Ticket_id)
);

-- 환불 결제 수단
CREATE TABLE Refund_Method(
Refund_id INT primary key,
Refund_name varchar(30)
);

-- 환불 세금
CREATE TABLE Refund_Tax(
Refund_id INT primary key,
Refund_tax_cost INT
);

-- 환불 운임
CREATE TABLE Refund_Freight_Charge(
Refund_id INT primary key,
Freight_Charge_cost INT
);






-- 승무원
CREATE TABLE Flight_Attendant(
Flight_Attendant_id INT,
Flight_Attendant_Group_id INT,
Flight_Attendant_gender enum('F', 'M'),
Flight_Attendant_name varchar(10),
CONSTRAINT pk_flight_attendant PRIMARY KEY(Flight_Attendant_id, Flight_Attendant_Group_id)
);

-- 승무원편조
CREATE TABLE Flight_Attendant_Group(
Flight_Attendant_Group_id INT,
Flight_Attendant_Plan_id INT,
Flight_Attendant_Group_name varchar(10),
CONSTRAINT pk_flight_attedant_group PRIMARY KEY(Flight_Attendant_Group_id, Flight_Attendant_Plan_id)
);

-- 승무원 비행계획
CREATE TABLE Flight_Attendant_Plan(
Flight_Attendant_Plan_id INT,
Flight_Plan_id INT,
CONSTRAINT pk_flight_attendant_plan PRIMARY KEY(Flight_Attendant_Plan_id, Flight_Plan_id)
);

-- 운항계획
CREATE TABLE Flight_Plan(
Flight_Plan_id INT primary key,
Airline_id INT,
Seasonal_Code INT,
Departure_date DATE,
Arrival_date DATE,
Departure_airport varchar(20),
Arrival_airport varchar(20)
);

-- 항공사
CREATE TABLE Airline(
Airline_id INT primary key,
Airline_name varchar(20)
);

-- 운항경로
CREATE TABLE Flight_Path(
Flight_Path_id INT,
Flight_Plan_id INT,
ATC_Advisor_id INT,
Departure_Arrival_Information_id INT,
CONSTRAINT pk_flight_path PRIMARY KEY(Flight_Path_id, Flight_Plan_id)
);

-- ATC Advisor
CREATE TABLE ATC_Advisor(
ATC_Advisor_id INT,
Airport_id INT,
ATC_Advisor_content TEXT,
CONSTRAINT pk_atc_advisor PRIMARY KEY(ATC_Advisor_id, Airport_id)
);

-- 출도착
CREATE TABLE Departure_Arrival_Information(
Departure_Arrival_Information_id INT,
Flight_Plan_id INT,
Departure_name varchar(30),
Arrival_name varchar(30),
mileage INT,
CONSTRAINT pk_departure_arrival_information PRIMARY KEY(Departure_Arrival_Information_id, Flight_Plan_id)
);

-- 공항
CREATE TABLE Airport(
Airport_id INT,
Departure_Arrival_Information_id int,
Flight_Path_id INT,
Airport_name varchar(20),
Airport_location varchar(30),
CONSTRAINT pk_airport PRIMARY KEY(Airport_id, Flight_Path_id)
);

-- 항공기
CREATE TABLE Airplane(
Airplane_id INT,
Flight_plan_id INT,
Airplane_name varchar(20),
CONSTRAINT pk_airplane PRIMARY KEY (Airplane_id, Flight_plan_id)
);

-- 좌석
CREATE TABLE Seat(
Seat_id INT,
Airplane_id INT,
Seat_type_id INT,
CONSTRAINT pk_seat PRIMARY KEY (Seat_id, Airplane_id, Seat_type_id)
);

-- 좌석 타입
CREATE TABLE Seat_Type(
Seat_type_id INT primary key,
Seat_name varchar(45)
);

-- 승무원 그룹 아이디 참조
ALTER TABLE  Flight_Attendant ADD CONSTRAINT fk_flight_attendant_group_id FOREIGN KEY (Flight_Attendant_Group_id) REFERENCES Flight_Attendant_Group(Flight_Attendant_Group_id);

-- 승무원 비행 계획 참조
ALTER TABLE Flight_Attendant_Group ADD CONSTRAINT fk_flight_attendant_plan_id FOREIGN KEY (Flight_Attendant_Plan_id) REFERENCES Flight_Attendant_Plan(Flight_Attendant_Plan_id);

-- 승무원 비행 계획에서 운항 계획 참조
ALTER TABLE Flight_Attendant_Plan ADD CONSTRAINT fk_flight_plan_id FOREIGN KEY (Flight_Plan_id) REFERENCES Flight_Plan(Flight_Plan_id);

-- 항공사 참조
ALTER TABLE Flight_Plan ADD CONSTRAINT fk_flight_plan_airline FOREIGN KEY (Airline_id) REFERENCES Airline(Airline_id);

-- 운항 경로에서 운항 계획 참조
ALTER TABLE Flight_Path ADD CONSTRAINT fk_flight_path_flight_plan FOREIGN KEY (Flight_Plan_id) REFERENCES Flight_Plan(Flight_Plan_id);

-- 운항 계획에서 ATC Advisor 참조
ALTER TABLE Flight_Path ADD CONSTRAINT fk_flight_path_atc_advisor FOREIGN KEY (ATC_Advisor_id) REFERENCES ATC_Advisor(ATC_Advisor_id);

-- 공항에서 운항 경로 참조
ALTER TABLE Airport ADD CONSTRAINT fk_airport_flight_path FOREIGN KEY (Flight_Path_id) REFERENCES Flight_Path(Flight_Path_id);

-- ATC Advisor에서 공항 참조
ALTER TABLE ATC_Advisor ADD CONSTRAINT fk_atc_advisor_airport FOREIGN KEY (Airport_id) REFERENCES Airport(Airport_id);

-- 출도착에서 운항 계획 참조
ALTER TABLE Departure_Arrival_Information ADD CONSTRAINT fk_departure_arrival_information_flight_plan FOREIGN KEY (Flight_Plan_id) REFERENCES Flight_Plan(Flight_Plan_id);

-- PAX에서 PNR 참조
ALTER TABLE PAX ADD CONSTRAINT fk_pax_pnr_id FOREIGN KEY (Airline_tickets_id) REFERENCES PNR(Airline_tickets_id);

-- SSR에서 PAX 참조
ALTER TABLE SSR ADD CONSTRAINT fk_ssr_pax_id FOREIGN KEY (Pax_id) REFERENCES Pax(Pax_id);

-- SSR에서 SEG 참조
ALTER TABLE SSR ADD CONSTRAINT fk_ssr_seg_id FOREIGN KEY (SEG_id) REFERENCES SEG(SEG_id);

-- 부가서비스 예약 내역에서 PAX 참조
ALTER TABLE Additional_Services_Reservation ADD CONSTRAINT fk_additional_services_reservation_pax_id FOREIGN KEY (Pax_id) REFERENCES Pax(Pax_id);

-- 부가서비스 예약 내역에서 SEG 참조
ALTER TABLE Additional_Services_Reservation ADD CONSTRAINT fk_additional_services_reservation_seg_id FOREIGN KEY (SEG_id) REFERENCES SEG(SEG_id);

-- 티켓에서 PAX 참조
ALTER TABLE Ticket ADD CONSTRAINT fk_ticket_pax_id FOREIGN KEY (Pax_id) REFERENCES Pax(Pax_id);

-- 쿠폰에서 SEG 참조
ALTER TABLE Coupon ADD CONSTRAINT fk_coupon_seg FOREIGN KEY (SEG_id) REFERENCES SEG(SEG_id);

-- 제휴회사 지원에서 SEG 참조
ALTER TABLE Affiliated_Company_Supprot ADD CONSTRAINT fk_affiliated_company_supprot_seg_id FOREIGN KEY (SEG_id) REFERENCES SEG(SEG_id);

-- 결제 수단에서 티켓 참조
ALTER TABLE Payment_Means ADD CONSTRAINT fk_payment_means_ticket_id FOREIGN KEY(Ticket_id) REFERENCES Ticket(Ticket_id);

-- 결제 수단 방법에서 결제 수단 참조
ALTER TABLE Payment_Means_Method ADD CONSTRAINT fk_payment_means_method_id FOREIGN KEY(Payment_Means_id) REFERENCES Payment_Means(Payment_Means_id);

-- 쿠폰에서 결제 수단 방법 참조
ALTER TABLE Payment_Means_Method ADD CONSTRAINT fk_payment_means_method_coupon_id FOREIGN KEY(Coupon_id) REFERENCES Coupon(Coupon_id);

-- Ticket_Tax에서 티켓 참조
ALTER TABLE Ticket_Tax ADD CONSTRAINT fk_ticket_tax_ticket_id FOREIGN KEY (Ticket_id) REFERENCES Ticket(Ticket_id);

-- Ticket_Tax에서 세금 참조
ALTER TABLE Ticket_Tax ADD CONSTRAINT fk_ticket_tax_tax_id FOREIGN KEY (Tax_id) REFERENCES Tax(Tax_id);

-- Ticket_Freight_Charge에서 티켓 참조
ALTER TABLE Ticket_Freight_Charge ADD CONSTRAINT fk_ticket_freight_charge_ticket_id FOREIGN KEY (Ticket_id) REFERENCES Ticket(Ticket_id);

-- Ticket_Freight_Charge에서 운임 참조 
ALTER TABLE Ticket_Freight_Charge ADD CONSTRAINT fk_ticket_freight_charge_freight_charge_id FOREIGN KEY (Freight_Charge_id) REFERENCES Freight_Charge(Freight_Charge_id);

-- 환불에서 티켓 참조
ALTER TABLE Refund ADD CONSTRAINT fk_refund_ticket_id FOREIGN KEY (Ticket_id) REFERENCES Ticket(Ticket_id);

-- 환불 수단에서 환불 참조
ALTER TABLE Refund_Method ADD CONSTRAINT fk_refund_method_refund_id FOREIGN KEY (Refund_id) REFERENCES Refund(Refund_id);

-- 환불 세금에서 환불 참조
ALTER TABLE Refund_Tax ADD CONSTRAINT fk_refund_tax_refund_id FOREIGN KEY (Refund_id) REFERENCES Refund(Refund_id);

-- 환불 운임에서 환불 참조
ALTER TABLE Refund_Freight_Charge ADD CONSTRAINT fk_refund_freight_charge_refund_id FOREIGN KEY (Refund_id) REFERENCES Refund(Refund_id);

-- 항공기에서 운항 계획 참조
ALTER TABLE Airplane ADD CONSTRAINT fk_airplane_flight_plan_id FOREIGN KEY (Flight_plan_id) REFERENCES Flight_Plan(Flight_plan_id);

-- 좌석에서 항공기 참조
ALTER TABLE Seat ADD CONSTRAINT fk_seat_airplane_id FOREIGN KEY (Airplane_id) REFERENCES Airplane(Airplane_id);

-- 좌석에서 좌석 타입 참조
ALTER TABLE Seat ADD CONSTRAINT fk_seat_seat_type FOREIGN KEY (Seat_type_id) REFERENCES Seat_Type(Seat_type_id);

-- PAX_Passport에서 PAX 참조
ALTER TABLE PAX_Passport ADD CONSTRAINT fk_pax_passport_pax_id FOREIGN KEY (Pax_id) REFERENCES Pax(Pax_id);

CREATE TABLE Products(
	Product_id integer primary key,
    Product_name varchar(30)
);
-- 항공권
CREATE TABLE AirLine_tickets(
	Product_id integer default 0,
	Airline_tickets_id integer auto_increment primary key,
    Airline_ticket_name varchar(10)
);

-- 부가서비스
CREATE TABLE Extra_services(
	Product_id integer default 1,
	Extra_services_id integer auto_increment primary key,
    Insurance_id integer, 
    Baggage_weight_id integer, 
    In_flight_meal integer,
    Seat_id integer
);
-- 부가서비스의 보험
CREATE TABLE Insurances(
	Insurance_id integer primary key,
	Travel_country varchar(30),
    Insurance_start_time DateTime,
    Insurance_end_time DateTime,
    Traveler_name varchar(30),
    Traveler_gender enum('M','F'),
    Traveler_birth Date,
    Subscription_plan_type_id integer
);
-- 부가서비스의 보험의 보험유형
CREATE TABLE Subscription_plan_types(
	Subscription_plan_type_id integer primary key,
    Subscription_plan_type_name varchar(30),
    Subscription_plan_type_price integer,
    Subscription_plan_type_fee integer
);

-- 부가서비스의 수화물 무게 선택지
CREATE TABLE Baggage_weights(
	Baggage_weight_id integer primary key,
    Baggage_weight integer,
    Baggage_weight_price integer
);
-- 부가서비스의 기내식 선택지
CREATE TABLE In_flight_meals(
	In_flight_meal_id integer primary key,
    In_flight_meal_name varchar(50),
    In_flight_meal_price integer
);
-- 제휴상품
CREATE TABLE Cooperation_products(
	Product_id integer default 2,
	Cooperation_product_id integer auto_increment primary key,
    Cooperation_product_category_id integer,
    Cooperation_product_name varchar(50),
    Cooperation_product_company_name varchar(50)
);
-- 제휴상품 카테고리
CREATE TABLE Cooperation_product_categories(
	Cooperation_product_category_id integer primary key,
    Cooperation_product_category_name varchar(30)
);
-- 악세사리
CREATE TABLE Accessories(
	Product_id integer default 3,
    Accessory_sub_category_id integer,
    Accessory_sup_category_id integer,
	Accessory_id integer auto_increment primary key,
    Accessory_name varchar(50),
    Accessory_price integer,
    Accessory_image varchar(300)
);
-- 악세사리 서브 카테고리
CREATE TABLE Accessory_sub_categories(
	Accessory_sub_category_id integer primary key,
    Accessory_sup_category_id integer,
    Accessory_sup_category_name varchar(50)
);
-- 악세사리 슈퍼 카테고리
CREATE TABLE Accessory_sup_categories(
	Accessory_sup_category_id integer primary key,
    Accessory_sup_category_name varchar(50)
);
-- 결합상품
CREATE TABLE Bundle_products(
	Product_id integer default 4,
    Bundle_product_id integer auto_increment primary key,
    Airline_Ticket_id integer,
    Extra_services_id integer,
    Cooperation_products integer,
    Accessory_id integer
);
-- 결합상품 구성상세 -->> 예약하는 순간 부가서비스 예약 내역에 Bundle_product_detail_id가 들어가는 방식임. 탑승객의 정보와 함께 1대1관
CREATE TABLE Bundle_product_detail(
	Bundle_product_detail_id integer,
    Bundle_product_id integer,
	AirLine_ticket_id integer,
    Extra_services_id integer,
    Cooperation_products_id integer,
    Accessory_id integer,
	Passenger_id integer,
    
    Constraint pk_Bundle_product_detail primary key(Bundle_product_detail_id,Passenger_id)
);


Alter table Extra_services add constraint fk_Insurances_Extra foreign key(Insurance_id) references Insurances(Insurance_id);
Alter table Extra_services add constraint fk_Baggageweight_Extra foreign key(Baggage_weight_id) references Baggage_weights(Baggage_weight_id);
Alter table Extra_services add constraint fk_Meal_Extra foreign key(In_flight_meal) references In_flight_meals(In_flight_meal_id);

SELECT *
from products;



Alter table Extra_services add constraint fk_Extra_Product foreign key(Product_id) references Products(Product_id);
Alter table Cooperation_products add constraint fk_Cooperation_Product foreign key(Product_id) references Products(Product_id);
Alter table Accessories add constraint fk_Accessory_Product foreign key(Product_id) references Products(Product_id);
Alter table AirLine_tickets add constraint fk_Aticket_Product foreign key(Product_id) references Products(Product_id);
Alter table Bundle_products add constraint fk_Bproduct_Product foreign key(Product_id) references Products(Product_id);

Alter table Accessories add constraint fk_Accessory_Subcategory foreign key(Accessory_sub_category_id) references Accessory_sub_categories(Accessory_sub_category_id);
Alter table Accessories add constraint fk_Accessory_Supcategory foreign key(Accessory_sup_category_id) references Accessory_sup_categories(Accessory_sup_category_id);

Alter table Accessory_sub_categories add constraint fk_Sub_Sup foreign key(Accessory_sup_category_id) references Accessory_sup_categories(Accessory_sup_category_id);

Alter table Cooperation_products add constraint fk_Cooperation_Category foreign key(Cooperation_product_category_id) references Cooperation_product_categories(Cooperation_product_category_id);

Alter table Bundle_product_detail add constraint fk_Detail_Ticket foreign key(AirLine_ticket_id) references Airline_tickets(AirLine_tickets_id);
Alter table Bundle_product_detail add constraint fk_Detail_Extra foreign key(Extra_services_id) references Extra_services(Extra_services_id);
Alter table Bundle_product_detail add constraint fk_Detail_Cooperation foreign key(Cooperation_products_id) references Cooperation_products(Cooperation_product_id);
Alter table Bundle_product_detail add constraint fk_Detail_Accessories foreign key(Accessory_id) references Accessories(Accessory_id);
Alter table Bundle_product_detail add constraint fk_Detail_Bundle foreign key(Bundle_product_id) references Bundle_products(Bundle_product_id);

Alter table Insurances add constraint fk_Plantype_Insurnace foreign key(Subscription_plan_type_id) references SubScription_plan_types(Subscription_plan_type_id);






-- 고객
CREATE TABLE Customer (
	Customer_id INT primary key
);

-- 고객 식별 (여권, 주민번호)
CREATE TABLE Customer_Code (
	Customer_residentNum varchar(100),
    Passport_id INT,
    Customer_id INT,
    
    constraint pk_Customer_Code primary key (Customer_id, Customer_residentNum, Passport_id)
);


-- 고객 주민등록번호 상세정보
CREATE TABLE Customer_ResidentNum (
	Customer_residentNum varchar(100),
    Customer_name varchar(30),
    Customer_address varchar(500),
    Local_government varchar(30), -- 관리 지자체
    
    constraint pk_Customer_ResidentNum primary key (Customer_residentNum)
);


-- 고객 여권 상세정보
CREATE TABLE Customer_Passport (
	Passport_id INT,
    English_name varchar(30),
    Korean_name varchar(30),
    Birth_num date,
    Gender enum('M', 'F'),
    Nationality varchar(30), -- 국적
    Nation_code varchar(30), -- 국가 코드
    Issue_date date,
    Expiration_date date,
    
    constraint pk_Customer_Passport primary key (Passport_id)
);



-- 선호 비행 정보
CREATE TABLE Prefer_Flight_Information(
	Prefer_flightInform_id INT,
    Customer_id INT,
    Content TEXT,
    
    constraint pk_Prefer_Flight_Information primary key (Prefer_flightInform_id, Customer_id)
);

-- 개인 고객
CREATE TABLE Individual_Customer (
	Customer_email varchar(30),
    Customer_id INT,
    
    constraint pk_Individual_Customer primary key (Customer_email, Customer_id)
);

-- 정회원
CREATE TABLE Regular_Member (
	Regular_memberId INT,
	Customer_email varchar(30),
    Regular_memberPassword varchar(40),
    Regular_memberName varchar(30),
    Gender enum('M', 'F'),
    Birth_num date,
    Nationality varchar(30), -- 국적
    Nation_code varchar(30), -- 국가 코드
    Phone_num varchar(30),
    
    constraint pk_Regular_Member primary key (Regular_memberId, Customer_email)
);

-- 간편 회원
CREATE TABLE Simple_Member (
	Simple_memberId INT,
	Customer_email varchar(30),
    Simple_memberPwd varchar(30),
    Simple_memberName varchar(30),
    Company_name varchar(30), -- 카카오, 네이버 ..
    Gender enum('M', 'F'),
    Birth_num date,
    Nationality varchar(30), -- 국적
    Nation_code varchar(30), -- 국가 코드
    Phone_num varchar(30),
    
    constraint pk_Simple_Member primary key (Simple_memberId, Customer_email)
);

-- 법인 고객
CREATE TABLE Corporate_Customer (
	Company_id INT,
    Customer_id INT,
    
    constraint pk_Corporate_Customer primary key (Company_id, Customer_id)
);

-- 법인 고객 직원
CREATE TABLE Corporate_Employee (
	Company_id INT,
    Customer_email varchar(30),
    Employee_name varchar(30),
    
    constraint pk_Corporate_Employee primary key (Company_id, Customer_email)
);

-- 법인 우대 혜택
CREATE TABLE Corporate_Benefit(
	Company_id INT primary key
);


-- 법인 우대 혜택 연중 상시 할인
CREATE TABLE Benefit_Discount (
	Benefit_discountId INT,
	Company_id INT,
    Benefit_name varchar(60), 
    
    constraint pk_Benefit_Discount primary key (Benefit_discountId, Company_id)
);


-- 법인 우대 가족 혜택
CREATE TABLE Family_Benefit (
	Family_benefitId INT,
	Company_id INT,
    Benefit_name varchar(60),
    
    constraint pk_Family_Benefit primary key (Family_benefitId, Company_id)
);

-- 법인 우대 포인트 적립 
CREATE TABLE Point_Save (
	Point_saveId INT,
	Company_id INT,
    Benefit_name varchar(60),
    
    constraint pk_Point_Save primary key (Point_saveId, Company_id)
);

-- 고객 식별에서 고객을 참조
ALTER TABLE Customer_Code add constraint fk_Customer_Code foreign key (Customer_id) references Customer(Customer_id);

-- 고객 주민 정보에서 고객식별을 참조
ALTER TABLE Customer_Code add constraint fk_Customer_ResidentNum foreign key (Customer_residentNum) references Customer_ResidentNum(Customer_residentNum);
-- 고객 여권 정보에서 고객식별을 참조
ALTER TABLE Customer_Code add constraint fk_Customer_Passport foreign key (Passport_id) references Customer_Passport(Passport_id);


-- 선호 비행 정보에서 고객을 참조
ALTER TABLE Prefer_Flight_Information add constraint fk_Prefer_Flight_Information foreign key (Customer_id) references Customer(Customer_id);
-- 개인 고객이 고객을 참조
ALTER TABLE Individual_Customer add constraint fk_Individual_Customer foreign key (Customer_id) references Customer(Customer_id);
-- 정회원이 개인 고객을 참조
ALTER TABLE Regular_Member add constraint fk_Regular_Member foreign key (Customer_email) references Individual_Customer(Customer_email);
-- 간편 회원이 개인 고객을 참조
ALTER TABLE Simple_Member add constraint fk_Simple_Member foreign key (Customer_email) references Individual_Customer(Customer_email);
--  법인 고객이 고객을 참조
ALTER TABLE Corporate_Customer add constraint fk_Corporate_Customer foreign key (Customer_id) references Customer(Customer_id);
-- 법인 고객 직원이 법인 고객을 참조
ALTER TABLE Corporate_Employee add constraint fk_Corporate_Employee_Customer foreign key (Company_id) references Corporate_Customer(Company_id);
-- 법인 고개 직원이 개인 고객을 참조
ALTER TABLE Corporate_Employee add constraint fk_Corporate_Employee_Individual_Customer foreign key (Customer_email) references Individual_Customer(Customer_email);
-- 법인 우대 혜택이 법인 고객을 참조
ALTER TABLE Corporate_Benefit add constraint fk_Corporate_Benefit foreign key (Company_id) references Corporate_Customer(Company_id);
-- 법인 우대 혜택 연중 상시 할인이 법인 우대 혜택을 참조
ALTER TABLE Benefit_Discount add constraint fk_Benefit_Discount foreign key (Company_id) references Corporate_Benefit(Company_id);
-- 법인 우대 가족 혜택이 법인 우대 혜택을 참조
ALTER TABLE Family_Benefit add constraint fk_Family_Benefit foreign key (Company_id) references Corporate_Benefit(Company_id);
-- 법인 우대 포인트 적립이 법인 우대 혜택을 참조
ALTER TABLE Point_Save add constraint fk_Point_Save foreign key (Company_id) references Corporate_Benefit(Company_id);





CREATE TABLE Products_Airplane(
Product_id INT,
Airplane_id INT,
CONSTRAINT pk_products_airplane PRIMARY KEY(Product_id, Airplane_id)
);

ALTER TABLE Products_Airplane ADD CONSTRAINT fk_products_airplane_product_id FOREIGN KEY (Product_id) REFERENCES Products(Product_id);

ALTER TABLE Products_Airplane ADD CONSTRAINT fk_products_airplane_airplane_id FOREIGN KEY (Airplane_id) REFERENCES Airplane(Airplane_id);

ALTER TABLE Bundle_Product_Detail ADD CONSTRAINT fk_bundle_product_detail_passenger_id FOREIGN KEY (Passenger_id) REFERENCES Customer(Customer_id);

ALTER TABLE Additional_Services_Reservation ADD CONSTRAINT fk_additional_servies_reservaiton_bundle_product_detail_id FOREIGN KEY(Bundle_product_detail_id) REFERENCES Bundle_Product_Detail(Bundle_Product_Detail_id);

ALTER TABLE PNR ADD CONSTRAINT fk_pnr_customer_id FOREIGN KEY (Passenger_id) REFERENCES Customer(Customer_id);

Alter table Airport add constraint fk_airport_departure_arrival_information_id foreign key (Departure_Arrival_Information_id) references Departure_Arrival_Information(Departure_Arrival_Information_id);



