package com.example.radiou

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Database {

    companion object {

        private const val URL = "jdbc:sqlserver://sqlserver-tb.windows.net:1433;databaseName=Testdatenbank"
        private const val USER = "TomBerkes"
        private const val PASSWORD = "cyjcit-cenjes-9jIkfe"
        // Methode zum Einfügen eines Songwunsches in die Datenbank
        suspend fun insertSongRequest(songWish: String): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    // JDBC-Treiber laden
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    DriverManager.getConnection(URL, USER, PASSWORD).use { connection ->
                        // SQL-Abfrage zum Einfügen eines Songwunsches in die Tabelle Songwunsch
                        val query = "INSERT INTO Songwunsch (Wunsch, Datum) VALUES (?, ?)"
                        connection.prepareStatement(query).use { statement ->
                            statement.setString(1, songWish)  // Songwunsch in die Spalte Wunsch
                            statement.setTimestamp(2, java.sql.Timestamp(System.currentTimeMillis()))  // Aktuelles Datum in die Spalte Datum
                            statement.executeUpdate()  // Abfrage ausführen
                        }
                    println("Erfolgreich")
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

