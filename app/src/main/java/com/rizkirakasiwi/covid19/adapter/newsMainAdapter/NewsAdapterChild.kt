package com.rizkirakasiwi.covid19.adapter.newsMainAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.data.news.DataNews
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_news_main_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

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


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.img_home_news_item_banner.load(dataNews.articles[position].urlToImage)
        view.txt_home_news_main_item_title.text = dataNews.articles[position].title
        view.txt_home_news_item_resume.text = dataNews.articles[position].description
        val time = updateNewsTime(dataNews.articles[position].publishedAt, view.context)
        view.txt_home_news_item_siteAndTime.text = "${dataNews.articles[position].source.name} - " + time
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateNewsTime(time:String, context: Context):String{
        val dateFormat = "yyyy-MM-dd HH:mm:ss"
        val getDate = time.substring(0,10)
        val getTime = time.substring(11,19)
        val newDate = "$getDate $getTime"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        val date = simpleDateFormat.parse(newDate)
        val dateNow = simpleDateFormat.format(Date())
        val now = simpleDateFormat.parse(dateNow)
        val diff = Duration.between(date.toInstant(), now.toInstant())
        return when {
            diff.toHours()>24 -> newDate
            diff.toMinutes() > 60 -> context.getString(R.string.hours_and_minutes, diff.toHours().toString())
            else -> context.getString(R.string.minutes, diff.toMinutes().toString())
        }
    }

    private fun ImageView.load(url:String)= GlobalScope.launch(Dispatchers.Main){
        Picasso.get().load(url).centerCrop().fit().into(this@load)
    }
}