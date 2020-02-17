package ncs_test_jhs.ui.list;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public abstract class AbsTblPanel<T> extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	protected notEditableModel model;
	protected List<T> list;
	
	
	public AbsTblPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	//팝업메뉴달기
	public void setPopupMenu(JPopupMenu popup) {
		scrollPane.setComponentPopupMenu(popup);
		table.setComponentPopupMenu(popup);
	}
	//데이터 불러오기
	public void loadData(List<T> items) {
		model = new notEditableModel(getRows(items), getColumnNames()); //컬럼 이름도 데이터 마다 다르니 추상화
		table.setModel(model);
		
		setTblWidthAlign(); //테이블 width설정하는 것. 각 테이블마다  컬럼 개수가 다르니 상속받게해서 처리해야한다
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
	}
	
	//값들 중앙정렬 설정하기
	protected void tableCellAlign(int align, int...idx) { //값이 몇개가 올지 모를때 ...으로 처리한다 
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
      dtcr.setHorizontalAlignment(align); 
      TableColumnModel cModel = table.getColumnModel();
      for(int i =0; i<idx.length; i++) {
    	  cModel.getColumn(idx[i]).setCellRenderer(dtcr);
      }
		
	}
	//각 컬럼마다 width를 다르게 하는 것
	protected void tableSetWidth(int...width) {
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0; i<width.length;i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	protected Object[][] getRows(List<T> items) {
		Object[][] rows = new Object[items.size()][];
           for(int i=0; i<rows.length; i++) {
        	   rows[i] = toArray(items.get(i));
           }
		return rows;
			
	}
	protected abstract Object[] toArray(T list); //데이터 마다 다른 리스트 각각 구현
	protected abstract String[] getColumnNames();
	protected abstract void setTblWidthAlign();
	public abstract void updateRow(T item, int updateIdx);
	
	
	
	//리스트에 추가시키기
		public void addItem(T item) {
			model.addRow(toArray(item));
		}
   //삭제하기
		public void removeRow() {
			int selectedIdx = getSelectdRowIndex();
			model.removeRow(selectedIdx);
		}

		
   public T getSelectedItem() {
			int selectedIdx = getSelectdRowIndex();
			return list.get(selectedIdx);
		};	
	
		
	public int getSelectdRowIndex() { //선택한 row의 인덱스값 구하기 tbl패널에서 부르려면 public으로 고쳐야했음 
			int selectedIdx = table.getSelectedRow();
				if(selectedIdx == -1) {
					throw new RuntimeException("선택된 데이터가 없습니다");
				}
			return selectedIdx;
		}
	public void clearSelection() {
			table.clearSelection();
		}
	//함부로 수정 못하게 하는 모델 선언 원래는 클래스 따로 만들어야함
	protected class notEditableModel extends DefaultTableModel{
		public notEditableModel(Object[][] data, Object[] columnNames) {
			super(data,columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	public int getLastRowIndex() {
		int lastIdx = table.getRowCount();
		return lastIdx;
	}

    
}
