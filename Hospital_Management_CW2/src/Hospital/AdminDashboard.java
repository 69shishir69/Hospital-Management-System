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

public class AdminDashboard extends JFrame implements MouseListener, ActionListener{
	 
	 JLabel titlelbl;
	 JButton addPatientbtn;
	 JButton viewPatientDetailsbtn;
	 JButton assignWardbtn;
	 JButton logoutbtn;
	 
	 
	 Connection conn;
	 
	 JTable tbl;
	 DefaultTableModel model;

   AdminDashboard() {
	   setTitle("Admin");
       setLayout(null);
       
       addPatientbtn=new JButton("Add Patient");
       viewPatientDetailsbtn=new JButton("View Patient Details");
       assignWardbtn=new JButton("Assign Ward");
       logoutbtn=new JButton("Logout");
       
       addPatientbtn.addActionListener(this);
       viewPatientDetailsbtn.addActionListener(this);
       assignWardbtn.addActionListener(this);
       logoutbtn.addActionListener(this);
       
       titlelbl = new JLabel("Admin Dashboard");
       titlelbl.setBounds(200, 35, 400, 40);
       titlelbl.setFont(new Font("Arial", Font.PLAIN, 40));
       
       addPatientbtn.setBounds(30, 100, 170, 30);
//       viewPatientDetailsbtn.setBounds(220, 50,  150, 50);
       assignWardbtn.setBounds(215, 100,  200, 30);
       logoutbtn.setBounds(435, 100,  170, 30);
       
       String [] cols= {"Patient_Id","Name","Address","Phone","DOB","Doctor","Gender","Description","Date"};
       model=new DefaultTableModel(cols,0);
       tbl=new JTable(model);
       
       JScrollPane sp=new JScrollPane(tbl);
       sp.setBounds(30, 150, 700, 370);
       

       add(addPatientbtn);
//       add(viewPatientDetailsbtn);
       add(assignWardbtn);
       add(logoutbtn);
       add(titlelbl);
       add(sp);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750,600);
       setVisible(true); 
       
       
       try {
    	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_hospital" + 
                   "?user=admin&password=test123&useSSL=false");

       
       } catch (SQLException ex) {
           // handle any errors
           System.out.println("SQLException: " + ex.getMessage());
           System.out.println("SQLState: " + ex.getSQLState());
           System.out.println("VendorError: " + ex.getErrorCode());
       }
       remove_table();
       displayTable();
       
   	}
   
   public void displayTable() {
       
       try {
           String sql="select * from patient_details";
           PreparedStatement stmt=conn.prepareStatement(sql);
           ResultSet result=stmt.executeQuery();
           
           while(result.next()) {
               
               String id=result.getString("id");
               String name=result.getString("name");
               String address=result.getString("address");
               String phone=result.getString("phone");
               String dob=result.getString("dob");
               String doctorName=result.getString("doctor_name");
               String gender = result.getString("gender");
               String description = result.getString("description");
               String date = result.getString("date");
               String medical_history = result.getString("medical_history");
               model.addRow(new Object[] {id,name,address,phone,dob,doctorName,gender,description,date,medical_history});
           }
       }
       catch(Exception ee) {
           ee.printStackTrace();
       }
   }
   public void remove_table() {
       for(int i=model.getRowCount() -1;i>0;i--) {
           
           model.removeRow(i);
       }
      
  }
            
   
   public static void main(String[] args) {
	   AdminDashboard f = new AdminDashboard();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       Object source=e.getSource();
       
       if(source==addPatientbtn) {
    	   PatientDetails f = new PatientDetails();
           dispose();
       }else if (source == logoutbtn) {
    	   Login f = new Login();
           dispose();
       } else if (source == assignWardbtn) {
    	   AssignWard f = new AssignWard();
           dispose();
       }



   }

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
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