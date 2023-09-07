-- DROP DATABASE 정제완_Shopping_Mall;

CREATE DATABASE 정제완_Shopping_Mall;

use 정제완_Shopping_Mall;

-- 사용자
CREATE TABLE Users (
	User_Id varchar(20) primary key,
	User_Password varchar(25),
    User_Name varchar(20),
    User_Email varchar(30),
    User_Phone varchar(30),
    User_Address varchar(30),
    User_Mileage INT
);

-- 사용자와 주소 다대다 관계
CREATE TABLE User_Address (
	Address_Id INT,
    User_Id varchar(20),
    
    constraint pk_User_Address primary key (Address_Id, User_Id)
);


-- 사용자 주소
CREATE TABLE Address (
	Address_Id INT primary key,
    User_Postal_Address1 varchar(20),
    User_Base_Address2 varchar(50),
    User_Detail_Address3 varchar(50)
);

-- 사용자 세부사항
CREATE TABLE User_detail (
	User_Id varchar(20) primary key,
	User_Gender enum('남자', '여자'),
    User_Birthd date
);


-- 사용자 주문
CREATE TABLE User_Order (
	Order_Id INT,
    User_Id varchar(20),
    Order_Date date,
    Receiver_Name varchar(30),
    Receiver_Phone varchar(30),
    Receiver_User_Address_Id INT,
    Receiver_User_Address varchar(30),
    
    
    constraint pk_User_Oreder primary key (Order_Id, User_Id)
);


-- 사용자 주문과 보내는 곳 주소 관계
CREATE TABLE Order_Address (
	Address_Id INT,
    Order_Id INT,
    
    constraint pk_Oreder_Address primary key (Address_Id, Order_Id)
);

-- 사용자 주문 세부사항
CREATE TABLE User_Order_Detail (
	Order_Detail_Id INT,
    Order_Id INT,
    Product_Id INT,
    Product_Count INT,
    Product_Price INT,
    Oreder_Detail_Status varchar(30),
    
    constraint pk_Order_Detail_Order_Product primary key (Order_Detail_Id, Order_Id, Product_Id)
);

-- 사용자 주문 환불
CREATE TABLE User_Order_Refund (
	Order_Refund_Id INT,
    Order_Detail_Id INT,
    Refund_Reason varchar(300),
    User_Email varchar(30),
    Refund_Image_Id INT,
    
    constraint pk_User_OrderRefund_Detail primary key (Order_Refund_Id, Order_Detail_Id)
);

-- 시용자 환불 이미지
CREATE TABLE Refund_ImageFile (
	Refund_Image_Id INT,
    Order_Refund_Id INT,
    Refund_ImageFile_Name varchar(30),
    
    constraint pk_User_OrderRefund_ImageFile primary key (Refund_Image_Id, Order_Refund_Id)
);

-- 쿠폰
CREATE TABLE Coupons (
	Coupon_Id INT primary key,
    Coupon_Name varchar(30),
    Coupon_Detail TEXT,
    Discount_Percent INT,
    Discount_Price INT
);

-- 사용자 쿠폰
CREATE TABLE User_Coupons (
	Coupon_Id INT,
    User_Id varchar(20),
    Coupon_Start_Dt INT,
    Coupon_End_Dt INT,
    
    constraint pk_User_Coupin primary key (Coupon_Id, User_Id)
);

-- 비회원 사용자 주문
CREATE TABLE Non_User_Order (
	Non_User_Order_Id INT primary key,
    Non_User_Receiver_Name varchar(20),
    Non_User_Receiver_Phone varchar(20),
    Receiver_Non_User_Address_Id INT,
    Receiver_Non_User_Address varchar(30)
);

-- 비회원 사용자 주문 세부사항
CREATE TABLE Non_User_Order_Detail (
	Non_User_Order_Detail_Id INT,
    Non_User_Order_Id INT,
    Product_Id INT,
    Non_User_Order_Date date,
    Product_Count INT,
    Product_Price INT,
    Oreder_Detail_Status varchar(20),

    constraint pk_NonUser_Oreder_Detail_Product primary key (Non_User_Order_Detail_Id, Non_User_Order_Id, Product_Id)
);

-- 비회원 사용자 주문 환불
CREATE TABLE Non_User_Order_Refund (
	Non_User_Order_Refund_Id INT,
    Non_User_Order_Detail_Id INT,
    Refund_Reason varchar(300),
    Refund_Email varchar(30),
    Refund_Image_Id INT,
    
    constraint pk_NonUser_Oreder_Detail_Refund primary key (Non_User_Order_Refund_Id, Non_User_Order_Detail_Id)
);

-- 싱품 상세내역
CREATE TABLE Products (
	Product_Id INT primary key,
    Category_Id INT,
    Product_Name varchar(20),
    Product_Price varchar(30),
    Product_Stock INT,
    Product_Desc TEXT,
    Product_Register_Date DATE,
    Product_Upadate_Date DATE
);

