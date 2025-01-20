import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import org.bson.Document


class GameRepository(private val database: MongoDatabase) {
    //TODO porque no me deja usar dotnev aqui?
    private val collection = database.getCollection("juegos")

    fun insertGame(game: Game): Boolean {
        // Comprueba que el titulo no se repita
        if (collection.find(Filters.eq("title", game.title)).first() != null) {
            return false
        }

        val document = Document()
            .append("title", game.title)
            .append("genre", game.genre)
            .append("price", game.price)
            .append("releaseDate", game.releaseDate)

        collection.insertOne(document)
        return true
    }

    fun listAllGames(): List<Game> {
        return collection.find().map { doc ->
            Game(
                doc.getString("title"),
                doc.getString("genre"),
                doc.getDouble("price"),
                doc.getDate("releaseDate")
            )
        }.toList()
    }

    fun findGamesByGenre(genre: String): List<Game> {
        return collection.find(Filters.eq("genre", genre))
            .sort(Sorts.ascending("title"))
            .map { doc ->
                Game(
                    doc.getString("title"),
                    doc.getString("genre"),
                    doc.getDouble("price"),
                    doc.getDate("releaseDate")
                )
            }.toList()
    }

    fun deleteGamesByGenre(genre: String) {
        collection.deleteMany(Filters.eq("genre", genre))
    }

    fun updateGame(title: String, updatedGame: Game): Boolean {
        // Comprueba que el titulo no se repita
        if (collection.find(Filters.eq("title", updatedGame.title)).first() != null) {
            return false
        }

        collection.updateOne(
            Filters.eq("title", title),
            Updates.combine(
                Updates.set("title", updatedGame.title),
                Updates.set("genre", updatedGame.genre),
                Updates.set("price", updatedGame.price),
                Updates.set("releaseDate", updatedGame.releaseDate)
            )
        )
        return true
    }
}
