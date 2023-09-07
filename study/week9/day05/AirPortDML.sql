-- 고객
INSERT INTO Customer values (1);
INSERT INTO Customer values (2);
-- 고객 주민 정보
INSERT INTO Customer_ResidentNum values ('980909-*******', '마르코', null, null);
INSERT INTO Customer_ResidentNum values ('940423-*******', '정정정', null, null);
-- 고객 여권 정보
INSERT INTO Customer_Passport values (1, 'marco', '마르코', '19980909', 'M', null, null, '20200101', '20250101');
INSERT INTO Customer_Passport values (2, 'jeong', '정정정', '19940423', 'M', null, null, '20200103', '20250103');
-- 고객 식별
INSERT INTO Customer_Code values ('980909-*******', 1, 1);
INSERT INTO Customer_Code values ('940423-*******', 2, 2);
-- 고객 선호 항공 정보
INSERT INTO Prefer_Flight_Information values (1, 1, '나는 아무것도 몰라요');
INSERT INTO Prefer_Flight_Information values (2, 2, '나는 아무것도 몰라요');
-- 개인 고객
INSERT INTO Individual_Customer values ('marco@nhnacademy.com', 1);
INSERT INTO Individual_Customer values ('jeong@nhnacademy.com', 2);
-- 정회원
INSERT INTO Regular_Member values (1, 'marco@nhnacademy.com', 'pwd', '마르코', 'M', '19980909', null, null, null);
-- 간편 회원
INSERT INTO Simple_Member values (2, 'jeong@nhnacademy.com', 'pwd', '정정정', '카카오', 'M', '19940423', null, null, null);
-- 법인 고객
INSERT INTO Corporate_Customer values (1, 1);
INSERT INTO Corporate_Customer values (2, 2);
-- 법인 고객 직원
INSERT INTO Corporate_Employee values (1, 'marco@nhnacademy.com', null);
INSERT INTO Corporate_Employee values (2, 'jeong@nhnacademy.com', null);
-- 법인 우대 혜택
INSERT INTO Corporate_Benefit values (1);
INSERT INTO Corporate_Benefit values (2);

