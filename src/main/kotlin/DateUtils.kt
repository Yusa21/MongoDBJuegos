import java.text.SimpleDateFormat
import java.util.Date

//Objeto usado para asegurarse de que la fechas
object DateUtils {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    //Comprueba que un String siga el formato
    fun parseDate(dateStr: String): Date? {
        return try {
            dateFormat.parse(dateStr)
        } catch (e: Exception) {
            null
        }
    }

    //Transforma un String a una fecha con el formato
    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    fun isValidDateFormat(dateStr: String): Boolean {
        return try {
            dateFormat.parse(dateStr)
            true
        } catch (e: Exception) {
            false
        }
    }
}