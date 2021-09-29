package com.light.notes.weather.ui.main.week_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.light.notes.weather.databinding.LiWeekBinding
import com.light.notes.weather.util.formatDate
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

import kotlin.collections.ArrayList

class WeekAdapter() :
    RecyclerView.Adapter<WeekAdapter.ViewHolder>() {

    private val list = ArrayList<WeekCellModel>()

    private lateinit var inflater: LayoutInflater

    fun weekData(newData: List<WeekCellModel>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LiWeekBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDate.text =
            SimpleDateFormat.getDateInstance().format(Date((list[position].date).toLong() * 1000L))
        holder.binding.tvDescription.text = list[position].description
        holder.binding.tvTempDay.text = list[position].tempDay.toString()+ "°C"
        holder.binding.tvTempNight.text = list[position].tempNight.toString() + "°C"
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + list[position].image + "@2x.png")
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = list.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        inflater = LayoutInflater.from(recyclerView.context)
    }

    class ViewHolder(val binding: LiWeekBinding) : RecyclerView.ViewHolder(binding.root) {}
}