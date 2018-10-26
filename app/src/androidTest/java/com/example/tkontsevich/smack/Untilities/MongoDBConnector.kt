package com.example.tkontsevich.smack.Untilities

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.MongoException
import com.mongodb.ServerAddress
import java.util.Arrays

object MongoDBConnector {

    internal var user = "tanyakontsevich"
    internal var database = "heroku_p02drcdg"
    internal var password = "Adjoy888".toCharArray()

    @JvmStatic
    fun main(args: Array<String>) {
        val credential = MongoCredential.createCredential(user,
                database,
                password)
        try {
            val mongoClient = MongoClient(ServerAddress("ds115768.mlab.com", 15768),
                    Arrays.asList(credential))
            println("Connected")
            val db = mongoClient.getDatabase(database)
            val collection = db.getCollection("channels")
            collection.drop()
        } catch (e: MongoException) {
            e.printStackTrace()
        }
    }
}
