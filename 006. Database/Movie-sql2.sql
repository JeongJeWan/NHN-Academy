

-- 1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER DBManager IDENTIFIED by '1234';

-- 2. User 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER User IDENTIFIED by '1234';

-- 3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
GRANT ALL PRIVILEGES on DatamotionMovieDatabase to DBManager;

-- 4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
GRANT SELECT ON *.* TO User;
GRANT INSERT, UPDATE ON DatamotionMovieDatabase.PersonTrivia TO User;
GRANT INSERT, UPDATE ON DatamotionMovieDatabase.MovieTrivia TO User;

SHOW FULL TABLES IN DatamotionMovieDatabase
WHERE TABLE_TYPE LIKE 'VIEW';
-- 5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
CREATE VIEW ActorInfo
AS
	SELECT p.*, m.*, r.*
    FROM Movie AS m INNER JOIN Appear AS a ON m.MovieID = a.MovieID
		INNER JOIN Role AS r ON r.RoleID = a.RoleID
        INNER JOIN Person AS p ON p.PersonID = a.PersonID
	WHERE RoleKorName = '배우';

select * from ActorInfo;

-- 6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
CREATE VIEW DirectorInfo
AS
	SELECT distinct p.*, m.*, r.*
    FROM Movie AS m INNER JOIN Appear AS a ON m.MovieID = a.MovieID
		INNER JOIN Role AS r ON r.RoleID = a.RoleID
        INNER JOIN Person AS p ON p.PersonID = a.PersonID
	WHERE RoleKorName = '감독';

select * from DirectorInfo;

-- 7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
CREATE VIEW AwardInfo
AS
	SELECT ay.*, m.*, p.*, s.*
    FROM
		awardinvolve AS al INNER JOIN sector AS s on al.SectorID = s.SectorID
        INNER JOIN awardyear AS ay on al.AwardYearID = ay.AwardYearID
        INNER JOIN winning AS w on w.WinningID = al.WinningID
        inner join award as aw on ay.AwardID = aw.AwardID
        inner join appear as ap on al.AppearID = ap.AppearID
        inner join person as p on ap.PersonID = p.PersonID
        inner join movie as m on m.MovieID = ap.MovieID
	where w.winningID = 2
    order by ay.Date;

SELECT * FROM AwardInfo;

-- 8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
CREATE VIEW GenreInfo
AS
	select GenreKorName ,AVG(Budget) as budget, AVG(BoxOfficeWWGross) as BoxOfficeWWGross
    from 
		movie as m inner join MovieGenre as mg on m.MovieID = mg.MovieID
        inner join Genre as g on g.GenreId = mg.GenreID
	group by g.GenreID;
    
select * from GenreInfo;

-- 9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
select count(*)
from DirectorInfo
where KoreanTitle = '매트릭스';

-- 10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
select title, KoreanTitle
	from ActorInfo
where KoreanName = '레오나르도 디카프리오' and RunningTime >= 100;


-- 11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요)
select m.title, m.KoreanTitle
from movie as m
	inner join appear as a on a.MovieID = m.MovieId
    inner join role as r on r.RoleID = a.RoleID
    inner join person as p on p.PersonID = a.PersonID
where KoreanName = "알란 실버스트리" and RoleKorName = "작곡"
union
select m.Title, m.KoreanTitle
from movie as m
	inner join appear as a on a.MovieID = m.MovieId
    inner join role as r on r.RoleID = a.RoleID
    inner join person as p on p.PersonID = a.PersonID
where KoreanName = "아론 소킨" and RoleKorName = "각본가";

-- 12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
select actor.name, avg(boxofficewwGross)/avg(Budget) * 100 rateOfReturn
from
(select title
from movie as m join appear as a on m.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where KoreanName = "쿠엔틴 타란티노" and r.RoleKorName = "감독") as director
inner join
(select title, Budget, BoxOfficeWWGross, Name
from movie as m join appear as a on m.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where r.RoleKorName = "배우") as actor on director.title = actor.title
group by actor.name;

-- 13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.
CREATE VIEW ComposeInfo
AS
	select m.*, r.*, p.*
	from movie as m
		inner join appear as a on a.MovieID = m.MovieId
		inner join role as r on r.RoleID = a.RoleID
		inner join person as p on p.PersonID = a.PersonID
	where RoleKorName = "작곡";

CREATE VIEW WriterInfo
AS
	select m.*, r.*, p.*
	from movie as m
		inner join appear as a on a.MovieID = m.MovieId
		inner join role as r on r.RoleID = a.RoleID
		inner join person as p on p.PersonID = a.PersonID
	where RoleKorName = "각본가";

-- 14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.
select KoreanName, KoreanTitle
from ComposeInfo
where KoreanName = "알란 실버스트리" 
union
select KoreanName, KoreanTitle
from WriterInfo
where KoreanName = "아론 소킨";




-- 14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
CREATE TABLE User (
	userID INT primary key auto_increment,
    Name varchar(20) NOT NULL,
    Password varchar(20) NOT NULL,
    Email varchar(20) NOT NULL
    );

-- 15. 사용자가  MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고, 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
Alter Table MovieTrivia Add UserID INT;
Alter Table PersonTrivia Add UserID INT;

-- 16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
Alter table MovieTrivia add constraint fk_MovieTrive_User foreign key(UserID) references User(UserID);
Alter table PersonTrivia add constraint fk_PersonTrivia_User foreign key(UserID) references User(UserID);

