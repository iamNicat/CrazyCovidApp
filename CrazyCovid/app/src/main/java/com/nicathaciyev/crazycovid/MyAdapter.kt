package com.nicathaciyev.crazycovid

import android.content.Context
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicathaciyev.crazycovid.data.responses.CountryItem
import kotlinx.android.synthetic.main.row_list.view.*

class MyAdapter(val list: List<CountryItem>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(countryItem: CountryItem) {
            itemView.tv_global_country.text = countryItem.country
            itemView.tv_global_cases.text = countryItem.totalCases
            itemView.tv_global_new_cases.text = countryItem.todayCases
            itemView.tv_global_deaths.text = countryItem.totalCases
            itemView.tv_global_new_deaths.text = countryItem.todayDeaths
            itemView.tv_global_recovered.text = countryItem.totalRecovered
            itemView.tv_global_critical.text = countryItem.critical
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])

    }
}