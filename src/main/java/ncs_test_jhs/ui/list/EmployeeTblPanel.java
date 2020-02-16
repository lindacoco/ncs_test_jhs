package ncs_test_jhs.ui.list;

import java.util.Date;

import javax.swing.SwingConstants;

import ncs_test_jhs.dto.Department;
import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.dto.Title;

public class EmployeeTblPanel extends AbsTblPanel<Employee> {

	@Override
	protected Object[] toArray(Employee list) {
		
		return new Object[] {
			list.getEmpNo(),
			list.getEmpName(),
			list.getTitle().getTitleName(),
			String.format("%,d", list.getSalary()),
			list.getGender()==0?"남자":"여자",
			String.format("%s(%s층)", list.getDno().getDeptName(),list.getDno().getFloor()),
			String.format("%tF", list.getHireDate())
		};
	}

	@Override
	protected String[] getColumnNames() {
		
		return new String[] {"번호","사원명","직책","급여","성별","부서","입사일"};
	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(100,70,100,100,70,100,100);
		tableCellAlign(SwingConstants.CENTER, 0,1,2,4,5,6);
		tableCellAlign(SwingConstants.RIGHT, 3);
		
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpNo(), updateIdx, 0);
		model.setValueAt(item.getEmpName(), updateIdx, 1);
		model.setValueAt(item.getTitle(), updateIdx, 2);
		model.setValueAt(String.format("%,d", item.getSalary()), updateIdx, 3);
		model.setValueAt(item.getGender(), updateIdx, 4);
	    model.setValueAt(item.getDno(), updateIdx, 5);
	    model.setValueAt(item.getHireDate(), updateIdx, 6);
	} 

	@Override
	public Employee getSelectedItem() {
		int selectedIdx = getSelectdRowIndex();
		int empNo = (int) model.getValueAt(selectedIdx, 0);
		String empName = (String) model.getValueAt(selectedIdx, 1);
		Title title = (Title) model.getValueAt(selectedIdx, 2);
		int salary = (int) model.getValueAt(selectedIdx, 3);
		int gender = getGenderInt();
		Department dno = (Department) model.getValueAt(selectedIdx, 5);
		Date hireDate = (Date) model.getValueAt(selectedIdx, 6);

		return new Employee(empNo, empName, title, salary, dno, gender, hireDate);
	}

	private int getGenderInt() {
		int selectedIdx = getSelectdRowIndex();
		String a = (String) model.getValueAt(selectedIdx, 4);
		if(a.contentEquals("여자")) {
			return 1;
		}
		return 0;
	}

}
