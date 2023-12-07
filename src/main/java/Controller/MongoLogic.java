package Controller;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoLogic {

    private final String uri = "mongodb+srv://SWENG_411:SWENG_411@cluster0.q4rjfic.mongodb.net/?retryWrites=true&w=majority";
    //private static final Logger logger = Logger.getLogger(MongoDatabase.class);
    private MongoCollection<Document> col;


    private void connectUsers(){

        //MongoClient mongoClient = MongoClients.create(uri);
        //MongoDatabase database = mongoClient.getDatabase("SWENG_411");
        //col = database.getCollection("Users");
    }

    public void addUser( String username, String password ){

        connectUsers();

        Document User = new Document()
                .append("Username", username)
                .append("Password", password);

        col.insertOne( User );
    }

    public static void main( String[] args ){
        MongoLogic logic = new MongoLogic();

        logic.addUser("Test", "1234");
    }
}
