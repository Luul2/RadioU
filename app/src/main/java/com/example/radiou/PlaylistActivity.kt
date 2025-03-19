package com.example.radiou

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class PlaylistActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Übergebenen Daten geholt
        val image = intent.getIntExtra("image", R.drawable.ic_launcher_background)
        val title = intent.getStringExtra("title") ?: "Unknown"
        val artist = intent.getStringExtra("artist") ?: "Unknown"
        val album = intent.getStringExtra("album") ?: "Unknown"
        val moderator = intent.getStringExtra("moderator") ?: "Unknown"

        // Daten in die Views gesetzt
        val imageView: ImageView = findViewById(R.id.imageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val artistTextView: TextView = findViewById(R.id.artistTextView)
        val albumTextView: TextView = findViewById(R.id.albumTextView)
        val moderatorTextView: TextView = findViewById(R.id.moderatorTextView)

        // Werte in die Views gesetzt
        imageView.setImageResource(image)
        titleTextView.text = title
        artistTextView.text = artist
        albumTextView.text = album
        moderatorTextView.text = moderator

        // Like Button
        val likeButton: ImageButton = findViewById(R.id.likeButton)
        val dislikeButton: ImageButton = findViewById(R.id.dislikeButton)
        likeButton.setOnClickListener {
            sendLike()
            likeButton.visibility = View.GONE
            likeButton.postDelayed({
                likeButton.visibility = View.VISIBLE},300)}

        // Dislike Button
        dislikeButton.setOnClickListener{
            sendDislike()
            dislikeButton.visibility = View.GONE
            dislikeButton.postDelayed({
                dislikeButton.visibility = View.VISIBLE}, 300)}


        // Songwünsche
        val songWishesEditText: EditText = findViewById(R.id.songWishesEditText)
        val songWishButton: ImageButton = findViewById(R.id.songWishButton)

        songWishButton.setOnClickListener{
            val enteredText = songWishesEditText.text.toString()

            if(enteredText.isNotEmpty()){
                songWishesEditText.setText("                              Danke :)")
                sendWishes(enteredText)
            }
            else {
                Toast.makeText(baseContext, "Bitte einen Songwunsch eingeben.", Toast.LENGTH_SHORT).show()
            }
        }

        // Sternebewertung
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val sendRatingButton: Button = findViewById(R.id.sendRatingButton)
        val ratingTextView: TextView = findViewById(R.id.ratingTextView)
        ratingBar.rating = 2.5f
        ratingTextView.text = "Geht schon"
        ratingBar.setOnRatingBarChangeListener { rBar, fl, _ ->
            ratingTextView.text = fl.toString()
            when {
                fl in 0f..1f -> ratingTextView.text = "Sehr schlecht"
                fl in 1f..2f -> ratingTextView.text = "Schlecht"
                fl in 2f..3f -> ratingTextView.text = getString(R.string.geht_schon)
                fl in 3f..4f -> ratingTextView.text = "Gut"
                fl in 4f..5f -> ratingTextView.text = "Sehr gut"
            }
        }
        sendRatingButton.setOnClickListener {
            sendRatingButton.text = "Danke für deine Bewertung"
            val ratingValue = ratingBar.rating
            sendRating(ratingValue)
        }
    }

    // Übergabe Like
    private fun sendLike() {
        lifecycleScope.launch {
            val success = Database.insertLike()
            if (success) {
                println("Like wurde erfolgreich in der Datenbank gespeichert!")
            } else {
                println("Fehler beim Speichern des Likes.")
            }
        }
    }

    // Übergabe Dislike
    private fun sendDislike() {
        lifecycleScope.launch {
            val success = Database.insertDislike()
            if (success) {
                println("Dislike wurde erfolgreich in der Datenbank gespeichert!")
            } else {
                println("Fehler beim Speichern des Dislikes.")
            }
        }
    }

    // Übergabe Songwunsch
    private fun sendWishes(songWish: String) {
        lifecycleScope.launch {
            val success = Database.insertSongRequest(songWish)
            if (success) {
                println("Songwunsch wurde erfolgreich in der Datenbank gespeichert!")
            } else {
                println("Fehler beim Speichern des Songwunsches.")
            }
        }
    }

    // Übergabe Sternebewertung
    private fun sendRating(ratingValue: Float) {
        lifecycleScope.launch {
            val success = Database.insertRating(ratingValue)
            if (success) {
                println("Bewertung wurden erfolgreich in der Datenbank gespeichert!")
            } else {
                println("Fehler beim Speichern der Bewertung.")
            }
        }
    }

    fun clickOnBackButton(view: View) {
        onBackPressed()
    }


}
