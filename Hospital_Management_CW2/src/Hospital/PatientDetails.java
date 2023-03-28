package Hospital;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PatientDetails extends JFrame implements MouseListener, ActionListener {
	String[][] alldata;

	JLabel titlelbl;
	JLabel idlbl;
	JLabel usernamelbl;
	JLabel addresslbl;
	JLabel doblbl;
	JLabel doctorlbl;
	JLabel genderlbl;
	JLabel descriptionlbl;
	JLabel datelbl;
	JLabel phonelbl;
	JLabel searchlbl;
	JLabel sortlbl;

	JTextField txtid;
	JTextField txtname;
	JTextField txtaddress;
	JTextField txtdob;
	JComboBox txtdoctor;
	JTextField txtphone;
	JComboBox txtgender;
	JTextField txtdescription;
	JFormattedTextField txtdate;
	JTextField txtsearch;
	JComboBox combo_search;
	JComboBox combo_sort;

	JButton backbtn;
	JButton addbtn;
	JButton updatebtn;
	JButton clearbtn;
	JButton searchbtn, sortbtn;

	// For Table
	JTable tbl;
	DefaultTableModel model;

	JTextField txt1;
	// For Ending Table

	Connection conn = null;

	PatientDetails() {
		// connection start
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_hospital" + 
                    "?user=admin&password=test123&useSSL=false");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		// connection end

		setTitle("Update Patient");
		getContentPane().setLayout(null);

		idlbl = new JLabel("Patient Id:");
		usernamelbl = new JLabel("Patient Name:");
		addresslbl = new JLabel("Address:");
		phonelbl = new JLabel("Phone");
		doblbl = new JLabel("DOB:");
		doctorlbl = new JLabel("Doctor Name:");
		genderlbl = new JLabel("Gender:");
		descriptionlbl = new JLabel("Description:");
		searchlbl = new JLabel("Search By:");
		sortlbl = new JLabel("Sort By:");

		txtid = new JTextField(10);
		txtid.setEditable(false);
		txtname = new JTextField(10);
		txtaddress = new JTextField(10);
		txtphone = new JTextField(10);
		txtdob = new JTextField(10);
		txtdoctor = new JComboBox();
		String gender[] = { "Male", "Female", "Others" };
		txtgender = new JComboBox(gender);
		txtdescription = new JTextField(10);
		txtsearch = new JTextField(10);
		String search[] = { "Patient_Id", "Name", "Phone" };
		combo_search = new JComboBox(search);
		String sort[] = { "Ascending", "Descending" };
		combo_sort = new JComboBox(sort);

		DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
		txtdate = new JFormattedTextField(dateFormat);
		datelbl = new JLabel("Date:");
		datelbl.setLabelFor(txtdate);
		txtdate.setValue(new Date());

		addbtn = new JButton("Add");
		backbtn = new JButton("Back");
		updatebtn = new JButton("Update");
		clearbtn = new JButton("Clear");
		searchbtn = new JButton("Search");
		sortbtn = new JButton("Sort");

		backbtn.addActionListener(this);
		addbtn.addActionListener(this);
		updatebtn.addActionListener(this);
		clearbtn.addActionListener(this);
		searchbtn.addActionListener(this);
		sortbtn.addActionListener(this);

		titlelbl = new JLabel("Patient Details");
		titlelbl.setBounds(400, 11, 400, 40);
		titlelbl.setFont(new Font("Arial", Font.PLAIN, 40));

		searchlbl.setBounds(400, 61, 75, 25);
		combo_search.setBounds(673, 62, 100, 25);
		txtsearch.setBounds(473, 62, 184, 25);
		searchbtn.setBounds(783, 62, 75, 25);

		combo_sort.setBounds(965, 62, 100, 25);
		sortbtn.setBounds(1075, 62, 75, 25);
		sortlbl.setBounds(904, 61, 75, 25);

		idlbl.setBounds(45, 97, 75, 25);
		usernamelbl.setBounds(45, 147, 75, 25);
		addresslbl.setBounds(45, 197, 75, 25);
		phonelbl.setBounds(45, 247, 75, 25);
		doblbl.setBounds(45, 297, 100, 25);
		doctorlbl.setBounds(45, 347, 75, 25);
		genderlbl.setBounds(45, 397, 75, 25);
		descriptionlbl.setBounds(45, 447, 75, 25);
		datelbl.setBounds(45, 497, 75, 25);

		txtid.setBounds(165, 97, 200, 25);
		txtname.setBounds(165, 147, 200, 25);
		txtaddress.setBounds(165, 197, 200, 25);
		txtphone.setBounds(165, 247, 200, 25);
		txtdob.setBounds(165, 297, 200, 25);
		txtdoctor.setBounds(165, 347, 200, 25);
		txtgender.setBounds(165, 397, 200, 25);
		txtdescription.setBounds(165, 447, 200, 25);
		txtdate.setBounds(165, 497, 200, 25);
//	     txtdate.setEditable(false);
		// today.setBounds();

		addbtn.setBounds(25, 547, 160, 25);
		backbtn.setBounds(25, 587, 160, 25);
		clearbtn.setBounds(195, 587, 160, 25);
		updatebtn.setBounds(195, 547, 160, 25);

		// Table
		String[] cols = { "Patient_Id", "Name", "Address", "Phone", "DOB", "Assigned Doctor", "Gender", "Description",
				"Date" };
		model = new DefaultTableModel(cols, 0);
		tbl = new JTable(model);

		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(400, 97, 750, 515);
		getContentPane().add(sp);
		tbl.addMouseListener(this);
		

		getContentPane().add(titlelbl);
		getContentPane().add(idlbl);
		getContentPane().add(txtid);
		getContentPane().add(usernamelbl);
		getContentPane().add(txtname);
		getContentPane().add(phonelbl);
		getContentPane().add(doctorlbl);
		getContentPane().add(searchlbl);
		getContentPane().add(sortlbl);

		getContentPane().add(addresslbl);
		getContentPane().add(txtaddress);
		getContentPane().add(txtphone);
		getContentPane().add(doblbl);
		getContentPane().add(txtdob);
		getContentPane().add(txtdoctor);
		getContentPane().add(genderlbl);
		getContentPane().add(txtgender);
		getContentPane().add(descriptionlbl);
		getContentPane().add(txtdescription);

		getContentPane().add(datelbl);
		getContentPane().add(txtdate);
		getContentPane().add(txtsearch);
		getContentPane().add(combo_search);
		getContentPane().add(combo_sort);

		getContentPane().add(backbtn);
		getContentPane().add(addbtn);
		getContentPane().add(updatebtn);
		getContentPane().add(clearbtn);
		getContentPane().add(sortbtn);
		getContentPane().add(searchbtn);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1207, 702);
		setVisible(true);
		
		remove_table();
		displayTable();