-- 상품 카테고리
CREATE TABLE Product_Category (
	Category_Id INT,
    Product_Type_Id INT,
    Category_Name varchar(50),
    
    constraint pk_Product_Category primary key (Category_Id, Product_Type_Id)
);

-- 상품 카테고리 타입
CREATE TABLE Product_Type (
	Product_Type_Id INT primary key,
    Product_Type_Name varchar(30)
);

-- 상품 옵션
CREATE TABLE Product_Option (
	Option_Id INT primary key,
    Product_Id INT,
	Option_Size varchar(30),
    Option_Color varchar(30),
    Option_Configuration varchar(30)
);



-- 상품 이미지
CREATE TABLE Product_ImageFile (
	Product_Image_Id INT,
    Product_Id INT,
    Product_ImageFile_Name varchar(40),
    
    constraint pk_Product_Image primary key (Product_Image_Id, Product_Id)
);

-- 장바구니
CREATE TABLE Product_Cart (
    Product_Id INT primary key,
    Cart_Value varchar(30),
    Product_Count INT
);

-- 상품 리뷰
CREATE TABLE Product_Review (
	Review_Id INT,
    Product_Id INT,
	Review_Horoscope double,
    Review_desc TEXT,
    Review_heart INT,
    Review_Register_Date DATE,
    Review_Update_Date DATE,
    Review_Register_Count INT,
    User_Name varchar(30),
    
    constraint pk_Product_Review primary key (Review_Id, Product_Id)
);

-- 상품 리뷰 이미지
CREATE TABLE Review_ImageFile (
	Review_Image_Id INT,
    Review_Id INT,
    Review_Image_Name varchar(30),
    
    constraint pk_Review_Image primary key (Review_Image_Id, Review_Id)
);

-- 상품 리뷰 댓글
CREATE TABLE Review_Comment (
	Comment_Id INT,
    Review_Id INT,
    Comment_Desc TEXT,
    User_Name varchar(30),
    
    constraint pk_Review_Comment primary key (Comment_Id, Review_Id)
);

-- 찜하기
CREATE TABLE WishLists (
	User_Id varchar(20),
    Product_Id INT,
    Product_Count INT,
    
    constraint pk_Review_Comment primary key (User_Id, Product_Id)
);

-- 1대1 문의 카테고리
CREATE TABLE Post_Category (
	Post_Category_Id INT primary key,
    Post_Category_Name varchar(30)
);

-- 문의 게시판
CREATE TABLE Inquiry_Posts (
	Inquiry_post_Id INT primary key,
    Post_Category_Id INT,
    Post_Writer varchar(30),
	Post_Phone varchar(30),
    Post_Title varchar(30),
    Post_Desc Text,
    Post_RegisterAt timestamp,
    Post_UpdateAt timestamp,
    Post_Inqury_Status varchar(1)
);

-- 문의 첨부 이미지
CREATE TABLE Inquiry_Post_Image (
	Inquiry_Post_Image_Id INT,
    Inquiry_post_Id INT,
    Inquiry_Post_Image_Name varchar(30),
    
    constraint  pk_Inquiry_Post_Image primary key (Inquiry_Post_Image_Id, Inquiry_post_Id)
);

-- 공지 사항
CREATE TABLE Notices (
	Notices_Id INT,
    Notices_Title varchar(30),
    Notices_RegisterAt timestamp,
    Notices_Desc Text,
    Notices_viewCount INT
);


ALTER TABLE Inquiry_Posts add constraint fk_Inquiry_Category foreign key (Post_Category_Id) references Post_Category (Post_Category_Id);

ALTER TABLE Inquiry_Post_Image add constraint fk_Inquiry_Image foreign key (Inquiry_post_Id) references Inquiry_Posts (Inquiry_post_Id);

ALTER TABLE User_Address add constraint fk_Users_Address foreign key (Address_Id) references Address(Address_Id);
ALTER TABLE User_Address add constraint fk_Address_Users foreign key (User_Id) references Users(User_Id);

ALTER TABLE Order_Address add constraint fk_Order_Address foreign key (Address_Id) references Address(Address_Id);
ALTER TABLE Order_Address add constraint fk_Address_Order foreign key (Order_Id) references User_Order(Order_Id);

ALTER TABLE User_detail add constraint fk_User_Detail foreign key (User_Id) references Users(User_Id);

ALTER TABLE User_Order add constraint fk_User_Order foreign key (User_Id) references Users(User_Id);

ALTER TABLE User_Order_Detail add constraint fk_Order_Detail foreign key(Order_Id) references User_Order(Order_Id);
ALTER TABLE User_Order_Detail add constraint fk_Order_Detail_Product foreign key(Product_Id) references Products(Product_Id);

