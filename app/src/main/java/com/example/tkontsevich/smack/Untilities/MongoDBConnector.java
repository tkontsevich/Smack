package com.example.tkontsevich.smack.Untilities;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;

public class MongoDBConnector {

    static String user="tanyakontsevich";
    static String database="heroku_p02drcdg";
    static char[] password="Adjoy888".toCharArray();


    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MongoCredential credential = MongoCredential.createCredential(user,
                database,
                password);
        MongoClient mongoClient = null;
        // making changes to trigger build?
        // more changes?
        // again
        // and again??


        try {
            mongoClient =
                    new MongoClient(new ServerAddress("ds115768.mlab.com", 15768),
                            Arrays.asList(credential));
            //mongodb://user123:pass!123@192.168.1.10:27017/socketTimeoutMS=1000&authSource=my_db
            System.out.println("Connected");
            MongoDatabase db = mongoClient.getDatabase(database);
            System.out.println(db.getName());
            MongoCollection collection = db.getCollection("channels");

            collection.drop();


        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }

    }

}



