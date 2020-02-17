package ncs_test_jhs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_jhs.dto.Title;
import ncs_test_jhs.ui.content.TitlePanel;
import ncs_test_jhs.ui.list.TitleTblPanel;
import ncs_test_jhs.ui.service.TitleUiService;

@SuppressWarnings("serial")
public class TitleUIPanel extends JPanel implements ActionListener {
	private TitleUiService service;
	private JButton btnCancel;
	private JButton btnAdd;
	private TitlePanel pTitlePanel;
	private TitleTblPanel pTblPanel;
	
	
	public TitleUIPanel() {
		service = new TitleUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pTitlePanel = new TitlePanel();
		add(pTitlePanel);

		
		JPanel btns = new JPanel();
		add(btns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btns.add(btnCancel);
		
		pTblPanel = new TitleTblPanel();
		add(pTblPanel);
		pTblPanel.loadData(service.showTitleList());
		pTblPanel.setPopupMenu(myPopup()); //팝업메뉴 만들기
		
		pTitlePanel.setTitleNo(pTblPanel);
		
		
		
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
    
	//팝업메뉴에 액션 리스너 달기
	ActionListener myPopListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Title updateTitle = pTblPanel.getSelectedItem();
				pTitlePanel.setItem(updateTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				Title delTitle = pTblPanel.getSelectedItem();
				service.removeTitle(delTitle);
				pTblPanel.removeRow();
				
			}
		}
	};
	
	
	
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
		Title newTitle = pTitlePanel.getItem();
		// System.out.println(newTitle);
		service.modifyTitle(newTitle);
		pTblPanel.updateRow(newTitle, pTblPanel.getSelectdRowIndex());
		btnAdd.setText("추가");
		pTitlePanel.setFirstNum((service.setFirstNum()+1));
		pTitlePanel.clearTf();
		
		
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pTitlePanel.clearTf();
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Title newTitle = pTitlePanel.getItem();
			service.addUpTitle(newTitle);
			pTblPanel.addItem(newTitle);
			pTitlePanel.setFirstNum(service.setFirstNum());
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
