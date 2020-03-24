package com.rizkirakasiwi.covid19.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.rizkirakasiwi.covid19.R
import com.rizkirakasiwi.covid19.adapter.mainMenuAdapter.IndonesiaTodayAdapter
import com.rizkirakasiwi.covid19.adapter.newsMainAdapter.NewsAdapter
import com.rizkirakasiwi.covid19.apiHelper.NewsHelperApi
import com.rizkirakasiwi.covid19.apiHelper.ConnectionHelper
import com.rizkirakasiwi.covid19.apiHelper.CovidHelperApi
import com.rizkirakasiwi.covid19.data.DataForHomeUi
import com.rizkirakasiwi.covid19.url.CovidUrl
import com.rizkirakasiwi.covid19.url.NewsUrl
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_news_main_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromApi()
    }

    override fun onResume() {
        super.onResume()
        viewModel.dataHome.observe(this, Observer {
            val adapter = GroupAdapter<GroupieViewHolder>()
            adapter.add(IndonesiaTodayAdapter(it.dataCovid))
            adapter.add(NewsAdapter(it.dataNews))
            recycler_home.adapter = adapter
        })
    }
    private fun getDataFromApi()=GlobalScope.launch(Dispatchers.IO){
        if (ConnectionHelper.isOnline(context!!)){
            val dataCovid = CovidHelperApi(context!!).getCovidData(CovidUrl.indoCovidUrl)
            val dataNews = NewsHelperApi(context!!).getNewsData(NewsUrl.indoCovidNewsUrl)
            viewModel.setDataHome(DataForHomeUi(dataCovid[0],dataNews))
        }else{
            Toast.makeText(context!!, "Connection Failed", Toast.LENGTH_LONG).show()
        }
    }


}
