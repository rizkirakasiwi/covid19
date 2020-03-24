package com.rizkirakasiwi.covid19.ui.detailIndoCovid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rizkirakasiwi.covid19.R

class DetailIndoCovid : Fragment() {

    companion object {
        fun newInstance() =
            DetailIndoCovid()
    }

    private lateinit var viewModel: DetailIndoCovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_indo_covid_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailIndoCovidViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
