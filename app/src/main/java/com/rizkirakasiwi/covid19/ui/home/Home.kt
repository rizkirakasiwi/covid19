package com.rizkirakasiwi.covid19.ui.home

import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rizkirakasiwi.covid19.BuildConfig

import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.adapter.mainMenuAdapter.IndonesiaTodayAdapter
import com.rizkirakasiwi.covid19.adapter.newsMainAdapter.NewsAdapter
import com.rizkirakasiwi.covid19.api.ApiHelper
import com.rizkirakasiwi.covid19.connection.ConnectionHelper
import com.rizkirakasiwi.covid19.data.DataForHomeUi
import com.rizkirakasiwi.covid19.data.covid.DataDetailCovid
import com.rizkirakasiwi.covid19.data.news.DataNews
import com.rizkirakasiwi.covid19.url.CovidUrl
import com.rizkirakasiwi.covid19.url.NewsUrl
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private val TAG = "Home"
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        adapter = GroupAdapter<GroupieViewHolder>()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading_home.startAnim()
        loadDataWhenConnected()
        swipeForLoadData()
    }

    private fun swipeForLoadData(){
        swipe_home.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        swipe_home.setColorSchemeColors(Color.WHITE)
        swipe_home.setOnRefreshListener {
            adapter.clear()
            loadDataWhenConnected()
        }
    }

    private fun loadDataWhenConnected(){
        if (ConnectionHelper.isOnline(context!!)) {
            recycler_home.visibility = View.VISIBLE
            linear_home_error.visibility = View.GONE

            GlobalScope.launch(Dispatchers.IO) {
                val covidData = dataCovid()
                val newsData = dataNews()
                viewModel.setDataHome(DataForHomeUi(covidData, newsData))
            }

        }else{
            recycler_home.visibility = View.GONE
            linear_home_error.visibility = View.VISIBLE
            swipe_home.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.dataHome.observe(this, Observer {
            if (!it.dataDetailCovid.isNullOrEmpty()) {
                swipe_home.isRefreshing = false
                loading_home.stopAnim()
                loading_home.visibility = View.GONE
                recycler_home.visibility = View.VISIBLE
            }

            adapter.add(IndonesiaTodayAdapter(it.dataDetailCovid[0]))
            adapter.add(NewsAdapter(it.dataNews))
            recycler_home.adapter = adapter
        })
    }

    private fun dataCovid(): DataDetailCovid {
        val json = ApiHelper.getJsonFromUrl(CovidUrl.indoCovidUrl)
        return Gson().fromJson(json, DataDetailCovid::class.java)
    }

    private fun dataNews():DataNews{
        val json = ApiHelper.getJsonFromUrl(NewsUrl.indoCovidNewsUrl, BuildConfig.NEWS_API)
        return Gson().fromJson(json, DataNews::class.java)
    }

    private fun showToast(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }




}
