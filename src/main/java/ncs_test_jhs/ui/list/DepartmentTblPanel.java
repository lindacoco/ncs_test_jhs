package ncs_test_jhs.ui.list;

import javax.swing.SwingConstants;

import ncs_test_jhs.dto.Department;

@SuppressWarnings("serial")
public class DepartmentTblPanel extends AbsTblPanel<Department> {

	@Override
	protected Object[] toArray(Department list) {
		// TODO Auto-generated method stub
		return new Object[] {
				list.getDeptNo(),
				list.getDeptName(),
				list.getFloor()
		};
	}

	@Override
	protected String[] getColumnNames() {
		
		return new String[] {"번호","부서명","위치"};
	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50,100,50);
		tableCellAlign(SwingConstants.CENTER, 0,1,2);
		
	}

	@Override
	public void updateRow(Department item, int updateIdx) {
		model.setValueAt(item.getDeptNo(), updateIdx, 0);
		model.setValueAt(item.getDeptName(), updateIdx, 1);
		model.setValueAt(item.getFloor(), updateIdx, 2);
		
	}

	@Override
	public Department getSelectedItem() {
		int selectedIdx = getSelectdRowIndex();
		int deptNo = (int) model.getValueAt(selectedIdx, 0);
		String deptName = (String) model.getValueAt(selectedIdx, 1);
		int floor = (int) model.getValueAt(selectedIdx, 2);
		return new Department(deptNo, deptName, floor);
	}
	
	
	public int getLastIndex() {
		int lastIdx = getLastRowIndex();
		int num =(int) model.getValueAt(lastIdx-1, 0) ;  //테이블 인덱스가 0 부터 시작해서 
		return num+1;
	}
	

}
