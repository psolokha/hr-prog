package lab2.pkg3;


import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab2.pkg3.threads.DeletePersonThread;
import lab2.pkg3.threads.InsertPersonThread;

/**
 *
 * @author sf
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Button addBtn;
    @FXML private Button rmvBtn;
    @FXML private RadioButton rbtnk;
    @FXML private RadioButton rbtnu;
    @FXML private ToggleGroup swtch;
    @FXML private ImageView imgv;
    @FXML private TextField setname;
    @FXML private TextField setage;
    @FXML private TextField setphoto;
    @FXML private TextField setid;
    @FXML private ToggleGroup group;
    @FXML private ListView<Person> plist;
    @FXML private Label label;
    @FXML private Label label1;
    @FXML private Label label2;
    
    
    private String nameValue;
    private Integer ageValue;
    private String pathValue;
    private Integer idValue;
    
    private void fitImage(){
        imgv.setFitWidth(200);
    }
    
    private void valuesSetter(){
        
        nameValue = (setname.getText() == null)?("Person" + Person.personCounter):setname.getText();
        pathValue = (setphoto.getText() == null)?("src/ico.png"):setphoto.getText();
        ageValue = (setage.getText() == null)?0:Integer.parseInt(setage.getText());
        idValue = (setid.getText() == null)?0:Integer.parseInt(setid.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Lab23.personsList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(ListChangeListener.Change changes) {
                plist.setItems(changes.getList());
            }
        });
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    
                    group.getSelectedToggle().isSelected();
                    
                
            }
        });
        
        File file = new File("src/ico.png");
        Image image = new Image(file.toURI().toString());
        imgv.setImage(image);
        fitImage();
        
        
    }
    
    @FXML
    protected void handleMouseClick() throws SQLException{
        Person p1 = Lab23.personsList.get(plist.getSelectionModel().getSelectedIndex());
        setname.setText(p1.getName());
        setage.setText(Integer.toString(p1.getAge()));
        setphoto.setText(p1.getPath());
        setid.setText(Integer.toString(p1.getId()));
        File file = new File(p1.getPath());
        Image image = new Image(file.toURI().toString());
        
        //
        // show/create
        // TableView
        //
        
        //Connection conn = SQLConnection.sqlConnect();
        //Statement st = conn.createStatement();
        
        //PreparedStatement ps = conn.prepareStatement("SELECT img FROM persons WHERE id=?");
        //ps.setInt(1, p1.getId());
        //ps.executeUpdate();
        
        
//        st.executeUpdate("USE db");
//        ResultSet rs = st.executeQuery("SELECT img FROM persons WHERE id=100");
//        Image image = new Image(rs.getBinaryStream("img")); //mail.specialst
        imgv.setImage(image);
        fitImage();
    }
    
    
    @FXML
    protected void unknownButtonSelected(ActionEvent event){
        
        setname.setDisable(true);
        setage.setDisable(true);
        setphoto.setDisable(true);
        setname.setText("Person" + Person.personCounter);
        setage.setText("0");
        setphoto.setText("src/ico.png");
        
    }
    
    @FXML
    protected void knownButtonSelected(ActionEvent event){
        
        setname.setDisable(false);
        setage.setDisable(false);
        setphoto.setDisable(false);
        setname.setText("Person" + Person.personCounter);
        setage.setText("0");
        setphoto.setText("src/ico.png");
        
    } 

    @FXML
    protected void addButtonClick(ActionEvent event) throws SQLException {
        
        valuesSetter();
        InsertPersonThread add = new InsertPersonThread(new Person(setname.getText(), Integer.parseInt(setage.getText()), setphoto.getText()));
        add.start();

//        GetPersonsThread gp = new GetPersonsThread();
//        gp.start();

    }
        
    
    @FXML
    protected void rmvButtonClick(ActionEvent event) throws SQLException{
        
        valuesSetter();
        
        DeletePersonThread rmv = new DeletePersonThread(new Person(Integer.parseInt(setid.getText()),"toDelete",0,"toDelete"));
        rmv.start();
        

    }  
    
    
    
}
