package ncs_test_jhs.dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dno;
	private int gender;
	private Date hireDate;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dno) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
	}
	
	


	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dno, int gender,
			Date hireDate) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
		this.gender = gender;
		this.hireDate = hireDate;
	}

	
	
	public Employee(int empNo, String empName, Title title, int salary, Department dno, int gender, Date hireDate) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.dno = dno;
		this.gender = gender;
		this.hireDate = hireDate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Employee(int empNo) {
		super();
		this.empNo = empNo;
	}

	

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dno == null) ? 0 : dno.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + empNo;
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + salary;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dno == null) {
			if (other.dno != null)
				return false;
		} else if (!dno.equals(other.dno))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (empNo != other.empNo)
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (salary != other.salary)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return String.format("[%s %s %s %s %s %s %s %s]", 
                 empNo,
                 empName,
                 title.getTitleNo(),
                 manager.getEmpNo(),
                 salary,
                 dno.getDeptNo(),
                 gender,
                 String.format("%1$tF %1$tT", hireDate)
    );
//		return "Employee [empNo=" + empNo + ", empName=" + empName + ", title=" + title.getTitleNo() + ", manager=" + manager.empNo
//				+ ", salary=" + salary + ", dno=" + dno.getDeptNo() + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}

	

}
