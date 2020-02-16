select  user(), database ();
use ncs_test_jhs;

desc department;
desc title;
desc employee;

insert into department values
(1,'영업',8),
(2,'개발',10),
(3,'총무',11);

insert into title values
(1,'대표이사'),
(2,'부장'),
(3,'과장'),
(4,'사원');



insert into employee values
(1,'김민수',1,1,5000000,1,0,'2000-01-01'),
(2,'장현서',2,1,4000000,1,1,'2005-01-01'),
(3,'송초코',3,2,3000000,3,1,'2008-08-01'),
(4,'박딸기',4,3,2000000,2,1,'2010-01-01'),
(5,'이수박',4,3,2000000,2,0,'2015-08-01');