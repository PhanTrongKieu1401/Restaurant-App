package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.SupplierDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Supplier;
import model.User;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchSupplierFrm extends JFrame implements ActionListener{

	private static User user;
	private ArrayList<Supplier> listSupplier;
	private JPanel contentPane;
	private JTextField txtSupplierName;
	private JButton btnSearch, btnAddSupplier;
	private JPanel pnTable;
	private JScrollPane scrollPane;
	private JTable tblSupplier;
	private JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchSupplierFrm frame = new SearchSupplierFrm(user);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchSupplierFrm(User user) {
		setTitle("Search supplier");
		this.user = user;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 1000;
		int height = 500;
		setSize(width, height);
		this.setLocation((widthsc-width)/2, (heightsc-height)/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pnAction = new JPanel();
		contentPane.add(pnAction);
		pnAction.setLayout(new BoxLayout(pnAction, BoxLayout.X_AXIS));
		pnAction.setMaximumSize(new Dimension(width-50,50));
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		
		txtSupplierName = new JTextField();
		txtSupplierName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnAction.add(txtSupplierName);
		txtSupplierName.setColumns(50);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnSearch);
		
		btnAddSupplier = new JButton("Add");
		btnAddSupplier.addActionListener(this);
		btnAddSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnAddSupplier);
		
		pnTable = new JPanel();
		pnTable.setLayout(new BoxLayout(pnTable, BoxLayout.Y_AXIS));
		tblSupplier = new JTable();
		scrollPane = new JScrollPane(tblSupplier);
		tblSupplier.setFillsViewportHeight(false); 
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 450));
		
		tblSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = tblSupplier.getColumnModel().getColumnIndexAtX(e.getX()); 
				int row = e.getY() / tblSupplier.getRowHeight(); 

				// *kiểm tra hàng hoặc cột có tồn tại hay không
				if (row < tblSupplier.getRowCount() && row >= 0 && column < tblSupplier.getColumnCount() && column >= 0) {
					Invoice invoice = new Invoice();
					Supplier supplier = new Supplier();
					supplier = listSupplier.get(row);
					invoice.setUser(user);
					invoice.setSupplier(supplier);
					invoice.setDetailedInvoices(new ArrayList<DetailedInvoice>());
					(new SearchMaterialFrm(invoice)).setVisible(true);
					dispose();
				}
			}
		});

		pnTable.add(scrollPane);		
		contentPane.add(pnTable);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnChecked = (JButton) e.getSource();
		if(btnChecked.equals(btnSearch)) {
			if((txtSupplierName.getText() == null)||(txtSupplierName.getText().length() == 0))
				return;
			SupplierDAO sd = new SupplierDAO();
			listSupplier = sd.searchSupplier(txtSupplierName.getText().trim());
			if(listSupplier.isEmpty()) {
				DefaultTableModel newModel = new DefaultTableModel();
				tblSupplier.setModel(newModel);
				contentPane.revalidate(); //load lại giao diện
				contentPane.repaint();
				JOptionPane.showMessageDialog(jFrame, "Nhà cung cấp không tồn tại!");
			}
			else {
				String[] columnNames = {"Id", "Name", "Address", "Email", "Description"};
				String[][] value = new String[listSupplier.size()][5];
				for(int i=0; i<listSupplier.size(); i++){
					value[i][0] = listSupplier.get(i).getId() +"";
					value[i][1] = listSupplier.get(i).getName();
					value[i][2] = listSupplier.get(i).getAddress();
					value[i][3] = listSupplier.get(i).getEmail();
					value[i][4] = listSupplier.get(i).getDescription();
				}
				DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       return false;
				    }
				};
				tblSupplier.setModel(tableModel);
				contentPane.revalidate(); //load lại giao diện
				contentPane.repaint();
			}		
		}
		else {
			if(btnChecked.equals(btnAddSupplier)) {
				(new AddSupplierFrm(user)).setVisible(true);
				this.dispose();
			}
		}
	}
}
