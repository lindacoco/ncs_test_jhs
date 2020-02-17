package ncs_test_jhs.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import ncs_test_jhs.dto.Department;
import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.ui.EmployeeUIpanel;
import ncs_test_jhs.ui.list.EmployeeTblPanel;
import ncs_test_jhs.ui.service.EmployeeUiService;

public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JDateChooser tfHireDate;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDno;
	private JSpinner spinner;
	private JPanel pGender;
	private JRadioButton radioMale;
	private EmployeeUiService service;
	private EmployeeTblPanel empTblPanel;
	private EmployeePanel empPanel;
	private JRadioButton radioFemale; 

	
	
	public EmployeePanel() {
	    service= new EmployeeUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pEmp = new JPanel();
		add(pEmp, BorderLayout.CENTER);
		pEmp.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblEmpNo = new JLabel("번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		tfEmpNo.setEditable(false);
		pEmp.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblEmpName);
		
		tfEmpName = new JTextField();
		pEmp.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pEmp.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblSalary);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		pEmp.add(spinner);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblGender);
		
		pGender = new JPanel();
		pEmp.add(pGender);
		pGender.setLayout(new GridLayout(1, 0, 0, 0));
		
		radioMale = new JRadioButton("남");
		radioMale.setSelected(true);
		radioMale.setHorizontalAlignment(SwingConstants.RIGHT);
		pGender.add(radioMale);
		
		radioFemale = new JRadioButton("여");
		pGender.add(radioFemale);

		JLabel lblDno = new JLabel("부서");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblDno);
		
		cmbDno = new JComboBox<>();
		pEmp.add(cmbDno);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblHireDate);
		
		tfHireDate = new JDateChooser(new Date(),"yyyy-MM-dd");
		pEmp.add(tfHireDate);
		
	    
		
		
	}
	public void setService(EmployeeUiService service) {
		this.service = service;
		setCmbTitleList(service.showTitleList());
		setCmbDeptList(service.showDepartmentList());

	}
	
	private void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDno.setModel(model);
		cmbDno.setSelectedIndex(-1);
	}
	private void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(-1);		
	}
	//콤보박스에 붙여주기
	public JComboBox<Department> getCmbDept(){
		return cmbDno;
	}
	public JComboBox<Title> getCmbTitle(){
		return cmbTitle; 
	}
	@Override
	public Employee getItem() {
		if(radioFemale.isSelected() == true) {
			radioMale.setSelected(false);
		}
		
	    int empNo = Integer.parseInt(tfEmpNo.getText().trim());
	    String empName = tfEmpName.getText().trim();
        
	    Title title = (Title) cmbTitle.getSelectedItem();
	    Employee manager = null;
	    int salary = (int) spinner.getValue();
	    Department dno = (Department) cmbDno.getSelectedItem();
	    int gender = getGender();
	    Date hireDate = tfHireDate.getDate();	
		return new Employee(empNo, empName, title, manager, salary, dno, gender, hireDate);
	}

	private int getGender() {
		if(radioMale.isSelected()==true) { //남자면
			return 0;
		}else {
			return 1;
		}
	}
	@Override
	public void setItem(Employee item) {
		tfEmpNo.setText(item.getEmpNo()+"");
	    tfEmpName.setText(item.getEmpName()+"");
	    cmbTitle.setSelectedItem(item.getTitle()+"");
	    spinner.setValue(item.getSalary()+"");
	    cmbDno.setSelectedItem(item.getDno()+"");
	    radioMale.setSelected(setGender(item));
	    tfHireDate.setDate(item.getHireDate());		
	}
	private boolean setGender(Employee item) {
		if(item.getGender() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void clearTf() {
//		empPanel.setEmpNo(empTblPanel);
		tfEmpNo.setText(""); //이거 나중에 수정 
//		tfEmpNo.setText(empTblPanel.getLastIndex()+"");
	    tfEmpName.setText("");
	    cmbTitle.setSelectedIndex(-1);
	    spinner.setValue(1500000);
	    cmbDno.setSelectedIndex(-1);
	    radioMale.setSelected(true);
	    tfHireDate.setDate(new Date());		
	}
	@Override
	public void setFirstNum(int a) {
		// TODO Auto-generated method stub
		
	}
	
	public void setEmpNo(EmployeeTblPanel empTblPanel) {
		tfEmpNo.setText(empTblPanel.getLastIndex()+"");
	}

}
