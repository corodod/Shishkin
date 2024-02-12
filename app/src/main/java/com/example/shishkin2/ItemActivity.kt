package com.example.shishkin2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        val filmName = intent.getStringExtra("filmName")
        val description = intent.getStringExtra("description")
        val genre = intent.getStringExtra("genre")
        val country = intent.getStringExtra("country")
        val imageId = intent.getIntExtra("imageId", R.drawable.mycat) // Получение идентификатора изображения

        val nameTextView: TextView = findViewById(R.id.nameofFilm)
        val descriptionTextView: TextView = findViewById(R.id.descriptionOfFilm)
        val genreTextView: TextView = findViewById(R.id.genreOfFilm)
        val countryTextView: TextView = findViewById(R.id.countriesOfFilm)
        val imageView: ImageView = findViewById(R.id.image) // Получение ImageView


        nameTextView.text = filmName
        descriptionTextView.text = description
        genreTextView.text = genre
        countryTextView.text = country
        imageView.setImageResource(imageId)


        val buttonToMain: Button = findViewById(R.id.buttonToMain)
        buttonToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}