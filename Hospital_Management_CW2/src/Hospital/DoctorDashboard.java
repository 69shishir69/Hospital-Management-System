package Hospital;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class DoctorDashboard extends JFrame implements ActionListener{
	 
	 JLabel titlelbl;
     JButton viewPatientDetailsbtn;
     JButton viewAdmitDetailsbtn;
     JButton logoutbtn;
     JButton showLabReportbtn;
     String doctor_name;

     DoctorDashboard(String name) {
    	 doctor_name = name;
    	 
       setTitle("Doctor");
       setLayout(null);

       viewAdmitDetailsbtn=new JButton("View Admit Details");
       viewPatientDetailsbtn=new JButton("View Patient Details");
       showLabReportbtn=new JButton("Show Lab Report");
       logoutbtn=new JButton("Logout");
       

       viewAdmitDetailsbtn.addActionListener(this);
       viewPatientDetailsbtn.addActionListener(this);
       logoutbtn.addActionListener(this);
       showLabReportbtn.addActionListener(this);

       titlelbl = new JLabel("Doctor Dashboard");
       titlelbl.setBounds(50, 35, 400, 40);
       titlelbl.setFont(new Font("Arial", Font.PLAIN, 40));
       viewAdmitDetailsbtn.setBounds(120, 100, 150, 50);
       viewPatientDetailsbtn.setBounds(120, 180,  150, 50);
       showLabReportbtn.setBounds(120, 260,  150, 50);
       logoutbtn.setBounds(120, 340, 150, 50);
       
       add(titlelbl);
       add(viewAdmitDetailsbtn);
       add(viewPatientDetailsbtn);
       add(logoutbtn);
       add(showLabReportbtn);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(420,600);
       setVisible(true); 

   }

//   public static void main(String[] args) {
//       DoctorDashboard f = new DoctorDashboard("doc2");
//   }

   @Override
   public void actionPerformed(ActionEvent e) {
       Object source=e.getSource();

       if(source==viewAdmitDetailsbtn) {
           ViewAdmitDetails_Doctor f = new ViewAdmitDetails_Doctor(doctor_name);
           dispose();
       }else if (source == logoutbtn) {
           Login f = new Login();
           dispose();
       }else if (source == viewPatientDetailsbtn) {
           ViewPatientDetails_Doctor f = new ViewPatientDetails_Doctor(doctor_name);
           dispose();
       }
       else if (source == showLabReportbtn) {
    	   ShowLabReport_Doctor f = new ShowLabReport_Doctor(doctor_name);
           dispose();
       }

   }


}
