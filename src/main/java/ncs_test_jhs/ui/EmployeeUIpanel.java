package ncs_test_jhs.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_jhs.dto.Employee;
import ncs_test_jhs.ui.content.EmployeePanel;
import ncs_test_jhs.ui.list.EmployeeTblPanel;
import ncs_test_jhs.ui.service.EmployeeUiService;
import ncs_test_jhs.util.LogUtil;

public class EmployeeUIpanel extends JPanel implements ActionListener {
	private EmployeeUiService service;
	private EmployeeTblPanel pEmpTblPanel;
	private EmployeePanel pEmpPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	
	public EmployeeUIpanel() {
		service= new EmployeeUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pEmpPanel = new EmployeePanel();
		add(pEmpPanel);
		pEmpPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel btns = new JPanel();
		add(btns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btns.add(btnAdd);
		
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btns.add(btnCancel);
		
		
		pEmpTblPanel = new EmployeeTblPanel();
		add(pEmpTblPanel);
		pEmpTblPanel.setLayout(new BoxLayout(pEmpTblPanel, BoxLayout.X_AXIS));
		
		pEmpTblPanel.loadData(service.showEmployeeList222());
		pEmpTblPanel.setPopupMenu(createPopupMenu());
		
		pEmpPanel.setService(service);
		
	    pEmpPanel.setEmpNo(pEmpTblPanel);

	}
	private JPopupMenu createPopupMenu() {
        JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
  
	ActionListener myPopMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("수정")) {
				btnAdd.setText("수정");
				Employee upEmp = pEmpTblPanel.getSelectedItem();
				pEmpPanel.setItem(upEmp);
				
			}
			if (e.getActionCommand().equals("삭제")) {
				Employee delEmp = pEmpTblPanel.getSelectedItem();
				service.removeEmployee(delEmp);
				pEmpTblPanel.removeRow();
				pEmpPanel.setEmpNo(pEmpTblPanel);
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		}
	};
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if (e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
				pEmpPanel.setEmpNo(pEmpTblPanel);
			}else {
				btnUpdateActionPerformed(e);
			}
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		
	}
	
	private void btnUpdateActionPerformed(ActionEvent e) {
		Employee upEmp = pEmpPanel.getItem();
		service.modifyEmployee(upEmp);
		
		pEmpTblPanel.updateRow(upEmp, pEmpTblPanel.getSelectdRowIndex());
		
		btnAdd.setText("추가");
		pEmpPanel.clearTf();
		JOptionPane.showMessageDialog(null, "부서가 수정되었습니다.");		
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
		pEmpPanel.clearTf();
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Employee newEmp = pEmpPanel.getItem();
			
			System.out.println(newEmp); //[6 dd 6 1500000 2 0 2020-02-16 18:22:28]
			LogUtil.prnLog(newEmp.toString());
			service.addUpEmployee(newEmp);
			pEmpTblPanel.addItem(newEmp);
			pEmpPanel.clearTf();
			
//			JOptionPane.showMessageDialog(null, String.format("%s(%d) 추가되었습니다.", newEmp.getEmpName(), newEmp.getEmpNo()));
		
		}catch(Exception e1) {
			System.out.println(e1.getStackTrace());
			e1.printStackTrace();
		}
	}
}
