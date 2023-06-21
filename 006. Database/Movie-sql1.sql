create database DatamotionMovieDatabase;
drop database DatamotionMovieDatabase;
use DatamotionMovieDatabase;

show databases;

show tables;

-- role : 역할, movie : 영화, person : 사람, appear : movie, person, role의 id
-- awardinvolve : 수상관련, gradeinkorea : 관람가 연령, moviegenre : 영화와 장르 연결, genre : 장르(드라마 코미디 등)
-- sector : 상부문
SELECT * FROM person;
SELECT * FROM role;
SELECT * FROM movie;
SELECT * FROM appear;
SELECT * FROM awardinvolve;
SELECT * FROM gradeinkorea;
SELECT * FROM moviegenre;
SELECT * FROM genre;
SELECT * FROM sector;

-- 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
select ReleaseYear, Title, RunningTime, Plot
from movie
where KoreanTitle = '퍼스트 맨';

-- 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
select KoreanTitle, Title
from Movie
where ReleaseYear = 2003;

-- 3. 영화 '글래디에이터'의 작곡가를 고르세요
select p.name
	from movie as f join appear as a on f.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
	where KoreanTitle = '글래디에이터' and r.RoleID = 27;

-- 4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
select count(RoleKorName)
	from movie as f join appear as a on f.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
	where KoreanTitle = '매트릭스' and r.RoleID = 2;

-- 5. 감독이 2명 이상인 영화를 출력하세요
select title
	from movie as m join appear as a on m.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where r.RoleID = 2
group by m.MovieId
Having count(RoleKorName) > 2;

-- 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
select title, KoreanTitle
	from movie as f join appear as a on f.MovieId = a.MovieID
	join Person as p on a.PersonId = p.PersonId
    join awardinvolve as aw on a.AppearID = aw.AppearId
	where KoreanName = '한스 짐머' and aw.WinningId = 2;
	

-- 7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
select director.title
from
(select title, KoreanName
from movie as m join appear as a on m.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where RoleKorName = '감독' and KoreanName = '제임스 카메론') as director
inner join
(select title, KoreanName
from movie as m join appear as a on m.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where KoreanName = '아놀드 슈워제네거') as actor on director.title = actor.title;

-- 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
select title
	from movie as f join appear as a on f.MovieId = a.MovieID
    join role as r on r.RoleId = a.RoleID
	join Person as p on a.PersonId = p.PersonId
where r.RoleID = 6 and KoreanName = '레오나르도 디카프리오' and RunningTime >= 100;

-- 9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
select * from gradeinkorea;

select Title, GradeInKoreaName
from Movie as m inner join GradeInKorea as g on m.GradeInKoreaID = g.GradeInKoreaID
where g.GradeInKoreaID = 4 and BoxOfficeWWGross = (
	Select Max(BoxOfficeWWGross)
    from movie as m inner join GradeInKorea as g on m.GradeInKoreaID = g.GradeInKoreaID
    where g.GradeInKoreaID = 4
);

-- 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
select AVG(BoxOfficeWWGross)
from Movie
where ReleaseYear < 1999;

-- 11. 가장 많은 제작비가 투입된 영화를 감독한 사람은 누구입니까?
select KoreanName
from Movie as m
	inner join Appear as a on m.MovieID = a.MovieID
	inner join role as r on a.RoleID = r.RoleID
	inner join Person as p on p.PersonId = a.PersonID
where r.RoleID = 2 and Budget = (
	select Max(Budget)
    from Movie
);

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
select name
from Movie as m
	inner join Appear as a on m.MovieID = a.MovieID
	inner join role as r on a.RoleID = r.RoleID
	inner join Person as p on p.PersonId = a.PersonID
where r.RoleID = 2
group by name
having sum(Budget) = (
select max(data)
from (
select sum(budget) as data
from Movie as m
	inner join Appear as a on m.MovieID = a.MovieID
	inner join role as r on a.RoleID = r.RoleID
	inner join Person as p on p.PersonId = a.PersonID
    where r.RoleID = 2
    group by name) as field
);


-- 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
select name
from Movie as m
	inner join Appear as a on m.MovieID = a.MovieID
	inner join role as r on a.RoleID = r.RoleID
	inner join Person as p on p.PersonId = a.PersonID
