package ncs_test_jhs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_jhs.dto.Department;
import ncs_test_jhs.ui.content.DepartmentPanel;
import ncs_test_jhs.ui.list.DepartmentTblPanel;
import ncs_test_jhs.ui.service.DepartmentUiService;

@SuppressWarnings("serial")
public class DepartmentUIPanel extends JPanel implements ActionListener{
	private DepartmentPanel pDeptPanel;
	private DepartmentUiService service;
	private JButton btnAdd;
	private JButton btnCancel;
	
	
	public DepartmentUIPanel() {
		service = new DepartmentUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pDeptPanel = new DepartmentPanel();
		add(pDeptPanel);
		
		JPanel btns = new JPanel();
		add(btns);
		
		btnAdd = new JButton("추가");
		btns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btns.add(btnCancel);
		
		pDeptTblPanel = new DepartmentTblPanel();		
		pDeptTblPanel.loadData(service.showDepartmentList());
		pDeptTblPanel.setPopupMenu(myPopup());
		
		add(pDeptTblPanel);
	}

	private JPopupMenu myPopup() {
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem updateItem = new JMenuItem("수정");
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		popMenu.add(deleteItem);

		updateItem.addActionListener(myPopListener);
		deleteItem.addActionListener(myPopListener);
		return popMenu;
	}
	
  ActionListener myPopListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Department updateTitle = pDeptTblPanel.getSelectedItem();
				pDeptPanel.setItem(updateTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				Department delDept = pDeptTblPanel.getSelectedItem();
				service.removeDepartment(delDept);
				pDeptTblPanel.removeRow();
				
			}
		}
	};
  private DepartmentTblPanel pDeptTblPanel;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
			}else {
			btnUpdateActionPerfromed(e);
		  }
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}
	private void btnUpdateActionPerfromed(ActionEvent e) {
		Department newDept = pDeptPanel.getItem();
		// System.out.println(newTitle);
		service.modifyDepartment(newDept);
		pDeptTblPanel.updateRow(newDept, pDeptTblPanel.getSelectdRowIndex());
		btnAdd.setText("추가");
//		pDeptPanel.setFirstNum((service.setFirstNum()+1));
		pDeptPanel.clearTf();
		
		
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pDeptPanel.clearTf();
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Department newDept = pDeptPanel.getItem();
			service.addUpDepartment(newDept);
			pDeptTblPanel.addItem(newDept);
//			pDeptPanel.setFirstNum(service.setFirstNum());
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
