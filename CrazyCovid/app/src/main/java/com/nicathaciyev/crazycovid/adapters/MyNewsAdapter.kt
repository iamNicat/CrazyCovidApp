package com.nicathaciyev.crazycovid.adapters

import android.os.Build
import android.text.Html
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.nicathaciyev.crazycovid.R
import com.nicathaciyev.crazycovid.data.responses.NewsItem
import kotlinx.android.synthetic.main.row_news.view.*

@Suppress("DEPRECATION")
class MyNewsAdapter(val newsItemList: List<NewsItem>) :
    RecyclerView.Adapter<MyNewsAdapter.NewsItemVH>() {


    class NewsItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsTitle: TextView = itemView.findViewById(R.id.tv_news_title)
        var newsBody: TextView = itemView.findViewById(R.id.tv_news_body)

        var newsTime: TextView = itemView.findViewById(R.id.tv_news_time)



        var linearLayout: LinearLayout = itemView.findViewById(R.id.newsLinearLayout)
        var expandableLayout: RelativeLayout =
            itemView.findViewById(R.id.news_expandable_layout)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false)
        return NewsItemVH(view)
    }

    override fun getItemCount(): Int {
        return newsItemList.size
    }

    override fun onBindViewHolder(holder: NewsItemVH, position: Int) {
        val newsItem: NewsItem = newsItemList[position]
        holder.newsTitle.text = newsItem.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.newsBody.setText(Html.fromHtml(newsItem.body,Html.FROM_HTML_MODE_LEGACY))
        } else{
            holder.newsBody.setText(Html.fromHtml(newsItem.body))
        }
        //   = newsItem.body


        holder.newsTime.text = newsItem.datetime

        val isExpandable: Boolean = newsItemList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val newsItem = newsItemList[position]
            newsItem.expandable = !newsItem.expandable
            notifyItemChanged(position)
        }
    }
}