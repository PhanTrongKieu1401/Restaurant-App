package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.MaterialDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Material;

public class SearchMaterialFrm extends JFrame implements ActionListener{

	private static Invoice invoice;
	private ArrayList<Material> listMaterial;
	private JPanel contentPane;
	private JTextField txtMaterialName;
	private JButton btnSearch, btnAddMaterial;
	private JPanel pnTable;
	private JScrollPane scrollPane;
	private JTable tblMaterial;
	private JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchMaterialFrm frame = new SearchMaterialFrm(invoice);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchMaterialFrm(Invoice invoice) {
		setTitle("Search material");
		this.invoice = invoice;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 600;
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
		
		txtMaterialName = new JTextField();
		txtMaterialName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnAction.add(txtMaterialName);
		txtMaterialName.setColumns(50);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnSearch);
		
		btnAddMaterial = new JButton("Add");
		btnAddMaterial.addActionListener(this);
		btnAddMaterial.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnAddMaterial);
		
		pnTable = new JPanel();
		pnTable.setLayout(new BoxLayout(pnTable, BoxLayout.Y_AXIS));
		tblMaterial = new JTable();
		scrollPane = new JScrollPane(tblMaterial);
		tblMaterial.setFillsViewportHeight(false); 
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 450));
		
		tblMaterial.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = tblMaterial.getColumnModel().getColumnIndexAtX(e.getX()); 
				int row = e.getY() / tblMaterial.getRowHeight(); 

				// *kiểm tra hàng hoặc cột có tồn tại hay không
				if (row < tblMaterial.getRowCount() && row >= 0 && column < tblMaterial.getColumnCount() && column >= 0) {
					DetailedInvoice detailedInvoice = new DetailedInvoice();
					Material material = listMaterial.get(row);		
					detailedInvoice.setMaterial(material);
					
					ArrayList<DetailedInvoice> detailedInvoices = invoice.getDetailedInvoices();
					detailedInvoices.add(detailedInvoice);
					(new ImportQuantityFrm(invoice)).setVisible(true);
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
			if((txtMaterialName.getText() == null)||(txtMaterialName.getText().length() == 0))
				return;
			MaterialDAO md = new MaterialDAO();
			listMaterial = md.searchMaterial(txtMaterialName.getText().trim());
			if(listMaterial.isEmpty()) {
				DefaultTableModel newModel = new DefaultTableModel();
				tblMaterial.setModel(newModel);
				contentPane.revalidate(); //load lại giao diện
				contentPane.repaint();
				JOptionPane.showMessageDialog(jFrame, "Nguyên liệu không tồn tại!");
			}
			else {
				String[] columnNames = {"Id", "Name", "Price"};
				String[][] value = new String[listMaterial.size()][5];
				for(int i=0; i<listMaterial.size(); i++){
					value[i][0] = listMaterial.get(i).getId() +"";
					value[i][1] = listMaterial.get(i).getName();
					value[i][2] = listMaterial.get(i).getPrice() +"";
				}
				DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       return false;
				    }
				};
				tblMaterial.setModel(tableModel);
				contentPane.revalidate();//load lại giao diện
				contentPane.repaint();
			}
		}
		else {
			if(btnChecked.equals(btnAddMaterial)) {
				(new AddMaterialFrm(invoice)).setVisible(true);
				this.dispose();
			}
		}
	}
}
