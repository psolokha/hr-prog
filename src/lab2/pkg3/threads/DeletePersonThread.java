package lab2.pkg3.threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import lab2.pkg3.Person;
import lab2.pkg3.SQLConnection;

public class DeletePersonThread implements Runnable{
    
    private Thread self;
    private Person person;

    public DeletePersonThread(Person person){
        self = new Thread(this);
        this.person = person;
        self.setName("InsertPersonThread");
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
        Connection conn = SQLConnection.sqlConnect();
        
        Statement st = conn.createStatement();
        st.executeUpdate("USE db");
        
        PreparedStatement ps = conn.prepareStatement("DELETE FROM persons WHERE id = ?");
        ps.setInt(1, person.getId());
        ps.executeUpdate();
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " ?!?!?!");
        }
       
       GetPersonsThread gp = new GetPersonsThread();
       gp.start();
       
       System.out.println("Person " + person.getId() + " deleted from db");
        
    }
    
    
    
}
