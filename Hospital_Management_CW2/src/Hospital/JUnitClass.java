package Hospital;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import junit.extensions.TestSetup;

public class JUnitClass {
    
    Login login;
    PatientDetails patientdetails;
    AssignWard assignward;
    
    @Before
    public void TestSetup() {
        login = new Login();
        patientdetails = new PatientDetails();
        assignward = new AssignWard();
    }
    
    
    @Test
    public void displayTable() {
        assertTrue(patientdetails.displayTable());
    }
    
    @Test
    public void reset() {
        assertTrue(login.reset());
    }
    
    @Test
    public void remove_table() {
        assertTrue(patientdetails.remove_table());
    }
    
    @Test
    public void display1() {
        assertTrue(assignward.display1());
    }
    
    @Test
    public void search(){
        String inputTxt = "Shishir";
        int column = 1;
        String[][] data = {{"7", "Shishir", "ktm", "9840117150","8th sept 2001", "doctor", "Male", "Headache", "04 Sep 2021"},
                {"3", "Sameer", "gulariya", "9864346200", "doctor1", "Male", "Headache", "04 Oct 2021"}};
        assertEquals("found", patientdetails.search(inputTxt,column,data));
    }
    
    @Test
    public void sort() {
        String [][] input = {{"2","Sameer Kandel"}, {"1", "Shishir Kandel"}};
        String [][] expectedResult = {{"1", "Shishir Kandel"},{"2","Sameer Kandel"}};
        assertArrayEquals(expectedResult, patientdetails.sort(input));
    }
    
    @Test
    public void reverseData() {
        String [][] input = {{"1", "Shishir Kandel"}, {"2","Sameer Kandel"}};
        String [][] expectedResult = {{"2","Sameer Kandel"},{"1", "Shishir Kandel"}};
        assertArrayEquals(expectedResult, patientdetails.reverseData(input));
    }
    
    
    

}