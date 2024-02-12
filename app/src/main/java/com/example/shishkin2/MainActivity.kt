package com.example.shishkin2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import com.google.gson.annotations.SerializedName


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonShowPopular: Button = findViewById(R.id.buttonShowPopular)
        val buttonShowFavourites: Button = findViewById(R.id.buttonShowFavourites)
        val text:TextView = findViewById(R.id.textViewTitle)


        //TODO я честно долго пытался считывать данные, непонятные проблемы с импортами, пробовал
        // разные варианты, сделал со своими данными, так работает, реализовал поиск, отметки фильмов и т.д.


        val listOfFilms:RecyclerView = findViewById(R.id.listof_films)
        val films = arrayListOf<ItemFilm>()
        val favouritesFilms = arrayListOf<ItemFilm>()
        films.add(ItemFilm(1,"kotik","mycat",2010,"Моя кошка Муся", "Домашние животные", "Россия"))
        films.add(ItemFilm(2,"sobaka","dog",2012,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(3,"yaico","mycat",2021,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(4,"kot","mycat",20200,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(5,"klybok","mycat",2022,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(6,"zemluanika","mycat",2023,"Моя кошка Муся", "Домашние животные", "Россия"))
        films.add(ItemFilm(7,"sabaka","dog2",2020,"Моя кошка Муся", "Домашние животные", "Россия"))
        films.add(ItemFilm(8,"shalash","mycat",2000,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(9,"sabaka2","dog2",2020,"Крутое описание, работает как надо", "Домашние животные", "Россия"))
        films.add(ItemFilm(10,"shalash2","mycat",2000,"Крутое описание, работает как надо", "Домашние животные", "Россия"))


        listOfFilms.layoutManager = LinearLayoutManager(this)//надо чтобы друг под дружкой
        listOfFilms.adapter = ItemsAdapter(films,this,favouritesFilms)

        val searchEditText:EditText = findViewById(R.id.write_moviename)
        searchEditText.addTextChangedListener { searchText ->
            val query = searchText.toString().trim()
            filterFilms(query, films, listOfFilms)
        }
        buttonShowPopular.setOnClickListener{
            text.text = "Популярные"
            listOfFilms.adapter = ItemsAdapter(films,this,favouritesFilms)
        }
        buttonShowFavourites.setOnClickListener{
            text.text = "Избранное"
            listOfFilms.adapter = ItemsAdapter(favouritesFilms,this,films)
        }
    }
    private fun filterFilms(query: String,films: List<ItemFilm>,listOfFilms:RecyclerView ) {
        val filteredFilms = films.filter { it.name.startsWith(query, ignoreCase = true) }
        (listOfFilms.adapter as? ItemsAdapter)?.updateList(filteredFilms)
    }
}


//        buttonShowPopular.setOnClickListener {
//            text.text = "Популярные"
//
//            CoroutineScope(Dispatchers.Main).launch {
//                try {
//                    val response = apiService.getPopularFilms("TOP_100_POPULAR_FILMS")
//
//                    if (response.isSuccessful) {
//                        val movies: List<Movie>? = response.body()
//                        movies?.let {
//                            val mappedFilms = it.map { movie ->
//                                ItemFilm(
//                                    id = movie.kinopoiskId,
//                                    name = movie.nameRu,
//                                    image = movie.posterUrlPreview,
//                                    year = movie.year,
//                                    description = movie.description,
//                                    genre = movie.genres.joinToString(separator = ", ") { it.genre },
//                                    country = movie.countries.joinToString(separator = ", ") { it.country }
//                                )
//                            }
//
//                            films.addAll(mappedFilms)
//                            listOfFilms.adapter?.notifyDataSetChanged()
//                        }
//                    } else {
//                        // Обработка ошибки
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//    }

//data class Movie(
//    @SerializedName("id") val id: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("releaseYear") val releaseYear: Int,
//
//)
//
//data class MovieDetails(
//    @SerializedName("id") val id: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("description") val description: String,
//
//)