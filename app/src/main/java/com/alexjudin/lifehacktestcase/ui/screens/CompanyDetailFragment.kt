package com.alexjudin.lifehacktestcase.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.alexjudin.lifehacktestcase.R
import com.alexjudin.lifehacktestcase.presentation.viewmodel.CompanyViewModel
import com.alexjudin.lifehacktestcase.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_company_detail.*


class CompanyDetailFragment : Fragment(R.layout.fragment_company_detail) {

    private lateinit var viewModel: CompanyViewModel
    private val args: CompanyDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        val id = args.id

        viewModel.getDetailData(id)

        viewModel.detailCompany.observe(viewLifecycleOwner) { resource ->

            resource.data?.let { request ->

                description.text = request[0].description
                Log.d("TOG", request[0].description)
                img.text = request[0].img
                lat.text = request[0].lat.toString()
                lon.text = request[0].lon.toString()
                name.text = request[0].name
                phone.text = request[0].phone
                www.text = request[0].www
            }
        }
    }

}