ALTER TABLE User_Order_Refund add constraint fk_Order_Refund_Detail foreign key (Order_Detail_Id) references User_Order_Detail(Order_Detail_Id);
ALTER TABLE User_Order_Refund add constraint fk_Order_Refund_Image foreign key (Refund_Image_Id) references Refund_ImageFile(Refund_Image_Id);

ALTER TABLE User_Coupons add constraint fk_Users_Coupons foreign key (User_Id) references Users(User_Id);
ALTER TABLE User_Coupons add constraint fk_Coupons_Users foreign key (Coupon_Id) references Coupons(Coupon_Id);

ALTER TABLE Non_User_Order add constraint fk_NonUser_Order_Address foreign key (Receiver_Non_User_Address_Id) references Address(Address_Id);

ALTER TABLE Non_User_Order_Detail add constraint fk_NonUser_Order_Detail foreign key (Non_User_Order_Id) references Non_User_Order(Non_User_Order_Id);

ALTER TABLE Non_User_Order_Refund add constraint fk_NonUser_Order_Refund_Detail foreign key (Non_User_Order_Detail_Id) references Non_User_Order_Detail(Non_User_Order_Detail_Id);
ALTER TABLE Non_User_Order_Refund add constraint fk_NonUser_Order_Refund_Image foreign key (Refund_Image_Id) references Refund_ImageFile(Refund_Image_Id);

ALTER TABLE Products add constraint fk_Products_Category foreign key (Category_Id) references Product_Category(Category_Id);

ALTER TABLE Product_Category add constraint fk_Products_Category_Type foreign key (Product_Type_Id) references Product_Type(Product_Type_Id);

ALTER TABLE Product_Option add constraint fk_Products_Option foreign key (Product_Id) references Products(Product_Id);

ALTER TABLE Product_ImageFile add constraint fk_Products_Image foreign key (Product_Id) references Products(Product_Id);

ALTER TABLE Product_Cart add constraint fk_Products_Cart foreign key (Product_Id) references Products(Product_Id);

ALTER TABLE Product_Review add constraint fk_Products_Review foreign key (Product_Id) references Products(Product_Id);

ALTER TABLE Review_ImageFile add constraint fk_Review_Image foreign key (Review_Id) references Product_Review(Review_Id);

ALTER TABLE Review_Comment add constraint fk_Review_Comment foreign key (Review_Id) references Product_Review(Review_Id);

ALTER TABLE WishLists add constraint fk_WishLists_Users foreign key (User_Id) references Users(User_Id);
ALTER TABLE WishLists add constraint fk_WishLists_Products foreign key (Product_Id) references Products(Product_Id);

INSERT INTO Product_Type values (1, "매트리스");
INSERT INTO Product_Type values (2, "베개/팔로우");

INSERT INTO Product_Category values(1, 1, "매트리스");
INSERT INTO Product_Category values(2, 1, "커버");
INSERT INTO Product_Category values(3, 1, "스트랩");
INSERT INTO Product_Category values(4, 2, "베개");
INSERT INTO Product_Category values(5, 2, "팔로우");

INSERT INTO Products values (1, 1, "시그니처9", "149,000", 10, "아주 푹신합니다.", "2023-04-27", null);
INSERT INTO Products values (2, 1, "시그니처10", "139,000", 15, "아주 푹신합니다.", "2023-04-27", null);
INSERT INTO Products values (3, 2, "방수커버 프리미엄1", "60,000", 10, "부드러워요", "2023-04-27", null);
INSERT INTO Products values (4, 2, "방수커버 프리미엄2", "80,000", 17, "부드러워요", "2023-04-27", null);
INSERT INTO Products values (5, 3, "보관 스트랩1", "3,000", 10, "튼튼해요", "2023-04-27", null);
INSERT INTO Products values (6, 3, "보관 스트랩2", "8,000", 17, "튼튼해요", "2023-04-27", null);

INSERT INTO Users values (1, null, "Jeong", null, null, null, null);
INSERT INTO Users values (2, null, "Kang", null, null, null, null);

INSERT INTO User_Order values (1, 1, "2023-03-28", null, null, null, null);
INSERT INTO User_Order values (2, 1, "2023-03-23", null, null, null, null);
INSERT INTO User_Order values (3, 1, "2023-04-23", null, null, null, null);
INSERT INTO User_Order values (4, 2, "2023-01-28", null, null, null, null);
INSERT INTO User_Order values (5, 2, "2023-04-23", null, null, null, null);
INSERT INTO User_Order values (6, 2, "2023-03-23", null, null, null, null);

