package ncs_test_jhs.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnTitle;
	private JButton btnEmp;
	private JButton btnDept;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initialize();
	}
	private void initialize() {
		setTitle("ERP관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		mainPanel.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		mainPanel.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		mainPanel.add(btnTitle);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDept) {
			btnDeptActionPerformed(e);
		}
		if (e.getSource() == btnEmp) {
			btnEmpActionPerformed(e);
		}
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		}
	}
	protected void btnTitleActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100,100,400,300);
		TitleUIPanel tu = new TitleUIPanel();
		frame.add(tu);
		frame.setVisible(true);
	}
	protected void btnEmpActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100,100,600,500);
		EmployeeUIpanel eu = new EmployeeUIpanel();
		frame.add(eu);
		frame.setVisible(true);
		
	}
	protected void btnDeptActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100,100,500,700);
		DepartmentUIPanel du = new DepartmentUIPanel();
		frame.add(du);
		frame.setVisible(true);
	}
}
