package lab2.pkg3.threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lab2.pkg3.Person;
import lab2.pkg3.SQLConnection;

public class InsertPersonThread implements Runnable{
    
    private Thread self;
    private Person person;
    
    public InsertPersonThread(Person person){
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
        
        PreparedStatement ps = conn.prepareStatement("INSERT INTO persons (name, age, photo) VALUES (?,?,?)");
        ps.setString(1, person.getName());
        ps.setInt(2, person.getAge());
        ps.setString(3, person.getPath());
        ps.executeUpdate();
        
        ResultSet rs = st.executeQuery("SELECT * FROM persons ORDER BY id DESC");
        rs.next();
        person.setId(Integer.parseInt(rs.getString("id")));
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+" ???");
        }
        
        
        
        GetPersonsThread gp = new GetPersonsThread();
        gp.start();
        
        System.out.println("id: "+ person.getId() + " " + person.getName() + " added to db");
    }
}
