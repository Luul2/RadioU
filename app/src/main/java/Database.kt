import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.DriverManager
import java.sql.SQLException

class Database {

    companion object {

        private const val URL = "jdbc:sqlserver://sqlserver-tb.database.windows.net:1433;databaseName=Testdatenbank;encrypt=true;trustServerCertificate=true;loginTimeout=30"
        private const val USER = "TomBerkes"
        private const val PASSWORD = "*****-*****-*****"

        // Methode zum Einfügen eines Likes
        suspend fun insertLike(): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    DriverManager.getConnection(URL, USER, PASSWORD).use { connection ->
                        val query = "INSERT INTO Like (Datum) VALUES (?)"
                        connection.prepareStatement(query).use { statement ->
                            statement.setTimestamp(1, java.sql.Timestamp(System.currentTimeMillis()))
                            statement.executeUpdate()
                        }
                    }
                    true
                } catch (e: SQLException) {
                    e.printStackTrace()
                    false
                }
            }
        }

        // Methode zum Einfügen eines Likes
        suspend fun insertDislike(): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    DriverManager.getConnection(URL, USER, PASSWORD).use { connection ->
                        val query = "INSERT INTO Dislike (Datum) VALUES (?)"
                        connection.prepareStatement(query).use { statement ->
                            statement.setTimestamp(1, java.sql.Timestamp(System.currentTimeMillis()))
                            statement.executeUpdate()
                        }
                    }
                    true
                } catch (e: SQLException) {
                    e.printStackTrace()
                    false
                }
            }
        }

        // Methode zum Einfügen eines Songwunsches
        suspend fun insertSongRequest(songWish: String): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    DriverManager.getConnection(URL, USER, PASSWORD).use { connection ->
                        val query = "INSERT INTO Songwunsch (Wunsch, Datum) VALUES (?, ?)"
                        connection.prepareStatement(query).use { statement ->
                            statement.setString(1, songWish)
                            statement.setTimestamp(2, java.sql.Timestamp(System.currentTimeMillis()))
                        }
                    }
                    true
                } catch (e: SQLException) {
                    e.printStackTrace()
                    false
                }
            }
        }
        // Methode zum Einfügen einer Bewertung
        suspend fun insertRating(rating: Float): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    DriverManager.getConnection(URL, USER, PASSWORD).use { connection ->
                        val query = "INSERT INTO Moderatorenbewertung (Bewertung, Datum) VALUES (?, ?)"
                        connection.prepareStatement(query).use { statement ->
                            statement.setFloat(1, rating)
                            statement.setTimestamp(2, java.sql.Timestamp(System.currentTimeMillis()))
                        }
                    }
                    true
                } catch (e: SQLException) {
                    e.printStackTrace()
                    false
                }
            }
        }
    }
}