-- 항공사 데이터
INSERT INTO Airline values(0,'제주항공');
INSERT INTO Airline values(1,'대한항공');
INSERT INTO Airline values(2,'아시아나');
-- 항공 계획
INSERT INTO Flight_plan values(0,0,0,20230429,20230501,'김해','제주');
-- 출발 도착
INSERT INTO Departure_Arrival_Information values(0,0,'김해','제주','10000');
-- 운항 경로
INSERT INTO Flight_Path values(0,0,null,0);
-- 공항
INSERT INTO Airport values(0,0,0,'김해공항','갱생냄도 김해시');
-- 비행기
INSERT INTO Airplane values(0,0,'NHN_plain');
-- 좌석 등급
INSERT INTO Seat_Type values(0,'이코노미');
INSERT INTO Seat_Type values(1,'비지니스');
INSERT INTO Seat_Type values(2,'퍼스트');
-- 좌석
INSERT INTO Seat values(0,0,0);
INSERT INTO Seat values(1,0,0);
INSERT INTO Seat values(2,0,0);
INSERT INTO Seat values(3,0,1);
INSERT INTO Seat values(4,0,1);
INSERT INTO Seat values(5,0,2);
-- 상품
INSERT INTO Products values(0,'항공권');
INSERT INTO Products values(1,'부가서비스');
INSERT INTO Products values(2,'제휴상품');
INSERT INTO Products values(3,'악세사리');
INSERT INTO Products values(4,'결합 상품');
-- 비행기와 상품사이의 관계
INSERT INTO Products_Airplane values(0,0);
INSERT INTO Products_Airplane values(1,0);
INSERT INTO Products_Airplane values(2,0);
INSERT INTO Products_Airplane values(3,0);
INSERT INTO Products_Airplane values(4,0);
-- 항공권
INSERT INTO Airline_Tickets(Product_id, Airline_Ticket_name) VALUES (default, '제주행 티켓');
-- 보험 타입
INSERT INTO Subscription_plan_types VALUES (0, '기본', 10000, 100000000);
-- 보험
INSERT INTO Insurances VALUES (0, '제주', 20230429110000, 20230501120000, '이성운', 'M', 19980404, 0);
-- 수하물 
INSERT INTO Baggage_weights VALUES (0, 10, 10000);
-- 기내식
INSERT INTO In_flight_meals VALUES (0, '비빔밥', 10000);
-- 부가서비스
INSERT INTO Extra_services(Product_id, Insurance_id, Baggage_weight_id, In_flight_meal, Seat_id) VALUES (default, 0, 0, 0, 1);
-- 제휴상품 카테고리
INSERT INTO Cooperation_product_categories VALUES (0, '음식');
-- 제휴상품
INSERT INTO Cooperation_products(Product_id, Cooperation_product_category_id, Cooperation_product_name, Cooperation_product_company_name) VALUES (default, 0, '비빔밥', 'NHN식품');
-- 번들 상품
INSERT INTO Bundle_products(Product_id, Airline_Ticket_id, Extra_services_id, Cooperation_products, Accessory_id) VALUES (default, null, null, null, null);
-- 액세서리 sup 카테고리
INSERT Accessory_sup_categories VALUES (0, '시계');
-- 액세서리 sub 카테고리
INSERT Accessory_sub_categories VALUES (0, 0, '손목시계');
-- 액세서리
INSERT Accessories(Product_id, Accessory_sup_category_id, Accessory_sub_category_id, Accessory_name, Accessory_price, Accessory_image) VALUES (default, 0, 0, '롤렉스', 124132000, null);
-- Bundle Product Detail
INSERT INTO Bundle_Product_Detail VALUES (0, null, 1, 1, 1, 1, 1);
-- PNR
INSERT INTO PNR VALUES (1, 1);
-- PAX 
INSERT INTO PAX VALUES (1, 0, '호성이', '0102133412');
-- SEG
INSERT INTO SEG VALUES (1, '제주 렌트카');
-- 추가 서비스 예약
INSERT INTO Additional_Services_Reservation VALUES (0,0,1);
-- SSR
INSERT INTO SSR VALUES (1, 0, '나는 몰라요');
-- 쿠폰
INSERT INTO Coupon VALUES (1, 1, '렌트 할인이요', 100, 20231203120000, 20231203040000);
-- 티켓
INSERT INTO Ticket VALUES (1, 1, 0);
-- 운임비
INSERT INTO Freight_Charge VALUES (1, 1000);
-- 티켓과 운임비 관계
INSERT INTO Ticket_Freight_Charge VALUES (1, 1);
-- TAX
INSERT INTO Tax VALUES (1, 3000);
-- Ticket Tax
INSERT INTO Ticket_Tax VALUES (1,1);
-- Paments Means
INSERT INTO Payment_means VALUES (1, '카드결제', 1);
-- 쿠폰과 결제 수단 관계
INSERT INTO Payment_means_Method VALUES (1, 1);
-- 환불
INSERT INTO Refund VALUES (1, 1);
-- 환불 운임비
INSERT INTO Refund_Freight_Charge VALUES (1, 10000);
-- 환불 방법
INSERT INTO Refund_Method VALUES (1, '통장');
-- 환불 세금
INSERT INTO Refund_TAX VALUES (1, 1000);


-- 일반 회원 모든 정보 가져오기
SELECT 
    c.Customer_id,
    cr.Customer_residentNum,
    cr.Customer_name,
    cr.Customer_address,
    cr.Local_government,
    cp.English_name,
    cp.Korean_name,
    cp.Birth_num,
    cp.Gender,
    cp.Nationality,
    cp.Nation_code,
    cp.Issue_date,
    cp.Expiration_date
FROM 
    Customer c
LEFT JOIN 
    Customer_Code cc ON c.Customer_id = cc.Customer_id