-- 17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입할 수 있도록 권한을 설정하세요.
	-- 4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
	GRANT SELECT ON *.* TO User;
	GRANT INSERT, UPDATE ON DatamotionMovieDatabase.PersonTrivia TO User;
	GRANT INSERT, UPDATE ON DatamotionMovieDatabase.MovieTrivia TO User;
	-- 4번 문제에서 권한을 미리 설정하였습니다.

-- 18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE insert_movie_trivia (
  IN trivia_id INT,
  IN movie_id INT
)
BEGIN
  INSERT INTO MovieTrivia (TriviaID, MovieID)
  VALUES (trivia_id, movie_id);
END $$
DELIMITER ;

call insert_movie_trivia (800, 200);
select * from MovieTrivia;

-- 19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE insert_person_trivia (
  IN trivia_id INT,
  IN movie_id INT
)
BEGIN
  INSERT INTO PersonTrivia (TriviaID, MovieID)
  VALUES (trivia_id, movie_id);
END $$
DELIMITER ;


-- 20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE find_movies_by_director (
  IN director_name VARCHAR(255)
)
BEGIN
  SELECT KoreanTItle
  FROM DiretorInfo
  where KoreanName = director_name;
END $$
DELIMITER ;
drop PROCEDURE find_movie_by_person_name;

call find_movies_by_director("쿠엔틴 타란티노");

-- 21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE find_actors_by_movie (
  IN movie_title VARCHAR(255)
)
BEGIN
  SELECT KoreanName
  FROM ActorInfo
  WHERE Title = movie_title and RoleKorName = '배우';
END$$
DELIMITER ;

call find_actors_by_movie("Avatar");

-- 22. 주어진 영화에 참여한, 감독, 각본, 배우를  제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE find_by_movie (
  IN movie_title VARCHAR(255)
)
BEGIN
  SELECT KoreanName, RoleKorName
  FROM Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
	inner join Role as r on r.RoleID = ap.RoleID
    inner join Person as p on p.PersonID = ap.PersonID
  WHERE RoleKorName NOT IN (
	select RoleKorName
    from Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
	inner join Role as r on r.RoleID = ap.RoleID
    inner join Person as p on p.PersonID = ap.PersonID
    where r.RoleKorName = "감독" or r.RoleKorName = "각본가" or r.RoleKorName = "배우");
END$$
DELIMITER ;

call find_by_movie("아바타");

-- 23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE find_movie_by_person_name (
  IN person_name VARCHAR(255)
)
BEGIN
  SELECT distinct Title
  FROM Movie as m inner join Appear as ap on m.MovieID = ap.MovieID
    inner join Person as p on p.PersonID = ap.PersonID
  WHERE KoreanName = person_name; 
END$$
DELIMITER ;

call find_movie_by_person_name("쿠엔틴 타란티노");

-- 24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요.
DELIMITER $$
CREATE PROCEDURE find_movie_by_genre_n (
  IN genre_n VARCHAR(255)
)
BEGIN
  SELECT * , boxOfficeWWGross/Budget * 100
  FROM GenreInfo
  where GenreKorName = genre_n;
END$$
DELIMITER ;

call find_movie_by_genre_n("액션");

select * from ratingsource;

-- <수업하지 않은 내용>
-- 25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다. 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고, 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.
-- 우리가 만든게 input 파라미터
DELIMITER $$
CREATE PROCEDURE get_movie_info(
    IN movie_id INT
)
BEGIN
    Select Title, KoreanTitle, Plot, ReleaseYear, RunningTime, GradeName, GradeInKoreaName,ReleaseDateInKorea, 
	BoxOfficeWWGross, Budget, OriginalAuthor, OriginalSource, group_concat(distinct GenreName),group_concat(distinct GenreKorName),
    group_concat(distinct contents), group_concat(distinct Remark), group_concat(distinct Year), group_concat(distinct Date), group_concat(distinct Location), 
    group_concat(distinct SectorName), group_concat(distinct SectorKorName), group_concat(distinct AwardEnglishTitle), group_concat(distinct AwardKoreanTitle),group_concat(distinct Description)t
    FROM Movie as m
		left outer join gradeinkorea as gk on gk.GradeInKoreaID = m.GradeInKoreaID
        left outer join grade as gd on gd.GradeID = m.GradeID
        left outer join moviegenre as mg on mg.movieID = m.MovieID
        left outer join genre as g on g.GenreID = mg.GenreID
        left outer join MovieTrivia as mt on mt.MovieID = m.MovieID
        left outer join trivia as t on t.TriviaID = mt.TriviaID
        left outer join rating as rt on rt.MovieID = m.MovieID
        left outer join ratingsource as rs on rs.RatingSourceID = rt.RatingSourceID
		left outer join appear as a on m.MovieID = a.MovieID
        left outer join awardinvolve as ai on ai.AppearID = a.AppearID
        left outer join awardyear as ay on ay.AwardYearID = ai.AwardYearID
        left outer join award as aw on aw.AwardID = ay.AwardID
        left outer join sector as s on s.SectorID = ai.SectorID
        left outer join winning as w on w.WinningID = ai.WinningID
    WHERE m.MovieID = movie_id
GROUP BY Title, KoreanTitle, Plot, ReleaseYear, RunningTime, GradeName, GradeInKoreaName,ReleaseDateInKorea, 
	BoxOfficeWWGross, Budget, OriginalAuthor, OriginalSource;
END $$
DELIMITER ; 


call get_movie_info(1); 


drop procedure get_movie_info;  





