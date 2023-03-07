package com.curiel_ruelas.republicserviceschallenge.ui.routes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import com.curiel_ruelas.republicserviceschallenge.databinding.RowRoutesBinding

class RoutesAdapter(private val info: List<Route>) :
    RecyclerView.Adapter<RoutesAdapter.RouteHolder>() {


    inner class RouteHolder(private val binding: RowRoutesBinding) : ViewHolder(binding.root) {
        fun bind(info: Route) {
            binding.theRoute = info
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteHolder {
        val binding = RowRoutesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RouteHolder(binding)
    }

    override fun getItemCount() = info.size

    override fun onBindViewHolder(holder: RouteHolder, position: Int) {
        holder.bind(info[position])
    }
}