where r.RoleID = 6 or r.RoleID = 7
group by name
having sum(BoxOfficeWWGross) = (
select max(data)
from (
select sum(BoxOfficeWWGross) as data
from Movie as m
	inner join Appear as a on m.MovieID = a.MovieID
	inner join role as r on a.RoleID = r.RoleID
	inner join Person as p on p.PersonId = a.PersonID
    where r.RoleID = 6 or r.RoleID = 7
    group by name) as field
);

-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
select KoreanTitle, budget
from Movie
where budget > 0
order by budget ASC
limit 1;

select KoreanTitle, Budget from movie where KoreanTitle = '아쿠아마니아';

-- 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
select avg(BoxOfficeUSGross)
from Movie
where budget <= 50000000;

-- 16. 액션 장르 영화의 평균 수익을 집계하세요.
select avg(BoxOfficeWWGross)
from
	movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
where g.GenreID = 4;

-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
select title
from
	movie as m inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join Genre as g on g.GenreID = mg.GenreID
where GenreKorName = '드라마' or GenreKorName = '전쟁';

-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
select Title, KoreanTitle, ReleaseYear
from
	movie as m inner join appear as a on m.MovieID = a.MovieID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on a.PersonID = p.PersonID
where r.RoleID = 6 and KoreanName = '톰 행크스' and RunningTime = (
	select Max(RunningTime)
from 
	movie as m inner join appear as a on m.MovieID = a.MovieID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on a.PersonID = p.PersonID
where r.RoleID = 6 and KoreanName = '톰 행크스'
);

-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
select name
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
    where SectorKorName = '남우주연상' and WinningID = '2' -- WinningID =2 수상한거, 1 수상안한 거
group by name
having count(SectorKorName) = (
select Max(count)
from (select count(SectorKorName) as count
	from 
    person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearId = aw.AppearId
	inner join sector as s on aw.SectorID = s.SectorID
	where SectorKorName = '남우주연상' and WinningID = '2'
	group by name) as result
);

-- 20. 아카데미상을 가장 많이 수상한 배우을 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
select name, count(WinningID)
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
    inner join role as r on r.RoleId = a.RoleID
    where SectorKorName != '수상자 없음' and WinningID = '2' and r.RoleID = 6
group by name
having count(WinningID) = (
select Max(count)
from (select count(WinningID) as count
	from 
    person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearId = aw.AppearId
	inner join sector as s on aw.SectorID = s.SectorID
    inner join role as r on r.RoleId = a.RoleID
    where SectorKorName != '수상자 없음' and WinningID = '2' and r.RoleID = 6
	group by name) as result
);
#group by name
#order by count(WinningID) desc
#limit 3;

-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
select name
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
    where SectorKorName = '남우주연상' and WinningID = '2'
group by name
having count(SectorKorName) >= 2;

-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
select name, count(WinningId)
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
    inner join role as r on r.RoleId = a.RoleID
    where KoreanName != '수상자 없음' and WinningID = '2'
group by name
having count(WinningID) = (
select Max(count)
from (select count(WinningID) as count
	from 
    person as p inner join appear as a on p.PersonID = a.PersonID
	inner join awardinvolve as aw on a.AppearId = aw.AppearId
	inner join sector as s on aw.SectorID = s.SectorID
    inner join role as r on r.RoleId = a.RoleID
    where KoreanName != '수상자 없음' and WinningID = '2'
	group by name) as result
);

-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
select title
from 
	Movie as m inner join appear as a on m.MovieID = a.MovieID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
where WinningId = 1
group by title
having count(WinningID) = (
select Max(count)
from (select count(WinningID) as count
	from 
    Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join awardinvolve as aw on a.AppearId = aw.AppearId
	inner join sector as s on aw.SectorID = s.SectorID
    where WinningId = 1
	group by title) as result
);

select * from sector;

-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where r.RoleID = 7
Group by p.PersonId
order by count(m.MovieID) desc
limit 1;

-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
select title
from Movie
order by BoxOfficeWWGross desc
limit 10;

-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
select title
from Movie
where BoxOfficeWWGross >= 1000000000 and budget <= 100000000;

