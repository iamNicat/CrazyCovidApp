package com.nicathaciyev.crazycovid.adapters

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicathaciyev.crazycovid.R
import com.nicathaciyev.crazycovid.data.responses.InfoItem

@Suppress("DEPRECATION")
class MyInfoAdapter(val infoItemList : List<InfoItem>) : RecyclerView.Adapter<MyInfoAdapter.InfoItemVH>(){
    class InfoItemVH(itemVIew : View)  : RecyclerView.ViewHolder(itemVIew) {



        var infoTitle : TextView = itemVIew.findViewById(R.id.tv_info_body)
        var infoBody : TextView = itemVIew.findViewById(R.id.tv_info_body)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.infoLinearLayout)
        var expandableLayout: RelativeLayout =
            itemView.findViewById(R.id.info_expandable_layout)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_info, parent, false)
        return MyInfoAdapter.InfoItemVH(view)
    }

    override fun getItemCount(): Int {
     return   infoItemList.size
    }

    override fun onBindViewHolder(holder: InfoItemVH, position: Int) {
       val infoItem  : InfoItem = infoItemList[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.infoTitle.setText(Html.fromHtml(infoItem.title, Html.FROM_HTML_MODE_LEGACY))
        } else{
            holder.infoTitle.setText(Html.fromHtml(infoItem.title))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.infoBody.setText(Html.fromHtml(infoItem.body, Html.FROM_HTML_MODE_LEGACY))
        } else{
            holder.infoBody.setText(Html.fromHtml(infoItem.body))
        }

        val isExpandable: Boolean = infoItemList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val infoItem = infoItemList[position]
            infoItem.expandable = !infoItem.expandable
            notifyItemChanged(position)
        }


    }
}