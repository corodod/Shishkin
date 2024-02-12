package com.example.shishkin2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(
    var items: List<ItemFilm>,
    var context: MainActivity,
    var favouritesFilms: MutableList<ItemFilm>
) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.filmImage)
        val name: TextView = view.findViewById(R.id.filmName)
        val genreAndYear: TextView = view.findViewById(R.id.filmGenreAndYear)
        val starIcon: ImageView = itemView.findViewById(R.id.starIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.film_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.genreAndYear.text = "${item.genre} (${item.year})"

        val imageId = context.resources.getIdentifier(item.image, "drawable", context.packageName)
        holder.image.setImageResource(imageId)

        //цвет звезды в зависимости от флага
        val starColor = if (item.flag) Color.DKGRAY else Color.WHITE
        holder.starIcon.setColorFilter(starColor, PorterDuff.Mode.SRC_IN)


        holder.itemView.setOnLongClickListener {
            // Изменяем флаг на противоположное значение
            item.flag = !item.flag

            // Обновляем цвет звезды
            val newStarColor = if (item.flag) Color.DKGRAY else Color.WHITE
            holder.starIcon.setColorFilter(newStarColor, PorterDuff.Mode.SRC_IN)

            // Добавляем или удаляем фильм из списка
            if (item.flag) {
                favouritesFilms.add(item)
            } else {
                favouritesFilms.remove(item)
            }

            true
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ItemActivity::class.java)

            val selectedFilm = items[position]

            // Передача данных через intent
            intent.putExtra("filmName", selectedFilm.name)
            intent.putExtra("description", selectedFilm.description)
            intent.putExtra("genre", selectedFilm.genre)
            intent.putExtra("country", selectedFilm.country)
            intent.putExtra("imageId", imageId)
            context.startActivity(intent)
        }
    }
    fun updateList(newList: List<ItemFilm>) {
        items = newList
        notifyDataSetChanged()
    }
}

