package com.rizkirakasiwi.covid19.adapter

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.data.covid.DataCovid

@RequiresApi(Build.VERSION_CODES.M)
class ChartAdapter(private val context:Context, private val yData: Array<Float>, private val xData: Array<String>) {

    fun pieChart(chart:PieChart, dataCovid: DataCovid):PieChart{
        chart.holeRadius = 60f
        chart.setCenterTextColor(context.getColor(R.color.white))
        chart.setHoleColor(context.getColor(R.color.colorPrimary))
        chart.isRotationEnabled = true
        chart.setDrawEntryLabels(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.centerText = context.getString(R.string.cases_reported, dataCovid.confirmed.toString())
        chart.setCenterTextSize(13f)
        val pieData = pieData()
        pieData.setValueFormatter(PercentFormatter(chart))
        chart.setUsePercentValues(true)
        chart.data = pieData
        chart.invalidate()
        return chart
    }

    private fun pieData():PieData{
        val yEntry = entryData()
        val pieDataSet = pieDataSet(yEntry)
        return PieData(pieDataSet)

    }

    private fun entryData():ArrayList<PieEntry>{
        val yEntry  = ArrayList<PieEntry>()
        for (it in yData.indices){
            yEntry.add(PieEntry(yData[it], xData[it]))
        }
        return yEntry
    }


    private fun pieDataSet(yEntry: ArrayList<PieEntry>):PieDataSet{
        val pieDataSet = PieDataSet(yEntry,"")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize =12f
        pieDataSet.valueTextColor = context.getColor(R.color.colorPrimary)
        val colors = arrayListOf(context.getColor(R.color.yellow), context.getColor(R.color.green), context.getColor(R.color.red))
        pieDataSet.colors = colors
        return pieDataSet
    }



}