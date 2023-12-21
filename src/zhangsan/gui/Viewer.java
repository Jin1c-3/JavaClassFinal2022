package zhangsan.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Viewer extends JFrame {

	private JPanel contentPane;
	private JTextField textField_isbn;
	private JTextField textField_title;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_price;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer frame = new Viewer();
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
	public Viewer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		splitPane.setBounds(0, 0, 830, 261);
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u56FE\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(10, 20, 62, 15);
		panel.add(lblNewLabel);
		
		textField_isbn = new JTextField();
		textField_isbn.setBounds(39, 17, 116, 21);
		panel.add(textField_isbn);
		textField_isbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("书名");
		lblNewLabel_1.setBounds(10, 67, 62, 15);
		panel.add(lblNewLabel_1);
		
		textField_title = new JTextField();
		textField_title.setBounds(39, 67, 116, 21);
		panel.add(textField_title);
		textField_title.setColumns(10);
		
		JComboBox comboBox_publisher = new JComboBox();
		comboBox_publisher.setModel(new DefaultComboBoxModel(new String[] {"高等教育出版社", "清华大学出版社", "机械工业出版社"}));
		comboBox_publisher.setBounds(10, 117, 145, 23);
		panel.add(comboBox_publisher);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("中文");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(10, 163, 55, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("英文");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(86, 163, 62, 23);
		panel.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("价格");
		lblNewLabel_2.setBounds(10, 203, 62, 15);
		panel.add(lblNewLabel_2);
		
		textField_price = new JTextField();
		textField_price.setText("");
		textField_price.setBounds(39, 203, 116, 21);
		panel.add(textField_price);
		textField_price.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.7);
		splitPane_1.setBounds(0, 0, 653, 259);
		panel_1.add(splitPane_1);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 451, 204);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u5047\u671F\u5012\u8BA1\u65F6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setRightComponent(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("距离2023年五一假期还有：");
		lblNewLabel_3.setBounds(10, 20, 175, 15);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_time = new JLabel("New label");
		lblNewLabel_time.setBounds(10, 45, 175, 157);
		panel_3.add(lblNewLabel_time);
	}
}
