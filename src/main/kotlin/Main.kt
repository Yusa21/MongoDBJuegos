fun main() {
    val dbConnection = ConexionMongo()
    val database = dbConnection.connect()
    if(database!=null){
        val repository = GameRepository(database)
        val gameService = GameService(repository)

        while (true) {
            println("\n=== Sistema de control de juegos ===")
            println("1. Listar todos los juegos")
            println("2. Buscar por genero")
            println("3. Anadir nuevo juego")
            println("4. Actualizar juego")
            println("5. Borrar por genero")
            println("6. Salir")
            print("Elije una opcion: ")

            when (readLine()) {
                "1" -> {
                    println("\nTodos los juegos:")
                    gameService.getAllGames().forEach { game ->
                        println("Titulo: ${game.title}")
                        println("Genero: ${game.genre}")
                        println("Precio: $${game.price}")
                        println("Fecha de lanzamiento: ${DateUtils.formatDate(game.releaseDate)}")
                        println("------------------------")
                    }
                }

                "2" -> {
                    print("Introduce genero para buscar: ")
                    val genre = readLine() ?: ""
                    println("\nJuegos del genero '$genre':")
                    gameService.getGamesByGenre(genre).forEach { game ->
                        println("Titulo: ${game.title}")
                        println("Precio: $${game.price}")
                        println("Fecha de lanzamiento: ${DateUtils.formatDate(game.releaseDate)}")
                        println("------------------------")
                    }
                }

                "3" -> {
                    print("Introduce titulo: ")
                    val title = readLine() ?: ""
                    print("Introduce genero: ")
                    val genre = readLine() ?: ""
                    print("Introduce precio: ")
                    val price = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Introduce fecha de lanzamiento (YYYY-MM-DD): ")
                    val releaseDate = readLine() ?: ""

                    try {
                        //El repositorio devuelve falso si no se ha añadido el juego, esto signifca que el titulo esta repetido
                        if (gameService.addNewGame(title, genre, price, releaseDate)) {
                            println("El juego ha sido anadido")
                        } else {
                            println("Error: Un juego con este nombre ya existe")
                        }
                    } catch (e: IllegalArgumentException) {
                        println("Error: ${e.message}")
                    }
                }

                "4" -> {
                    print("Introduce el titulo del juego a actualizar: ")
                    val oldTitle = readLine() ?: ""
                    print("Introduce un nuevo titulo: ")
                    val newTitle = readLine() ?: ""
                    print("Introduce un nuevo genero: ")
                    val genre = readLine() ?: ""
                    print("Introduce un nuevo precio: ")
                    val price = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Introduce una nueva fecha de lanzamiento (YYYY-MM-DD): ")
                    val releaseDate = readLine() ?: ""

                    try {
                        //El repositorio devuelve falso si no se ha añadido el juego, esto signifca que el titulo esta repetido
                        if (gameService.updateGame(oldTitle, newTitle, genre, price, releaseDate)){
                            println("El juego ha sido actualizado")
                        } else {
                            println("Error: Un juego con este nombre ya existe")
                        }
                    } catch (e: Exception) {
                        println("Error actualizando juego: ${e.message}")
                    }
                }

                "5" -> {
                    print("Introduce genero a borrar: ")
                    val genre = readLine() ?: ""
                    gameService.deleteGamesByGenre(genre)
                    println("Todos los juegos del genero '$genre' se han borrado.")
                }

                "6" -> {
                    dbConnection.close()
                    println("Saliendo")
                    return
                }

                else -> println("Opcion invalida")
            }
        }
    }else{
        println("Hubo un erro al conectar con la base de datos")
    }

}