LEFT JOIN 
    Customer_ResidentNum cr ON cc.Customer_residentNum = cr.Customer_residentNum
LEFT JOIN 
    Customer_Passport cp ON cc.Passport_id = cp.Passport_id
LEFT JOIN 
    Prefer_Flight_Information pfi ON c.Customer_id = pfi.Customer_id
LEFT JOIN 
    Individual_Customer ic ON c.Customer_id = ic.Customer_id
LEFT JOIN 
    Regular_Member rm ON ic.Customer_email = rm.Customer_email
LEFT JOIN 
    Simple_Member sm ON ic.Customer_email = sm.Customer_email
WHERE c.Customer_id = 1;

-- marco 티켓 정보 가져오기
-- 일반 회원 모든 정보 가져오기
SELECT
	*
FROM 
    Customer c
LEFT JOIN 
    Customer_Code cc ON c.Customer_id = cc.Customer_id
LEFT JOIN 
    Customer_ResidentNum cr ON cc.Customer_residentNum = cr.Customer_residentNum
LEFT JOIN 
    Customer_Passport cp ON cc.Passport_id = cp.Passport_id
LEFT JOIN 
    Prefer_Flight_Information pfi ON c.Customer_id = pfi.Customer_id
LEFT JOIN 
    Individual_Customer ic ON c.Customer_id = ic.Customer_id
LEFT JOIN 
    Regular_Member rm ON ic.Customer_email = rm.Customer_email
LEFT JOIN 
    Simple_Member sm ON ic.Customer_email = sm.Customer_email
LEFT JOIN 
	PNR pnr ON c.Customer_id = pnr.Passenger_id
LEFT JOIN
	PAX pax ON pnr.Airline_tickets_id = pax.Airline_tickets_id
LEFT JOIN
	Additional_Services_Reservation asr ON asr.Pax_id = pax.Pax_id
LEFT JOIN
	Bundle_Product_Detail bpd ON bpd.Passenger_id = pnr.Passenger_id
LEFT JOIN
	AirLine_tickets at ON at.AirLine_tickets_id = bpd.AirLine_ticket_id
LEFT JOIN
	Products p ON p.Product_id = at.Product_id
LEFT JOIN
	Products_Airplane pa ON pa.Product_id = p.Product_id
LEFT JOIN
	Airplane a ON a.Airplane_id = pa.Airplane_id
LEFT JOIN
	Flight_Plan fp ON fp.Flight_Plan_id = a.Flight_Plan_id
LEFT JOIN
	Extra_Services es ON bpd.Extra_Services_id = es.Extra_Services_id
WHERE c.Customer_id = 1;


-- 비회원으로 예약한 '호성이'에 대한 예약 내역 조회
SELECT
	rm.Regular_memberName as '예약자', pax.Passenger_name as '탑승객', rm.gender as '성별', rm.Customer_email as '예약자 이메일', a.Airplane_name as '항공기',
    fp.Departure_date as '출발일', fp.Departure_airport as '출발지', fp.Arrival_date as '도착일', fp.Arrival_airport as '도착지 ',
    s.Seat_id as '좌석번호', st.Seat_Name as '좌석등급'
FROM 
    Customer c
LEFT JOIN 
    Customer_Code cc ON c.Customer_id = cc.Customer_id
LEFT JOIN 
    Customer_ResidentNum cr ON cc.Customer_residentNum = cr.Customer_residentNum
LEFT JOIN 
    Customer_Passport cp ON cc.Passport_id = cp.Passport_id
LEFT JOIN 
    Prefer_Flight_Information pfi ON c.Customer_id = pfi.Customer_id
LEFT JOIN 
    Individual_Customer ic ON c.Customer_id = ic.Customer_id
LEFT JOIN 
    Regular_Member rm ON ic.Customer_email = rm.Customer_email
LEFT JOIN 
    Simple_Member sm ON ic.Customer_email = sm.Customer_email
