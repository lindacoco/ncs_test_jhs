package ncs_test_jhs.ui.content;

import ncs_test_jhs.dto.Department;
import ncs_test_jhs.ui.list.DepartmentTblPanel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbsItemPanel<Department> {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfDeptFloor;
	private DepartmentTblPanel deptTblPanel;

	public DepartmentPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		tfDeptNo.setEditable(false);
		add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);
		
		tfDeptName = new JTextField();
		add(tfDeptName);
		tfDeptName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfDeptFloor = new JTextField();
		add(tfDeptFloor);
		tfDeptFloor.setColumns(10);
	}

	@Override
	public Department getItem() {
		int deptNo = Integer.parseInt(tfDeptNo.getText());
		String deptName = tfDeptName.getText();
		int floor = Integer.parseInt(tfDeptFloor.getText());
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public void setItem(Department item) {
		tfDeptNo.setText(item.getDeptNo()+"");
		tfDeptName.setText(item.getDeptName());
		tfDeptFloor.setText(item.getFloor()+"");
	}

	@Override
	public void clearTf() {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfDeptFloor.setText("");		
	}

	@Override
	public void setFirstNum(int a) {
		// TODO Auto-generated method stub
		
	}
	
	public void setDeptNo(DepartmentTblPanel detpTblPanel) {
		tfDeptNo.setText(detpTblPanel.getLastIndex()+"");
	}

}
