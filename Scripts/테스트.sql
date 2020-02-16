select user(), database ();


select deptno, deptname, floor from department;
select titleno, titlename from title;
select empno,empname, title, manager, salary, dno from employee;
select * from employee e ;

select titleno, titlename from title where titleno=1;

delete from title where titleno= 5;

select deptno, deptname, floor from department where deptno =1;

insert into department values (4,'꿀보',11);
update department set deptname ='꿀꿀' ,floor= 8 where deptno =4;
delete from department where deptno =4;

select empno,empname, title, manager, salary, dno from employee where empno=1;

insert into employee(empno,empname,title,manager,salary,dno,gender,hiredate) values (6,'방송국',3,2,3000000,1,0,'2009-05-01');
update employee set empname='권민수',title=3, manager=2, salary=5000000,dno=1, gender =0, hiredate ='2018-07-27' where empno=6;
delete from employee where empno=6;

select e.empno, e.empname, t.titleno, t.titlename , e.salary, e.gender, d.deptname, d.deptno, d.floor, e.hiredate
  from employee e left join title t on e.dno = t.titleno left join department d on e.dno =d.deptno ;