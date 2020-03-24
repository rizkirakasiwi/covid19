package com.rizkirakasiwi.covid19.adapter.mainMenuAdapter

import android.os.Build
import androidx.annotation.RequiresApi
import com.rizkirakasiwi.covid19.R

import com.rizkirakasiwi.covid19.adapter.ChartAdapter
import com.rizkirakasiwi.covid19.data.covid.confirmed.DataConfirmedCovidItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.pinned_country_layout.view.*

class IndonesiaTodayAdapter(private val dataConfirmedCovid: DataConfirmedCovidItem):Item<GroupieViewHolder>() {
    private val yData = arrayOf(392f, 20f, 38f)
    private val xData = arrayOf("Active", "Recovered","Deaths")

    override fun getLayout(): Int = R.layout.pinned_country_layout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.txt_home_deathCount.text = dataConfirmedCovid.deaths.toString()
        view.txt_home_recoveredCount.text = dataConfirmedCovid.recovered.toString()
        view.txt_home_unrecoveryCount.text = dataConfirmedCovid.active.toString()
        view.txt_home_countryToday.text = view.context.getString(R.string.country_today, "Indonesia")
        ChartAdapter(view.context,yData, xData).pieChart(view.chart_homeFragment, dataConfirmedCovid.confirmed.toString())
    }
}