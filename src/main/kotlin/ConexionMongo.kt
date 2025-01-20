import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.dotenv

class ConexionMongo {
    private val dotenv = dotenv()
    private val connectionString = dotenv["URL_MONGODB"]
    private var mongoClient: MongoClient? = null

    fun connect(): MongoDatabase {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(connectionString)
        }
        return mongoClient!!.getDatabase(dotenv["DATABASE"])
    }

    fun close() {
        mongoClient?.close()
        mongoClient = null
    }
}