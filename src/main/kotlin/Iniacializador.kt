import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Filters
import io.github.cdimascio.dotenv.dotenv
import org.bson.Document
import java.text.SimpleDateFormat
import java.util.Date

//Anade juegos de ejemplo, completamente aparte del programa principal
fun main() {
    val dotenv = dotenv()
    val connectionString = dotenv["URL_MONGODB"]
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    val database = mongoClient.getDatabase(dotenv["DATABASE"])
    val collection = database.getCollection(dotenv["COL"])

    val sampleGames = listOf(
        createGameDocument(
            "Marvel Rivals",
            "Shooter",
            0.0,
            "2025-11-23T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Elden Ring 2",
            "RPG",
            69.99,
            "2025-02-15T23:00:00.000+00:00"
        ),
        createGameDocument(
            "FIFA 25",
            "Sports",
            59.99,
            "2024-09-28T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Starfield 2",
            "RPG",
            69.99,
            "2026-11-11T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Call of Duty: Modern Warfare IV",
            "Shooter",
            69.99,
            "2024-11-05T23:00:00.000+00:00"
        ),
        createGameDocument(
            "The Legend of Zelda: Echoes of the Past",
            "Adventure",
            59.99,
            "2025-05-15T23:00:00.000+00:00"
        ),
        createGameDocument(
            "NBA 2K25",
            "Sports",
            59.99,
            "2024-09-15T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Grand Theft Auto VI",
            "Action",
            69.99,
            "2025-03-25T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Destiny 3",
            "Shooter",
            49.99,
            "2025-09-09T23:00:00.000+00:00"
        ),
        createGameDocument(
            "Final Fantasy XVII",
            "RPG",
            69.99,
            "2025-07-20T23:00:00.000+00:00"
        )
    )

    try {
        var gamesInserted = 0

        // Check and insert each game
        sampleGames.forEach { gameDoc ->
            val title = gameDoc.getString("title")
            val existingGame = collection.find(Filters.eq("title", title)).first()

            if (existingGame == null) {
                collection.insertOne(gameDoc)
                println("Inserted: $title")
                gamesInserted++
            } else {
                println("Skipped (already exists): $title")
            }
        }

        println("\nInicializacion completa!")
        println("Juegos anadidos: $gamesInserted")
        println("Juegos saltado: ${sampleGames.size - gamesInserted}")

    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        mongoClient.close()
    }
}

private fun createGameDocument(title: String, genre: String, price: Double, releaseDateStr: String): Document {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val releaseDate = dateFormat.parse(releaseDateStr)

    return Document()
        .append("title", title)
        .append("genre", genre)
        .append("price", price)
        .append("releaseDate", releaseDate)
}