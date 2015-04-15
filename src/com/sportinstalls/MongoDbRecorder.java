package com.sportinstalls;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sportinstalls.model.Line;

import java.net.UnknownHostException;
import java.util.Map;

public class MongoDbRecorder implements Recorder
{
    private MongoClient mongoClient;
    private DB db;
    private DBCollection collection;
    private BulkWriteOperation bulkOperation;


    public MongoDbRecorder() throws UnknownHostException
    {
        mongoClient = new MongoClient();
        db = mongoClient.getDB("sportInt");

        boolean collectionAlreadyExists = db.getCollectionNames().contains("installations");
        collection = db.getCollection("installations");

        if (!collectionAlreadyExists)
            createIndexes();
    }

    public void prepareBulkOperation()
    {
        bulkOperation = collection.initializeUnorderedBulkOperation();
    }

    public void executeBulkOperation()
    {
        bulkOperation.execute();
        bulkOperation = null;
    }

    public void createIndexes()
    {
        DBObject indexes = new BasicDBObject();
        indexes.put("town_lowercase", 1);
        indexes.put("name_lowercase", 1);
        indexes.put("install_number", 1);

        collection.createIndex(indexes);

        indexes = new BasicDBObject();
        indexes.put("geo_loc", "2dsphere");

        collection.createIndex(indexes);

        System.out.println("Indexes created");
        for (DBObject i : collection.getIndexInfo())
            System.out.println(i);
    }

    @Override
    public void record(Line line, String[] idKeys)
    {
        Map<String, Object> fields  = line.getFields();
        BasicDBObject query         = new BasicDBObject();
        BasicDBObject document      = new BasicDBObject();


        for (String idKey : idKeys)
            query.append(idKey, fields.get(idKey));

        for (String fieldKey : fields.keySet())
            document.append(fieldKey, fields.get(fieldKey));

        BasicDBObject set = new BasicDBObject("$set", document);

        if (bulkOperation == null)
            collection.update(query, set, true, false);
        else
            bulkOperation.find(query).upsert().update(set);
    }

    public void print(String town)
    {
        DBCursor cursor = collection.find(new BasicDBObject("town", town));

        try
        {
            while(cursor.hasNext())
                System.out.println(cursor.next());
        }
        finally
        {
            cursor.close();
        }
    }

    public void drop()
    {
        collection.drop();
    }

    @Override
    public void close()
    {
        mongoClient.close();
    }
}