-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
select name
from Movie as m
	inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.movieID = a.MovieID
    inner join genre as g on g.GenreID = mg.GenreID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on p.PersonId = a.PersonID
where GenreKorName = '전쟁' and r.RoleID =2
group by p.PersonId
order by count(m.MovieID) desc
limit 1;


-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
select name, count(m.MovieID)
from Movie as m
	inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.movieID = a.MovieID
    inner join genre as g on g.GenreID = mg.GenreID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on p.PersonId = a.PersonID
where r.RoleID=6 or r.RoleID = 7 and GenreKorName = '드라마'
group by p.PersonId
order by count(m.MovieId) desc
limit 1;

-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
select name, GenreKorName
from Movie as m
	inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.movieID = a.MovieID
    inner join genre as g on g.GenreID = mg.GenreID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on p.PersonId = a.PersonID
where r.RoleID=6 or r.RoleID = 7 and GenreKorName = '드라마' and GenreKorName != '호러';

-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
select Location
from awardyear
group by Location
order by count(AwardId) desc
limit 1;

select * from award;
select * from awardyear;
-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
select 2023 - min(year)
from awardyear;

-- 34. 아카데미에 가장 많이 노미네이트된 사람은 누구인가요?
select name
	FROM Movie AS m JOIN appear AS a ON m.MovieID = a.MovieID
	JOIN Person AS p ON a.PersonID = p.PersonID
	JOIN awardinvolve AS aw ON a.appearID = aw.appearID
	JOIN Winning AS w ON w.WinningID = aw.WinningID
where WinningId = 1 and SectorKorName != "수상자 없음"
group by p.personID
order by count(WinningId) desc
limit 1;
SELECT p.Name, COUNT(p.Name)
FROM Movie AS m JOIN appear AS a ON m.MovieID = a.MovieID
JOIN Person AS p ON a.PersonID = p.PersonID
JOIN awardinvolve AS aw ON a.appearID = aw.appearID
JOIN Winning AS w ON w.WinningID = aw.WinningID
WHERE w.WinningID = 1 AND KoreanName != '수상자 없음'
GROUP BY p.PersonID
ORDER BY COUNT(p.Name) DESC
LIMIT 1;

-- 35. 1999년에서 2009년 사이에 남우 주연상을 수상한 배우를 모두 고르세요.
select name
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
    inner join awardyear as ay on ay.AwardYearID = aw.AwardYearID
    where SectorKorName = '남우주연상' and WinningID = '2' and 1999<=Year and Year<=2009;

-- 36. 아카데미를 한번 수상한 후, 가장 짧은 기간에 한번 더 수상한 사람을 고르세요.
-- 37. '제임스 카메론'의 영화에 출연한 모든 배우를 고르세요
Select name
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
Where RoleKorName = '배우' and title in (select distinct title
From Movie as m inner join appear as a on m.MovieID = a.MovieID
	inner join role as r on r.RoleID = a.RoleID
    inner join Person as p on p.PersonID = a.PersonID
where r.RoleID = 2 and KoreanName = "제임스 카메론");

-- 38. '월드 디즈니'가 수상한 아카데미상의 종류를 고르세요
select distinct SectorKorName
from 
	person as p inner join appear as a on p.PersonID = a.PersonID
    inner join awardinvolve as aw on a.AppearId = aw.AppearId
    inner join sector as s on aw.SectorID = s.SectorID
where aw.WinningId = 2 and KoreanName = "월트 디즈니";


-- 39. 장르별 영화의 제작비 평균을 구하세요.
select avg(Budget)
from Movie as m
	inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.movieID = a.MovieID
    inner join genre as g on g.GenreID = mg.GenreID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on p.PersonId = a.PersonID
group by GenreKorName;
-- 40. 장르별 영화의 제작비 대비 수익률을 구하세요.
select GenreKorName, avg(boxofficewwGross)/avg(Budget) * 100 rateOfReturn
from Movie as m
	inner join moviegenre as mg on m.MovieID = mg.MovieID
    inner join appear as a on m.movieID = a.MovieID
    inner join genre as g on g.GenreID = mg.GenreID
    inner join role as r on a.RoleID = r.RoleID
    inner join Person as p on p.PersonId = a.PersonID
group by GenreKorName;



























