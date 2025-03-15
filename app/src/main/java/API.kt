package com.example.radiou

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.IOException

fun sendWishes(songWish: String) {
    val url = "https://your-api-endpoint.com/addSong"

    // Erstelle das JSON-Objekt
    val json = JSONObject().apply {
        put("Songwunsch", songWish)
        put("Datum", "2025-03-15")
    }

    // Erstelle den RequestBody
    val body = RequestBody.create(
        "application/json; charset=utf-8".toMediaType(),
        json.toString().toByteArray(Charsets.UTF_8)
    )

    // Baue die HTTP-POST-Anfrage
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    // Erstelle einen OkHttpClient
    val client = OkHttpClient()

    // Führe die Anfrage asynchron aus
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()  // Fehlerbehandlung
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                println("Antwort erfolgreich: ${response.body?.string()}")
            } else {
                println("Fehler: ${response.code} - ${response.message}")
            }
        }
    })
}