INSERT INTO User_Order_Detail values (1, 1, 1, 3, 447000, "Y");
INSERT INTO User_Order_Detail values (1, 2, 2, 3, 447000, "Y");
INSERT INTO User_Order_Detail values (1, 3, 3, 3, 447000, "Y");
INSERT INTO User_Order_Detail values (1, 4, 4, 3, 447000, "Y");
INSERT INTO User_Order_Detail values (1, 5, 5, 3, 447000, "Y");
INSERT INTO User_Order_Detail values (1, 6, 6, 3, 447000, "Y");

INSERT INTO Product_Review values (1, 1, 4.5, "아주 마음에 듭니다!", "4", "2023-04-29", null, null, "Jeong");
INSERT INTO Product_Review values (1, 2, 3.5, "아주 굿굿 듭니다!", "1", "2023-04-29", null, null, "Kang");
INSERT INTO Product_Review values (1, 3, 4.7, "아주 푹신 듭니다!", "3", "2023-04-29", null, null, "Jeong");
INSERT INTO Product_Review values (1, 4, 3.5, "아주 이리 듭니다!", "6", "2023-04-29", null, null, "Kang");
INSERT INTO Product_Review values (1, 5, 4.0, "아주 마음으이에 듭니다!", "7", "2023-04-29", null, null, "Jeong");
INSERT INTO Product_Review values (1, 6, 4.2, "아주 마마마 듭니다!", "4", "2023-04-29", null, null, "Jeong");

-- 사용자 모든 정보 출력
SELECT *
FROM Users;

-- 매트리스 상품에 "시그니처9"에 대한 정보 가져오기
SELECT *
FROM Products
where Product_Name = "시그니처9";

-- 타입이 매트리스인 상품 전부 가져오기
SELECT *
FROM Products as p join Product_Category as pc on p.Category_Id = pc.Category_Id
	join Product_Type as pt on pc.Product_Type_Id = pt.Product_Type_Id
Where pt.Product_Type_Name = "매트리스";

-- 매트리스 상품에 카테고리 "매트리스" 정보 가져오기
SELECT *
FROM Products as p join Product_Category as pc on p.Category_Id = pc.Category_Id
where Category_Name = "매트리스";

-- 상품 최신 등록 3개만 가져오기
SELECT *
FROM Products
Order by Product_Register_Date desc
limit 3;

-- "Jeong" 이 주문한 목록 가져오기
SELECT *
FROM Users as u join User_Order as uo on u.User_Id = uo.User_Id
WHERE u.User_Name = "Jeong";

-- "Jeong"이 주문한 총 개수
SELECT sum(Product_Count)
FROM Users as u join User_Order as uo on u.User_Id = uo.User_Id
	join User_Order_Detail as uod on uo.Order_Id = uod.Order_Id
WHERE u.User_Name = "Jeong";

-- "Jeong"이 주문한 총 금액
SELECT sum(Product_Price)
FROM Users as u join User_Order as uo on u.User_Id = uo.User_Id
	join User_Order_Detail as uod on uo.Order_Id = uod.Order_Id
WHERE u.User_Name = "Jeong";

-- 모든 사용자가 주문한 세부사항 보여주기
SELECT *
FROM Users as u join User_Order as uo on u.User_Id = uo.User_Id
	join User_Order_Detail as uod on uo.Order_Id = uod.Order_Id;

-- 모든 사용자들에 주문 내역을 최신 등록순으로 정렬
SELECT *
FROM Users as u join User_Order as uo on u.User_Id = uo.User_Id
	join User_Order_Detail as uod on uo.Order_Id = uod.Order_Id
Order By Order_Date desc;

-- 상품을 좋아요가 많은 순으로 나열
SELECT *
FROM Products as p join Product_Review as pr on p.Product_Id = pr.Product_Id
Order by Review_heart desc;

-- "보관 스트랩1"에 리뷰 좋아요 수를 가져오시오
SELECT sum(Review_heart)
FROM Products as p join Product_Review as pr on p.Product_Id = pr.Product_Id
Where Product_Name = "보관 스트랩1";

-- "매트리스" 타입에 상품에 모든 좋아요 수를 가져오시오
SELECT sum(Review_heart)
FROM Products as p join Product_Category as pc on p.Category_Id = pc.Category_Id
	join Product_Type as pt on pc.Product_Type_Id = pt.Product_Type_Id
    join Product_Review as pr on p.Product_Id = pr.Product_Id
WHERE pt.Product_Type_Name = "매트리스";

-- "매트리스"타입에 "매트리스" 카테고리에 종아요 수를 가져오시오.

SELECT sum(Review_heart)
FROM Products as p join Product_Category as pc on p.Category_Id = pc.Category_Id
	join Product_Type as pt on pc.Product_Type_Id = pt.Product_Type_Id
    join Product_Review as pr on p.Product_Id = pr.Product_Id
WHERE pc.Category_Name = "매트리스";
























