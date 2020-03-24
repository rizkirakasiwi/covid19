package com.rizkirakasiwi.covid19.adapter.newsMainAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.data.news.DataNews
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_news_main_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapterChild (private val dataNews: DataNews) : RecyclerView.Adapter<NewsAdapterChild.MyViewHolder>(){
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_news_main_item, parent, false)
        val myViewHolder =
            MyViewHolder(
                view
            )
        return myViewHolder
    }


    override fun getItemCount(): Int = if (dataNews.totalResults > 10) 10 else dataNews.totalResults


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.img_home_news_item_banner.load(dataNews.articles[position].urlToImage)
        view.txt_home_news_main_item_title.text = dataNews.articles[position].title
        view.txt_home_news_item_resume.text = dataNews.articles[position].description
        view.txt_home_news_item_siteAndTime.text = dataNews.articles[position].source.name
        Log.i("NewsAdapterChild", updateNewsTime(dataNews.articles[position].author))
    }


    private fun updateNewsTime(time:String):String{
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale("id","ID", "ID"))
        return simpleDateFormat.format(Date())
    }

    private fun ImageView.load(url:String)= GlobalScope.launch(Dispatchers.Main){
        Picasso.get().load(url).into(this@load)
    }
}