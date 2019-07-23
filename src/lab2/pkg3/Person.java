package lab2.pkg3;

import java.sql.Blob;
import java.util.Random;

public class Person {
    
    public static int personCounter = 0;
    //public static ArrayList<Person> persons = new ArrayList<>();
    //private static LinkedList<Person> list = new LinkedList<>();
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String name;
    private int age;
    private String path;
    private Boolean img;
   
   public Person(){
       
       this.name = "Person " + personCounter;
       this.age = new Random().nextInt(100);
       this.img = false;
       personCounter++;
       
   }
   
   public Person(int id, String name, int age, String path){
       this(name,age,path);
       this.id = id;
   }
   
   public Person(String name, int age, String path){
       this.name = name;
       this.age = age;
       this.path = path;
       this.img = true;
       personCounter++;
   }
   
   public Person(String name, int age){
       this(name, age, null);
       this.img = false;
       personCounter++;
   }
   
   public Person(String name){
       this(name,new Random().nextInt(100), null);
       this.img = false;
       personCounter++;
   }
   
   public Person(int age){
       this("Person" + personCounter, age, null);
       this.img = false;
       personCounter++;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "\nID: " + id + " Name: " + name + " Age: " + age;
    }
    
//    public static void addPersons() throws SQLException{
//        Lab23.personsList.clear();
//        Connection conn = SQLConnection.sqlConnect();
//        Statement st = conn.createStatement();
//        st.executeUpdate("USE db");
//        ResultSet rs = st.executeQuery("SELECT * FROM persons ORDER BY id");
//        while(rs.next()){
//        Lab23.personsList.add(new Person(Integer.parseInt(rs.getString("id")), rs.getString("name"),Integer.parseInt(rs.getString("age")),rs.getString("photo")));
//        }
//        System.out.println(Lab23.personsList.toString());
//    }
   
}
