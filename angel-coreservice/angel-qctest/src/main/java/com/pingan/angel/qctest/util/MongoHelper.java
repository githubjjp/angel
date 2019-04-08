package com.pingan.angel.qctest.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.index.Index;

import java.util.concurrent.TimeUnit;

public class MongoHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(MongoHelper.class);

    static final String DBName = "mydbs";
    static final String ServerAddress = "192.168.174.200";
    static final int PORT = 29017;

    public MongoHelper() {
    }

    public MongoClient getMongoClient() {
        MongoClient mongoClient = null;
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient(ServerAddress, PORT);
            logger.debug("Connect to mongodb successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return mongoClient;
    }

    public MongoDatabase getMongoDataBase(MongoClient mongoClient) {
        MongoDatabase mongoDataBase = null;
        try {
            if (mongoClient != null) {
                // 连接到数据库
                mongoDataBase = mongoClient.getDatabase(DBName);
                logger.debug("Connect to DataBase successfully");
            } else {
                throw new RuntimeException("MongoClient不能够为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public MongoDatabase getMongoDataBase() {
        MongoDatabase mongoDataBase = null;
        try {
            // 连接到数据库
            mongoDataBase = getMongoDataBase(getMongoClient());
            mongoDataBase.getCollection("a").createIndex(Indexes.text("date"),new IndexOptions().expireAfter(60*60L, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public void closeMongoClient(MongoDatabase mongoDataBase,
                                 MongoClient mongoClient) {
        if (mongoDataBase != null) {
            mongoDataBase = null;
        }
        if (mongoClient != null) {
            mongoClient.close();
        }
        logger.debug("CloseMongoClient successfully");
    }

}
