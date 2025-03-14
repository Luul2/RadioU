package com.example.radiou

data class Playlist(
    val image: Int,
    val title: String,
    val artist: String,
    val album: String,
    val moderator: String
)

class DescriptionPlaylist {

    private val differentPlaylistsData = mapOf(
        "hardstyle" to Playlist(
            image = R.drawable.hardstyleicon,
            title = "Living for the Moment",
            artist = "Ran-D",
            album = "A2 Records 010",
            moderator = "Live für euch: Max von der Lippe"
        ),
        "80er" to Playlist(
            image = R.drawable.achtzigericon,
            title = "Major Tom (völlig losgelöst)",
            artist = "Peter Schilling",
            album = "von Anfang an...bis jetzt.",
            moderator = "Live für euch: Maria Müller"
        ),
        "EDM" to Playlist(
            image = R.drawable.edmicon,
            title = "Motive",
            artist = "Armin van Buuren",
            album = "Breathe In",
            moderator = "Live für euch: John Doe"
        ),
        "festival" to Playlist(
            image = R.drawable.festivalicon,
            title = "I Follow Rivers",
            artist = "Tiesto, Oaks",
            album = "Prismatic: Pack One",
            moderator = "Live für euch: John Doe"
        ),
        "house" to Playlist(
            image = R.drawable.houseicon,
            title = "My My My",
            artist = "Armand van Helden",
            album = "Nympho",
            moderator = "Live für euch: Lisa Beiruth"
        ),
        "afrohouse" to Playlist(
            image = R.drawable.afrohouseicon,
            title = "Conga Mongo",
            artist = "AMEME",
            album = "Power",
            moderator = "Live für euch: Paul Panzer"
        ),
        "chill" to Playlist(
            image = R.drawable.chillicon,
            title = "Sundream",
            artist = "RÜFÜS DU SOL",
            album = "Atlas",
            moderator = "Live für euch: Alina Müller"
        ),
        "techno" to Playlist(
            image = R.drawable.technoicon,
            title = "3am",
            artist = "Mha Iri",
            album = "Mind Bender",
            moderator = "Live für euch: Miles Mothes"
        ),
        "90er" to Playlist(
            image = R.drawable.neunzigericon,
            title = "Fly Away",
            artist = "Lenny Kravitz",
            album = "5",
            moderator = "Live für euch: Tanja Turner"
        )
    )

    // Rückgabe der Playlist-Daten anhand des Namens
    fun getPlaylistData(playlistName: String): Playlist? {
        return differentPlaylistsData[playlistName]
    }
}