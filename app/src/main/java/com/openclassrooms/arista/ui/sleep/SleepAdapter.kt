package com.openclassrooms.arista.ui.sleep

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Sleep
import java.time.format.DateTimeFormatter

class SleepAdapter(private var sleeps: List<Sleep>) :
    RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sleep, parent, false)
        return SleepViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val sleep = sleeps[position]
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        holder.tvStartTime.text =
            String.format("Start Time: %s", sleep.startTime.format(formatter))
        holder.tvDuration.text = "Duration: ${sleep.duration} minutes"
        holder.tvQuality.text = "Quality: ${sleep.quality}"
    }

    override fun getItemCount() = sleeps.size

    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStartTime: TextView
        var tvDuration: TextView
        var tvQuality: TextView
        var ivDelete: ImageView

        init {
            tvStartTime = itemView.findViewById(R.id.tv_start_time)
            tvDuration = itemView.findViewById(R.id.tv_duration)
            tvQuality = itemView.findViewById(R.id.tv_quality)
            ivDelete = itemView.findViewById(R.id.delete)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newSleeps: List<Sleep>) {
        this.sleeps = newSleeps
        notifyDataSetChanged()
    }
}
