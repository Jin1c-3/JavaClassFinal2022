package yujingyi.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.util.StringUtils;

import yujingyi.dbOper.InsertServiceImpl;
import yujingyi.dbOper.InsertServiceInter;
import yujingyi.dbOper.QueryServiceImpl;
import yujingyi.dbOper.QueryServiceInter;
import yujingyi.fileOper.TxtReaderImpl;
import yujingyi.fileOper.TxtReaderInter;
import yujingyi.pojo.Book;
import yujingyi.thread.DrawThread;

import java.awt.Font;
import java.awt.Color;

public class Viewer extends JFrame {

	private JPanel contentPane;
	private JTextField textField_isbn;
	private JTextField textField_title;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_price;
	private JTable table;

	private List<Book> book_list = new ArrayList<Book>();
	private static Integer count = 0;
	private Long s;
	private Timer timer;

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
		lblNewLabel.setBounds(10, 33, 62, 15);
		panel.add(lblNewLabel);

		textField_isbn = new JTextField();
		textField_isbn.setBounds(39, 30, 116, 21);
		panel.add(textField_isbn);
		textField_isbn.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("书名");
		lblNewLabel_1.setBounds(10, 79, 62, 15);
		panel.add(lblNewLabel_1);

		textField_title = new JTextField();
		textField_title.setBounds(39, 76, 116, 21);
		panel.add(textField_title);
		textField_title.setColumns(10);

		JComboBox comboBox_publisher = new JComboBox();
		comboBox_publisher.setModel(new DefaultComboBoxModel(new String[] { "高等教育出版社", "清华大学出版社", "机械工业出版社" }));
		comboBox_publisher.setBounds(10, 124, 145, 23);
		panel.add(comboBox_publisher);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("中文");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(10, 163, 55, 23);
		panel.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setSelected(true);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("英文");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(86, 163, 62, 23);
		panel.add(rdbtnNewRadioButton_2);

		JLabel lblNewLabel_2 = new JLabel("价格");
		lblNewLabel_2.setBounds(10, 212, 62, 15);
		panel.add(lblNewLabel_2);

		textField_price = new JTextField();
		textField_price.setText("");
		textField_price.setBounds(39, 209, 116, 21);
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

		JButton btnNewButton_1 = new JButton("从文件读取记录");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!book_list.isEmpty() && (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null,
						"表格数据将被覆盖，您确定吗？", "提示", JOptionPane.YES_NO_OPTION))) {
					JOptionPane.showMessageDialog(null, "已经为您取消！");
					return;
				}
				TxtReaderInter tr = new TxtReaderImpl();
				book_list = tr.importData();
				String[] cols = { "ISBN", "书名", "出版社", "语言", "价格" };
				DefaultTableModel tm = new DefaultTableModel(cols, 0);
				book_list.forEach(o -> {
					tm.addRow(o.toStringArray());
				});
				table.setModel(tm);
				table.setAutoCreateRowSorter(true); // 创建自动排序按钮
			}
		});
		btnNewButton_1.setBounds(5, 214, 125, 33);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("添加记录");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isComplete = !textField_isbn.getText().isBlank() && !textField_title.getText().isBlank()
						&& !textField_price.getText().isBlank();
				Boolean isPrice = textField_price.getText()
						.matches("(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)")
								? Double.parseDouble(textField_price.getText()) == 0 ? false : true
								: false;

				if (isComplete && isPrice) {
					Book book_temp = new Book(textField_isbn.getText(), textField_title.getText(),
							(String) comboBox_publisher.getSelectedItem(),
							rdbtnNewRadioButton_1.isSelected() ? (String) rdbtnNewRadioButton_1.getSelectedObjects()[0]
									: (String) rdbtnNewRadioButton_2.getSelectedObjects()[0],
							textField_price.getText());

					book_list.add(book_temp);
					String[] cols = { "ISBN", "书名", "出版社", "语言", "价格" };
					DefaultTableModel dm = new DefaultTableModel(cols, 0); // add rows
					book_list.forEach(o -> {
						dm.addRow(o.toStringArray());
					});
					table.setModel(dm);
					table.setAutoCreateRowSorter(true);
				} else {
					JOptionPane.showMessageDialog(null, "添加失败，请检查", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(139, 214, 87, 33);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("写入数据库");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!book_list.isEmpty()) {
					InsertServiceInter is = new InsertServiceImpl("mysql");
					for (Book book : book_list) {
						if (!is.insertRecords(book.toString())) {
							JOptionPane.showMessageDialog(null, "插入数据库失败，可能插入重复主键！", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					book_list = new ArrayList<Book>();
					DefaultTableModel dm = new DefaultTableModel();
					table.setModel(dm);
					JOptionPane.showMessageDialog(null, "写入成功！");
				} else {
					JOptionPane.showMessageDialog(null, "插入失败,列表为空！");
				}
			}
		});
		btnNewButton_3.setBounds(235, 214, 97, 33);
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("按出版社查询");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!book_list.isEmpty() && (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null,
						"表格数据将被覆盖，您确定吗？", "提示", JOptionPane.YES_NO_OPTION))) {
					JOptionPane.showMessageDialog(null, "已经为您取消！");
					return;
				}
				QueryServiceInter qs = new QueryServiceImpl("mysql");

				book_list = qs.selectByPublisher((String) comboBox_publisher.getSelectedItem());

				// 线程写文件部分
				DrawThread dt = new DrawThread(book_list);
				dt.start();

				String[] cols = { "ISBN", "书名", "出版社", "语言", "价格" };
				DefaultTableModel dm = new DefaultTableModel(cols, 0); // add rows
				book_list.forEach(o -> {
					dm.addRow(o.toStringArray());
				});
				table.setModel(dm);
				table.setAutoCreateRowSorter(true);
			}
		});
		btnNewButton_4.setBounds(337, 214, 110, 33);
		panel_2.add(btnNewButton_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u5047\u671F\u5012\u8BA1\u65F6", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		splitPane_1.setRightComponent(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("距离2023年五一假期还有：");
		lblNewLabel_3.setBounds(10, 20, 175, 15);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_time = new JLabel("1000(秒)");
		lblNewLabel_time.setOpaque(true);
		lblNewLabel_time.setBackground(new Color(100, 149, 237));
		lblNewLabel_time.setFont(new Font("宋体", Font.PLAIN, 28));
		lblNewLabel_time.setBounds(10, 45, 175, 157);
		panel_3.add(lblNewLabel_time);

		JButton btnNewButton_5 = new JButton("开始倒计时");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Date not suggested /LocalDateTime /伽利略时间
				LocalDateTime start = LocalDateTime.now();
				LocalDateTime end = LocalDateTime.of(2023, 5, 1, 0, 0);
				Duration duration = Duration.between(start, end);
				s = duration.toSeconds();

				if (count % 2 == 0) {
					timer = new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (s == 0)
								timer.stop();
							lblNewLabel_time.setText(s + "(秒)");
							s--;
						}
					});
					timer.start();
					btnNewButton_5.setText("停止倒计时");
					count++;
				} else {
					timer.stop();
					btnNewButton_5.setText("开始倒计时");
					count++;
				}

			}
		});
		btnNewButton_5.setBounds(41, 212, 101, 33);
		panel_3.add(btnNewButton_5);
	}
}
