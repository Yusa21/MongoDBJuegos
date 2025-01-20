import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.dotenv

class ConexionMongo {
    private val dotenv = dotenv()
    private val connectionString = dotenv["URL_MONGODB"]
    private var mongoClient: MongoClient? = null

    fun connect(): MongoDatabase? {
        try{
            if (mongoClient == null) {
                mongoClient = MongoClients.create(connectionString)
            }
            return mongoClient!!.getDatabase(dotenv["DATABASE"])
        }catch(e:Exception){
            println("Error al conectar a MongoDB: ${e.message}")
            return null
        }

    }

    fun close() {
        mongoClient?.close()
        mongoClient = null
    }
}