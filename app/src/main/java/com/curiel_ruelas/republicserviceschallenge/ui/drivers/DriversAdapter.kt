package com.curiel_ruelas.republicserviceschallenge.ui.drivers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.databinding.RowDriversBinding

class DriversAdapter(private val interactor: DriverInterface) :
    RecyclerView.Adapter<DriversAdapter.DriverHolder>() {

    var info: List<Driver> = emptyList()
        set(newInfo) {
            field = newInfo
            notifyDataSetChanged()
        }

    var sorted = false

    inner class DriverHolder(private val binding: RowDriversBinding) : ViewHolder(binding.root) {

        fun bind(info: Driver) {
            binding.driver = info
            binding.root.setOnClickListener {
                interactor.onDriverSelected(driver = info)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverHolder {
        val binding = RowDriversBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriverHolder(binding)
    }

    override fun getItemCount() = info.size

    override fun onBindViewHolder(holder: DriverHolder, position: Int) {
        holder.bind(info[position])
    }

    fun isSorted(): Boolean {
        sorted = !sorted
        return !sorted
    }

    interface DriverInterface {
        fun onDriverSelected(driver: Driver)
    }
}