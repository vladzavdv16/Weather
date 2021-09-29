package com.light.notes.weather.ui.main.hours_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.light.notes.weather.databinding.LiHoursBinding
import com.light.notes.weather.util.HOUR_DOUBLE_DOT_MINUTE
import com.light.notes.weather.util.toDateFormatOf
import com.squareup.picasso.Picasso
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HoursAdapter :
    RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

//    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Hours>() {
//        override fun areItemsTheSame(oldItem: Hours, newItem: Hours): Boolean {
//            return oldItem.dt_txt == newItem.dt_txt
//        }
//
//        override fun areContentsTheSame(oldItem: Hours, newItem: Hours): Boolean {
//            return Objects.equals(oldItem, newItem)
//        }
//    }

    fun hoursData(newData: List<HoursCellModel>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

    private val dataList = ArrayList<HoursCellModel>()

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LiHoursBinding.inflate(inflater, parent, false));
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cellModel = dataList[position])

    }

    override fun getItemCount(): Int = dataList.size


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        inflater = LayoutInflater.from(recyclerView.context)
    }

    class ViewHolder(private val binding: LiHoursBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cellModel: HoursCellModel) {
            binding.time.text = cellModel.time.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)
            binding.temp.text = cellModel.temp.toString() + "°C"
            Picasso.get()
                .load("https://openweathermap.org/img/wn/" + cellModel.image + "@2x.png")
                .into(binding.image)
        }


    }
}