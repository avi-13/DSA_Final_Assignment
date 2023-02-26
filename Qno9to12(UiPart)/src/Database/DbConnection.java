package Database;


import java.sql.*;

public class DbConnection {
    // creating instance variables/objects of database related classes
    public Connection connection;
    Statement statement;
    ResultSet resultSet;
    int val;
    public DbConnection(){
        try {
            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establishing connection in the connection object
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dsaAssignment",
                    username,
                    password);
            if (connection !=null){
                System.out.println("Connected to database");
            }else {
                System.out.println("Database Error");
            }
            // creating the statement object
            statement = connection.createStatement();

        }catch (Exception e){
            //handling exception
            System.out.println(e);
            e.printStackTrace();
        }
    }

//    //function to insert, update, delete
//    public int manipulate(String query){
//        try {
//            val = statement.executeUpdate(query);
//            connection.close();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return val;
//    }

    public int manipulate(PreparedStatement st) {
        try {
            val = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public ResultSet retrieve(PreparedStatement st){
        try {
            resultSet=st.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

//    public ResultSet retrieve(String query){
//        try {
//            resultSet=statement.executeQuery(query);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return resultSet;
//    }


    public static void main(String[] args) {
        new DbConnection();
    }
}
