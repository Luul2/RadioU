package com.example.radiou

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Method "clickOnPlaylist"
    fun clickOnPlaylist(clickedPlaylist: View?) {
        val playlistName = when (clickedPlaylist?.id) {
            R.id.button80er -> "80er"
            R.id.buttonEDM -> "EDM"
            R.id.button90er -> "90er"
            R.id.buttonFestival -> "festival"
            R.id.buttonHouse -> "house"
            R.id.buttonAfrohouse -> "afrohouse"
            R.id.buttonChill -> "chill"
            R.id.buttonHardstyle -> "hardstyle"
            R.id.buttonTechno -> "techno"
            else -> return
        }

        // Data from RadioSystemStub
        val radioSystem = DescriptionPlaylist()
        val playlistData = radioSystem.getPlaylistData(playlistName)

        // Intent for Playlist-Data
        playlistData?.apply {
            val i = Intent(this@MainActivity, PlaylistActivity::class.java)
                i.putExtra("image", image)
                i.putExtra("title", title)
                i.putExtra("artist", artist)
                i.putExtra("album", album)
                i.putExtra("moderator", moderator)

            startActivity(i)
        }
    }
}