//		displayTableData();
		displaydoctorname();
		getTableData(tbl);
		alldata = getTableData(tbl);

	}

	public boolean displayTable() {
		try {
			String sql = "select * from patient_details";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {

				String id = result.getString("id");
				String name = result.getString("name");
				String address = result.getString("address");
				String phone = result.getString("phone");
				String dob = result.getString("dob");
				String doctorName = result.getString("doctor_name");
				String gender = result.getString("gender");
				String description = result.getString("description");
				String date = result.getString("date");
				String medical_history = result.getString("medical_history");
				model.addRow(new Object[] { id, name, address, phone, dob, doctorName, gender, description, date,
						medical_history });
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return true;
	}

	public boolean remove_table() {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {

			model.removeRow(i);
		}
		return true;

	}

	public void displaydoctorname() {

		try {
			String sql = "select name from register where role='doctor'";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {

				String doctor = res.getString("name");
				txtdoctor.addItem(doctor);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        PatientDetails f = new PatientDetails();
    }

	public void reset() {
		txtid.setText("");
		txtname.setText("");
		txtaddress.setText("");
		txtphone.setText("");
		txtdob.setText("");
		txtdescription.setText("");
	}

	public static String[][] getTableData(JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		String[][] tableData = new String[nRow][nCol];
		for (int i = 0; i < nRow; i++)
			for (int j = 0; j < nCol; j++)
				tableData[i][j] = (String) dtm.getValueAt(i, j);
		return tableData;
	}

	public String search(String inputTxt, int column, String[][] tableData) {
		remove_table();
		for (int i = 0; i < tableData.length; i++) {
			if (tableData[i][column].equals(inputTxt)) {
				String id = tableData[i][0];
				String name = tableData[i][1];
				String address = tableData[i][2];
				String phone = tableData[i][3];
				String dob = tableData[i][4];
				String doctorName = tableData[i][5];
				String gender = tableData[i][6];
				String description = tableData[i][7];
				String date = tableData[i][8];
				model.addRow(new Object[] { id, name, address, phone, dob, doctorName, gender, description,
						date });
				return "found";
			}
		}
		return "not found";
	}

	public String[][] sort(String[][] tableData) {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < (tableData.length - i - 1); j++) {
				if (Integer.parseInt(tableData[j][0]) > Integer.parseInt(tableData[j + 1][0])) {
					Object[] temp = tableData[j];
					tableData[j] = tableData[j + 1];
					tableData[j + 1] = (String[]) temp;
				}
			}
		}
		remove_table();
		return tableData;
	}

	public String[][] reverseData(String[][] tableData) {
		for (int i = 0; i < tableData.length / 2; i++) {
			Object temp = tableData[i];
			tableData[i] = tableData[tableData.length - 1 - i];
			tableData[tableData.length - 1 - i] = (String[]) temp;
		}
		return tableData;
	}
	
	 public void displayTableData(String[][] tableData){
		    for (int i = 0; i < tableData.length; i++) {
		    	String id = tableData[i][0];
				String name = tableData[i][1];
				String address = tableData[i][2];
				String phone = tableData[i][3];
				String dob = tableData[i][4];
				String doctorName = tableData[i][5];
				String gender = tableData[i][6];
				String description = tableData[i][7];
				String date = tableData[i][8];
				model.addRow(new Object[] { id, name, address, phone, dob, doctorName, gender, description,
						date });
		    }
		} 

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addbtn) {
			String name = txtname.getText();
			String address = txtaddress.getText();
			String phone = txtphone.getText();
			String dob = txtdob.getText();
			String doctorName = txtdoctor.getSelectedItem().toString();
			String gender = txtgender.getItemAt(txtgender.getSelectedIndex()).toString();
			String description = txtdescription.getText();
			String date = txtdate.getText();

			if (name.isEmpty() == false && phone.isEmpty() == false && address.isEmpty() == false
					&& dob.isEmpty() == false && doctorName.isEmpty() == false && description.isEmpty() == false) {

				String sql = "insert into patient_details(name,address,phone,dob,doctor_name,gender,description,date) values(?,?,?,?,?,?,?,?)";
				try {
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2, address);
					stmt.setString(3, phone);
					stmt.setString(4, dob);
					stmt.setString(5, doctorName);
					stmt.setString(6, gender);
					stmt.setString(7, description);
					stmt.setString(8, date);

					int ins = stmt.executeUpdate();
					if (ins > 0) {
						JOptionPane.showMessageDialog(null, "Added successfully");
						reset();
						remove_table();
						displayTable();
					} else {
						JOptionPane.showMessageDialog(null, "Patient addition failed");
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Patient addition failed");
			}
		} else if (source == updatebtn) {
			String name = txtname.getText();
			String address = txtaddress.getText();
			String phone = txtphone.getText();
			String dob = txtdob.getText();
			String doctorName = txtdoctor.getSelectedItem().toString();
			String gender = txtgender.getItemAt(txtgender.getSelectedIndex()).toString();
			String description = txtdescription.getText();
			String date = txtdate.getText();
			String id = txtid.getText();

			if (name.isEmpty() == false && address.isEmpty() == false && phone.isEmpty() == false
					&& dob.isEmpty() == false && description.isEmpty() == false) {

				String sql = "UPDATE patient_details SET name=?, address=?,phone=?, dob=?,doctor_name=?, gender=?, description=?, date=? WHERE id=?";

				try {
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2, address);
					stmt.setString(3, phone);
					stmt.setString(4, dob);
					stmt.setString(5, doctorName);
					stmt.setString(6, gender);
					stmt.setString(7, description);
					stmt.setString(8, date);
					stmt.setString(9, id);
					int ins = stmt.executeUpdate();
					if (ins > 0) {
						JOptionPane.showMessageDialog(null, "Updated successfully");
						reset();
						remove_table();
						displayTable();
					} else {
						JOptionPane.showMessageDialog(null, "Patient Update failed");
					}

				} catch (Exception ee) {
					ee.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Patient Update failed");
			}
			
		}
		else if (e.getSource() == searchbtn) {
		    String searchTxt = txtsearch.getText();
		    String searchBySelected = (String) combo_search.getSelectedItem();
		    if (!searchTxt.equals("")) {
		        if (searchBySelected.equals("Patient_Id")) {
		            search(searchTxt, 0, alldata);
		        }
		        if (searchBySelected.equals("Name")) {
		            search(searchTxt, 1, alldata);
		        }
		        if (searchBySelected.equals("Phone")) {
		            search(searchTxt, 2, alldata);
		        }
		        

		    } else {
		        remove_table();
		        displayTable();
		    }

		}
		if (e.getSource() == sortbtn) {
		    String sortBy = (String) combo_sort.getSelectedItem();
		    if (sortBy.equals("Ascending")){
		        String[][] data = sort(getTableData(tbl));
		        displayTableData(data);
		    }
		    if (sortBy.equals("Descending")){
		        String[][] data = sort(getTableData(tbl));
		        displayTableData(reverseData(data));
		    }
		}  
		else if (source == clearbtn) {
			reset();
		} else if (source == backbtn) {
			AdminDashboard f = new AdminDashboard();
			dispose();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl.rowAtPoint(e.getPoint());

//        System.out.println(model.getValueAt(row, 0));
		txtid.setText(model.getValueAt(row, 0).toString());
		txtname.setText(model.getValueAt(row, 1).toString());
		txtaddress.setText(model.getValueAt(row, 2).toString());
		txtphone.setText(model.getValueAt(row, 3).toString());
		txtdob.setText(model.getValueAt(row, 4).toString());
		txtdoctor.setSelectedItem(model.getValueAt(row, 5));
		txtgender.setSelectedItem(model.getValueAt(row, 6));
		txtdescription.setText(model.getValueAt(row, 7).toString());
		txtdate.setText(model.getValueAt(row, 8).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}