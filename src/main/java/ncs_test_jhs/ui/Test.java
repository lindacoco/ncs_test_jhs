package ncs_test_jhs.ui;

import ncs_test_jhs.ui.list.EmployeeTblPanel;
import ncs_test_jhs.ui.service.EmployeeUiService;

public class Test {

	
      
      public static void main(String[] args) {
		EmployeeUiService service = new EmployeeUiService();
		EmployeeTblPanel empTblPanel = new EmployeeTblPanel();
		int num = empTblPanel.getLastIndex();
		System.out.println(num);
		
		
		
		
		

	}

}