LEFT JOIN 
	PNR pnr ON c.Customer_id = pnr.Passenger_id
LEFT JOIN
	PAX pax ON pnr.Airline_tickets_id = pax.Airline_tickets_id
LEFT JOIN
	Additional_Services_Reservation asr ON asr.Pax_id = pax.Pax_id
LEFT JOIN
	Bundle_Product_Detail bpd ON bpd.Passenger_id = pnr.Passenger_id
LEFT JOIN
	AirLine_tickets at ON at.AirLine_tickets_id = bpd.AirLine_ticket_id
LEFT JOIN
	Products p ON p.Product_id = at.Product_id
LEFT JOIN
	Products_Airplane pa ON pa.Product_id = p.Product_id
LEFT JOIN
	Airplane a ON a.Airplane_id = pa.Airplane_id
LEFT JOIN
	Flight_Plan fp ON fp.Flight_Plan_id = a.Flight_Plan_id
LEFT JOIN
	Extra_Services es ON bpd.Extra_Services_id = es.Extra_Services_id
LEFT JOIN
	Seat s ON s.Airplane_id = a.Airplane_id and s.seat_id = es.seat_id
LEFT JOIN 
	Seat_Type st ON st.Seat_type_id = s.Seat_type_id
WHERE pax.Passenger_name = '호성이' AND pnr.Airline_tickets_id = 1 AND fp.Departure_date = 20230429;

-- 고객인 마르고가 예약한 탑승객이 '호성이'에 대한 예약 내역 조회
SELECT
	rm.Regular_memberName as '예약자', pax.Passenger_name as '탑승객', rm.gender as '성별', rm.Customer_email as '예약자 이메일', a.Airplane_name as '항공기',
    fp.Departure_date as '출발일', fp.Departure_airport as '출발지', fp.Arrival_date as '도착일', fp.Arrival_airport as '도착지 ',
    s.Seat_id as '좌석번호', st.Seat_Name as '좌석등급'
FROM 
    Customer c
LEFT JOIN 
    Customer_Code cc ON c.Customer_id = cc.Customer_id
LEFT JOIN 
    Customer_ResidentNum cr ON cc.Customer_residentNum = cr.Customer_residentNum
LEFT JOIN 
    Customer_Passport cp ON cc.Passport_id = cp.Passport_id
LEFT JOIN 
    Prefer_Flight_Information pfi ON c.Customer_id = pfi.Customer_id
LEFT JOIN 
    Individual_Customer ic ON c.Customer_id = ic.Customer_id
LEFT JOIN 
    Regular_Member rm ON ic.Customer_email = rm.Customer_email
LEFT JOIN 
    Simple_Member sm ON ic.Customer_email = sm.Customer_email
LEFT JOIN 
	PNR pnr ON c.Customer_id = pnr.Passenger_id
LEFT JOIN
	PAX pax ON pnr.Airline_tickets_id = pax.Airline_tickets_id
LEFT JOIN
	Additional_Services_Reservation asr ON asr.Pax_id = pax.Pax_id
LEFT JOIN
	Bundle_Product_Detail bpd ON bpd.Passenger_id = pnr.Passenger_id
LEFT JOIN
	AirLine_tickets at ON at.AirLine_tickets_id = bpd.AirLine_ticket_id
LEFT JOIN
	Products p ON p.Product_id = at.Product_id
LEFT JOIN
	Products_Airplane pa ON pa.Product_id = p.Product_id
LEFT JOIN
	Airplane a ON a.Airplane_id = pa.Airplane_id
LEFT JOIN
	Flight_Plan fp ON fp.Flight_Plan_id = a.Flight_Plan_id
LEFT JOIN
	Extra_Services es ON bpd.Extra_Services_id = es.Extra_Services_id
LEFT JOIN
	Seat s ON s.Airplane_id = a.Airplane_id and s.seat_id = es.seat_id
LEFT JOIN 
	Seat_Type st ON st.Seat_type_id = s.Seat_type_id
WHERE c.Customer_id = 1;

















