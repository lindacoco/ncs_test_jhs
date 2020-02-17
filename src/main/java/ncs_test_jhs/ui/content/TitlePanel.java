package ncs_test_jhs.ui.content;

import ncs_test_jhs.dao.TitleDao;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.ui.list.TitleTblPanel;
import ncs_test_jhs.ui.service.TitleUiService;

import java.awt.GridLayout;
import java.security.Provider.Service;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class TitlePanel extends AbsItemPanel<Title> {
	
	private TitleUiService service;
	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	public TitlePanel() {
		initialize();
	}
	private void initialize() {
		service = new TitleUiService();
		setBorder(null);
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblTitleNo = new JLabel("번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		tfTitleNo.setEditable(false);
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		add(tfTitleName);
		tfTitleName.setColumns(10);
		
	}

 
	@Override
	public Title getItem() {
		int titleNo = service.setFirstNum();
		String titleName = tfTitleName.getText().trim();
		return new Title(titleNo, titleName);
	}

	@Override
	public void setItem(Title item) {
		tfTitleNo.setText(item.getTitleNo()+"");
		tfTitleName.setText(item.getTitleName());		
	}

	@Override
	public void clearTf() {
		tfTitleName.setText("");
		
	}
	@Override
	public void setFirstNum(int a) {
		tfTitleNo.setText(String.format("T%03d", a));
		//String.format("T%03d", list.getTitleNo())
	}
	
	public void setTitleNo(TitleTblPanel titleTblPanel) {
		tfTitleNo.setText(titleTblPanel.getLastIndex()+"");
	}

}
