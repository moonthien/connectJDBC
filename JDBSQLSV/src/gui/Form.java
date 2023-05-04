package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.LopHoc_Dao;
import entity.LopHoc;

public class Form extends JFrame implements ActionListener{
	private JLabel lbmaLop,lbtenLop,lbgvCn;
	private JTextField txtma,txtten,txtgvCn;
	private JButton btnthem,btnxoa,btnsua,btnluu,btntruoc,btnrp,btnnext,btnlast,btnds;
	private JTable table;
	private DefaultTableModel model;
	private LopHoc_Dao lopHocc;
	private LopHoc lop;
	public Form() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		setTitle("thong tin lop hoc");
		setSize(700,450);
		setResizable(false);
		setLocale(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel north = new JPanel();
		add(north , BorderLayout.NORTH);
		JLabel title;
		north.add(title = new JLabel("Thong Tin Lop Hoc"));
		title.setForeground(Color.black);
		Font font = new Font("Arial",Font.BOLD,25);
		title.setFont(font);
		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5;
		b.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lbmaLop = new JLabel("malop:"));
		b1.add(txtma = new JTextField());
		b.add(Box.createVerticalStrut(10));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lbtenLop = new JLabel("ten lop :"));
		b2.add(txtten = new JTextField());
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lbgvCn = new JLabel("giao vien chu nhiem :"));
		b3.add(txtgvCn = new JTextField());
		b.add(Box.createVerticalStrut(20));
		lbmaLop.setPreferredSize(lbgvCn.getPreferredSize());
		lbtenLop.setPreferredSize(lbgvCn.getPreferredSize());

		b.add(b4 = Box.createHorizontalBox());
		b4.add(Box.createVerticalStrut(10));

		JPanel center = new JPanel();
		JLabel lb;
		b4.add(btntruoc = new JButton("|<"));
		b4.add(btnrp = new JButton("<<"));
		b4.add(lb = new JLabel("1/2"));
		b4.add(btnlast = new JButton(">>"));
		b4.add(btnnext = new JButton(">|"));
		b4.add(Box.createHorizontalStrut(30));
		b4.add(btnluu = new JButton("luu")) ;
		b4.add(btnsua = new JButton("sua")) ;
		b4.add(btnxoa = new JButton("xoa")) ;
		b4.add(btnthem = new JButton("them")) ;
		btnluu.setEnabled(false);
		b4.add(Box.createHorizontalStrut(100));
		//		b4.add(center);

		b.add(Box.createVerticalStrut(20));
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("ma lop hoc");
		model.addColumn("ten lop hoc");
		model.addColumn("giao vien chu nhiem");
		JScrollPane scro = new JScrollPane(table);
		scro.setPreferredSize(new Dimension(0,300));
		scro.setBorder(BorderFactory.createTitledBorder("danh sach lop hoc"));
		b.add(scro);
		add(b,BorderLayout.CENTER);
		add(btnds = new JButton("hien thi danh sach sinh vien cua lop hien tai"),BorderLayout.SOUTH);
		btnds.setForeground(Color.red);
		Font btn = new Font("Arial",Font.BOLD,20);
		btnds.setFont(btn);

		btnthem.addActionListener(this);
		btnluu.addActionListener(this);
		btnxoa.addActionListener(this);
		btnsua.addActionListener(this);


		lopHocc = new LopHoc_Dao();
		table.setRowHeight(25);
		for (LopHoc lh : lopHocc.getAllLopHoc()) {
			Object [] rowData= {lh.getMaLop(),lh.getTenLop(),lh.getGiaoVienCN()};
			model.addRow(rowData);
		}
	}
	public static void main(String[] args) {
		new Form().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object  o= e.getSource();
		if(o.equals(btnthem)) {
			if(btnthem.getText().equalsIgnoreCase("them")) {
				btnxoa.setEnabled(false);
				btnsua.setEnabled(false);
				btnluu.setEnabled(true);
				btnthem.setEnabled(true);
				btnthem.setText("huy");
			}else if(btnthem.getText().equalsIgnoreCase("huy")) {
				btnxoa.setEnabled(true	);
				btnsua.setEnabled(true);
				btnluu.setEnabled(false);
				btnthem.setText("them");
			}
		}else if(o.equals(btnluu)) {
			LopHoc lop = new LopHoc(txtma.getText(),txtten.getText(), txtgvCn.getText());
			lopHocc.addLophoc(lop);
			JOptionPane.showMessageDialog(null, "them thanh cong");
			String row[]= {txtma.getText(),txtten.getText(), txtgvCn.getText()};
			model.addRow(row);
		}else if(o.equals(btnxoa)) {

			int r = table.getSelectedRow();
			if(r != -1)
			{
				int tb = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa dòng này không?","Delete",JOptionPane.YES_NO_OPTION);
				if(tb == JOptionPane.YES_OPTION){
					lopHocc.xoa(model.getValueAt(r,0).toString());
					model.removeRow(r);

				}
			}
		}else if(o.equals(btnsua)) {
			int r = table.getSelectedRow();
			if(r != -1) {
				try {
					String ma= model.getValueAt(r,0).toString();
					String ten  = JOptionPane.showInputDialog("Enter new ten:");
					String giaovienCN = JOptionPane.showInputDialog("Enter new giaovienCN:");
					LopHoc lop	 = new LopHoc(ma,ten,giaovienCN);

					lopHocc.sua(lop);
					model.setValueAt(ten, r, 1);
					model.setValueAt(giaovienCN, r, 2);
					JOptionPane.showMessageDialog(null, "bạn đã chập nhật thành công");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

		}
	}
}
