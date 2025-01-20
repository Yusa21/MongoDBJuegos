class GameService(private val repository: GameRepository) {
    //Anade un juego nuevo
    fun addNewGame(title: String, genre: String, price: Double, releaseDateStr: String): Boolean {
        if (title.isBlank()) { //Comprueba que el titulo no este en blanco
            throw IllegalArgumentException("El titulo es obligatorio")
        }
        val releaseDate = DateUtils.parseDate(releaseDateStr) //Comprueba que la fecha sigue el formato
            ?: throw IllegalArgumentException("Formato de fecha invalido. Usa YYYY-MM-DD")

        return repository.insertGame(Game(title, genre, price, releaseDate))
    }

    //Devuelve todos los juegos
    fun getAllGames() = repository.listAllGames()

    //Devuelve todos los juegos de un genero en especifico
    fun getGamesByGenre(genre: String) = repository.findGamesByGenre(genre)

    //Borrar todos los juegos de un genero
    fun deleteGamesByGenre(genre: String) = repository.deleteGamesByGenre(genre)

    //Actualiza un juego
    fun updateGame(oldTitle: String, newTitle: String, genre: String, price: Double, releaseDateStr: String): Boolean {
        if (newTitle.isBlank()) { //Comprueba que el titulo no este en blanco
            throw IllegalArgumentException("El titulo es obligatorio")
        }
        val releaseDate = DateUtils.parseDate(releaseDateStr) //Comprueba que la fecha siga el formato
            ?: throw IllegalArgumentException("Formato de fecha invalido. Usa YYYY-MM-DD")

        return repository.updateGame(oldTitle, Game(newTitle, genre, price, releaseDate))
    }
}