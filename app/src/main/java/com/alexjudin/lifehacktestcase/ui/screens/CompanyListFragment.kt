package com.alexjudin.lifehacktestcase.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.observe
import com.alexjudin.lifehacktestcase.R
import com.alexjudin.lifehacktestcase.data.network.Resource
import com.alexjudin.lifehacktestcase.presentation.viewmodel.CompanyViewModel
import com.alexjudin.lifehacktestcase.ui.activity.MainActivity
import com.alexjudin.lifehacktestcase.ui.adapter.CompanyAdapter
import kotlinx.android.synthetic.main.fragment_company_list.*
import kotlinx.android.synthetic.main.item_error.*


class CompanyListFragment : Fragment(R.layout.fragment_company_list) {

    private lateinit var viewModel: CompanyViewModel
    private lateinit var companyAdapter: CompanyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        bindRV()
        viewModel.company.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    resource.data?.let { request ->
                        companyAdapter.differ.submitList(request.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    resource.message?.let {
                        if (it != "")
                            showErrorMessage(it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
        btnRetry.setOnClickListener {
            viewModel.getData()
        }
        companyAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("id", it.id.toInt())
            }
            findNavController().navigate(

                R.id.action_companyListFragment_to_companyDetailFragment,
                bundle
            )
        }

        viewModel.getData()


    }

    private fun bindRV() {
        companyAdapter = CompanyAdapter()
        rvBreakingNews.apply {
            adapter = companyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        loading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        loading = true
    }

    private fun hideErrorMessage() {
        itemErrorMessage.visibility = View.INVISIBLE
        error = false
    }

    private fun showErrorMessage(message: String) {
        itemErrorMessage.visibility = View.VISIBLE
        tvErrorMessage.text = message
        this.error = true
    }

    private var loading = false
    private var error = false
}


