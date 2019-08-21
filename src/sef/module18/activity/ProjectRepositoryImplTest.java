package sef.module18.activity;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectRepositoryImplTest {
        private Connection conn;
//    @Test
//    public void findProjectByID() {
//    }
//
//    @Test
//    public void findProjectByName() {
//    }
//
//    @Test
//    public void findProjectByEmployee() {
//    }
//
//    @Test
//    public void insertProject() {
//    }
//
//    @Test
//    public void updateProject() {
//    }
//    private Connection conn;

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
    public void findProjectByID() {
        ProjectRepository pr=new ProjectRepositoryImpl(conn);
        Project result;
        try {
            result = pr.findProjectByID(2);
            assertEquals(result.getProjectID(), 2);
            assertEquals(result.getProjectName().toUpperCase(), "Time Report System".toUpperCase());
            assertEquals(result.getProjectDescription().toUpperCase(), "A stand-alone application that records and generates time reports.".toUpperCase());

            result = pr.findProjectByID(1);
            assertEquals(result.getProjectID(), 1);
            assertEquals(result.getProjectName().toUpperCase(), "Online Insurance System".toUpperCase());
            assertEquals(result.getProjectDescription().toUpperCase(),
                    "A web application that automates insurance transactions.".toUpperCase());
        } catch (HRSSystemException e) {
            fail();
        }
    }

    @Test
    public void findProjectByName() {
        ProjectRepository pr=new ProjectRepositoryImpl(conn);

        try {
            List<Project> result = pr.findProjectByName("Time Report System");
            assertEquals(result.get(0).getProjectName().toUpperCase(), "Time Report System".toUpperCase());
            assertEquals(result.get(0).getProjectDescription().toUpperCase(),
                    "A stand-alone application that records and generates time reports.".toUpperCase());

            result = pr.findProjectByName("Online Insurance System");
            assertEquals(result.get(0).getProjectID(), 1);
            assertEquals(result.get(0).getProjectName().toUpperCase(), "Online Insurance System".toUpperCase());
            assertEquals(result.get(0).getProjectDescription().toUpperCase(),
                    "A web application that automates insurance transactions.".toUpperCase());
        } catch (HRSSystemException e) {
            fail();
        }
    }
    @Test
    public void findProjectByEmployee() {
        ProjectRepository pr=new ProjectRepositoryImpl(conn);
        try {
            List<Project> results = pr.findProjectByEmployee(1);

            assertEquals(results.get(0).getProjectID(), 1);
            Assert.assertEquals(results.get(0).getProjectName().toUpperCase(),
            "Online Insurance System".toUpperCase());
            Assert.assertEquals(results.get(0).getProjectDescription().toUpperCase(),
                    "A web application that automates insurance transactions.".toUpperCase());
        }catch (HRSSystemException e) {


            System.out.println(HRSSystemException.ERROR_FIND_PROJECT_EMPLOYEEID);
            fail();

        }

    }

    @Test
    public void insertProject() {
        ProjectRepository pr=new ProjectRepositoryImpl(conn);
        try {
            Statement statement = conn.createStatement();
            Project project = new ProjectImpl(44,"System of Secuirity","System of CYBER ATTACK");
        int res = pr.insertProject(project);
            String SQL = "SELECT * FROM Project";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.last();
            int id = resultSet.getInt("ID");
        assertEquals(id,res);
        assertEquals(project.getProjectName().toUpperCase(),"System of Secuirity".toUpperCase());
        assertEquals(project.getProjectDescription().toUpperCase(),"System of CYBER ATTACK".toUpperCase());
        }catch (Exception e){
            System.out.println(HRSSystemException.ERROR_INSERT_PROJECT);
            e.getMessage();
            fail();

        }

    }
    @Test
    public  void updateProject(){
        ProjectRepository pr=new ProjectRepositoryImpl(conn);
        try{
            Project project = new ProjectImpl(5,"Change_name_project","Change_description");
            assertTrue(pr.updateProject(project));
        } catch (HRSSystemException e) {
            e.printStackTrace();
            fail();
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