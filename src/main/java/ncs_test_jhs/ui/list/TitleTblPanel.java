package ncs_test_jhs.ui.list;

import javax.swing.SwingConstants;

import ncs_test_jhs.dto.Title;

public class TitleTblPanel extends AbsTblPanel<Title> {

	
	
	@Override
	protected Object[] toArray(Title list) {
		
		return new Object[] {
				//String.format("T%03d", list.getTitleNo()),
				list.getTitleNo(),
				list.getTitleName()
		};
	}

	@Override
	protected String[] getColumnNames() {
		
		return new String[] {"번호","직책"} ;
	}

	@Override
	protected void setTblWidthAlign() {
		//테이블 정렬
		tableSetWidth(80,150);
		tableCellAlign(SwingConstants.CENTER, 0, 1);
		
	}

	@Override
	public void updateRow(Title item, int updateIdx) {
		model.setValueAt(item.getTitleNo(), updateIdx , 0);
		model.setValueAt(item.getTitleName(), updateIdx, 1);
		
	}

	@Override
	public Title getSelectedItem() {
		int selectedIdx = getSelectdRowIndex();
		int titleNo =  (int)model.getValueAt(selectedIdx, 0);
		String titleName = (String) model.getValueAt(selectedIdx, 1);
		return new Title(titleNo, titleName);
	}

}
