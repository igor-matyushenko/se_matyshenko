package sef.module18.activity;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmployeeRepositoryImplTest {
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String username = "sa";
        String password = "";
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");

    }

    @Test
    public void testFindEmployeeByID() {
        EmployeeRepository er = new EmployeeRepositoryImpl(conn);
        try {
            Employee result = er.findEmployeeByID(1);

            assertEquals(result.getEmployeeID(), 1);
            assertEquals(result.getFirstName().toUpperCase(), "JOHN");
            assertEquals(result.getLastName().toUpperCase(), "DOE");
            assertEquals(result.getProfLevel(), 1);
        } catch (HRSSystemException e) {
            fail();
        }
    }

    @Test
    public void testFindEmployeeByName() {
        EmployeeRepository er = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = er.findEmployeeByName("J", "DOE");

            assertEquals(results.get(0).getEmployeeID(), 1);
            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);

            assertEquals(results.get(1).getEmployeeID(), 2);
            assertEquals(results.get(1).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(1).getProfLevel(), 2);

        } catch (HRSSystemException e) {
            fail();
        }
    }

    @Test
    public void testFindEmployeeByProfLevel() {
        EmployeeRepository er = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = er.findEmployeeByProfLevel(3);

            assertEquals(results.get(0).getEmployeeID(), 3);
            assertEquals(results.get(0).getFirstName().toUpperCase(), "TONI");
            assertEquals(results.get(0).getLastName().toUpperCase(), "LARSON");
            assertEquals(results.get(0).getProfLevel(), 3);

            assertEquals(results.get(1).getEmployeeID(), 4);
            assertEquals(results.get(1).getFirstName().toUpperCase(), "JAMES");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DONNELL");
            assertEquals(results.get(1).getProfLevel(), 3);
        } catch (HRSSystemException e) {
            fail();
        }
    }

    @Test
    public void testFindEmployeeByProject() {
        EmployeeRepository er = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = er.findEmployeeByProject(3);

            assertEquals(results.get(0).getEmployeeID(), 2);
            assertEquals(results.get(0).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 2);

            assertEquals(results.get(1).getEmployeeID(), 4);
            assertEquals(results.get(1).getFirstName().toUpperCase(), "JAMES");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DONNELL");
            assertEquals(results.get(1).getProfLevel(), 3);
        } catch (HRSSystemException e) {
            fail();
        }
    }

    @Test
    public void testInsertEmployee() {
        EmployeeRepository er = new EmployeeRepositoryImpl(conn);
        try {
            Employee employee = new EmployeeImpl(34,"IGOR","MATYUSHENKO",3);
            int res = er.insertEmployee(employee);
//                assertEquals(res, employee.getEmployeeID() );
                assertEquals(employee.getFirstName(), "IGOR");
                assertEquals(employee.getLastName(), "MATYUSHENKO");
                assertEquals(employee.getProfLevel(), 3);


        }
        catch (HRSSystemException  e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public  void testUpdateEmployee(){
        EmployeeRepository er=new EmployeeRepositoryImpl(conn);
        Employee employee = new EmployeeImpl(5,"IGOR","Larson",3);
        try {
            boolean res=er.updateEmployee(employee);
            Assert.assertTrue(res);
            assertEquals(employee.getEmployeeID(), 5);
            assertEquals(employee.getFirstName(), "IGOR");
            assertEquals(employee.getLastName(), "Larson");
            assertEquals(employee.getProfLevel(), 3);
        }catch (HRSSystemException e){
            e.getMessage();
        }

    }
    @After
    public void tearDown() throws Exception {
        try{
            conn.close();
            System.out.println("Connection closed!");
        }catch(AssertionFailedError e){
            fail();
        }
    }
}