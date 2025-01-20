import java.util.Date

data class Game(
    var title: String,
    var genre: String,
    var price: Double,
    val releaseDate: Date
)