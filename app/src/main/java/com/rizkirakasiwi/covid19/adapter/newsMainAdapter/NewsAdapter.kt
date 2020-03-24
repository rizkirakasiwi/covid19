package com.rizkirakasiwi.covid19.adapter.newsMainAdapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.data.news.DataNews
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.home_news_main_layout.view.*

class NewsAdapter (private val dataNews: DataNews):Item<GroupieViewHolder>(){
    override fun getLayout(): Int = R.layout.home_news_main_layout

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view =  viewHolder.itemView
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
        view.recycler_news_main_layout.layoutManager = layoutManager
        view.recycler_news_main_layout.adapter =
            NewsAdapterChild(
                dataNews
            )
    }
}