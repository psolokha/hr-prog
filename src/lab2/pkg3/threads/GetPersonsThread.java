package lab2.pkg3.threads;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lab2.pkg3.Lab23;
import lab2.pkg3.Person;
import lab2.pkg3.SQLConnection;

public class GetPersonsThread implements Runnable{

    private Thread self;
    
    public GetPersonsThread(){
        self = new Thread(this);
        self.setName("GetPersonsThread");
    }
    
    public void start(){
        self.start();
    }
    
    public void join() throws InterruptedException{
        self.join();
    }
    
    @Override
    public void run() {
        try {
            
            
            Lab23.personsList.clear();

            Connection conn = SQLConnection.sqlConnect();

            Statement st = conn.createStatement();

            st.executeUpdate("USE db");
            ResultSet rs = st.executeQuery("SELECT * FROM persons ORDER BY id");

            while(rs.next()){
            Lab23.personsList.add(new Person(Integer.parseInt(rs.getString("id")), rs.getString("name"),Integer.parseInt(rs.getString("age")),rs.getString("photo")));
            }
            System.out.println(Lab23.personsList.toString());
        
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " !!!!!");
           
        }
        
        System.out.println("UsersList Completed!");
    }
    
}
