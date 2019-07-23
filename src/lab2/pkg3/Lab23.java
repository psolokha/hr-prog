package lab2.pkg3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab23 extends Application {
    
    public static ObservableList<Person> personsList = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage stage) throws Exception {
        //SQLConnection.driverTest();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
//        Person p1 = new Person();
//        Person p2 = new Person();
//        Person p3 = new Person();
//        Person p4 = new Person();
//        PersonToSQL.insertPerson(p1);
//        PersonToSQL.insertPerson(p2);
//        PersonToSQL.insertPerson(p3);
//        PersonToSQL.insertPerson(p4);
        
        Connection conn = SQLConnection.sqlConnect();
        try {
            Statement st = conn.createStatement();
            //st.executeUpdate("CREATE DATABASE db");
            st.executeUpdate("USE db");
            st.executeUpdate("CREATE TABLE persons (id int(4) not null primary key auto_increment, name varchar(32), age int(3), photo varchar(32))");
			
            //st.executeUpdate("USE db");
            //st.executeUpdate("INSERT INTO persons (name,age,photo) VALUES ('Jack Daniels', 7, 'src/jd.jpg')");
//            ResultSet rs = st.executeQuery("SELECT * FROM persons ORDER BY id");
//            while(rs.next()) {
//                System.out.println( rs.getString("id") + " "
//                                                +rs.getString("name") + " " 
//                                                +rs.getString("age") + " " 
//                                                +rs.getString("photo"));
//            }
            
            //st.executeUpdate("ALTER TABLE persons ADD img blob");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " trtrt");
        }
        
            
        launch(args);
        
        
    }
    
}
