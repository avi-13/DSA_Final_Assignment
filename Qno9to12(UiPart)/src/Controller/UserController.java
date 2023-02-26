package Controller;

import Database.DbConnection;
import Model.Job;
import Model.Task;
import Model.User;
import Model.task_screen;
import Views.LoginScreen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    DbConnection db =new DbConnection();


    public int registerCustomerPreparedStatement(User user) {
        try {
            String query = "insert into user(username,password,email) values(?,?,?)";
            PreparedStatement st = db.connection.prepareStatement(query);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            return db.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public User loginCustomerPreparedStatement(String username, String password) {
        User user = null;
        try {
            String query;
            query = "select userId from user where username =? and password =?";
            PreparedStatement pt = db.connection.prepareStatement(query);
            pt.setString(1, username);
            pt.setString(2, password);
            ResultSet rs = db.retrieve(pt);
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                LoginScreen.USER_ID=rs.getInt("userId");
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return user;
    }

    public User fetchLoggedInCustomer() {
        User user = null;
        try {
            String query;
            query = "select * from user where userId =?";
            PreparedStatement pt = db.connection.prepareStatement(query);
            pt.setInt(1, LoginScreen.USER_ID);
            ResultSet rs = db.retrieve(pt);
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return user;
    }

    public int taskCustomerPreparedStatement(Task task) {
        try {
            String query = "insert into task(taskId,taskName) values(?,?)";
            PreparedStatement st = db.connection.prepareStatement(query);
            st.setInt(1, task.getTaskId());
            st.setString(2, task.getTaskName());
            return db.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int jobCustomerPreparedStatement(Job job) {
        try {
            String query = "insert into job(jobId,jobName) values(?,?)";
            PreparedStatement st = db.connection.prepareStatement(query);
            st.setInt(1, job.getJobId());
            st.setString(2, job.getJobName());
            return db.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int taskScreenCustomerPreparedStatement(task_screen taskScreen) {
        try {
            String query = "insert into taskScreen(jobId,task1,task2) values(?,?,?)";
            PreparedStatement st = db.connection.prepareStatement(query);
            st.setInt(1, taskScreen.getJobId());
            st.setString(2, taskScreen.getTask1());
            st.setString(3, taskScreen.getTask2());
            return db.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
