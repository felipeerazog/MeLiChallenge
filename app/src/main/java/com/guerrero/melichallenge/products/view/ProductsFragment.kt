package com.guerrero.melichallenge.products.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.guerrero.melichallenge.base.ViewModelFactory
import com.guerrero.melichallenge.databinding.FragmentProductsBinding
import com.guerrero.melichallenge.products.business.Product
import com.guerrero.melichallenge.products.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: ProductsViewModel by viewModels { factory }
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        observeViewState()
        setupSearchListener()
    }

    private fun setupAdapter() {
        binding.rvItems.run {
            adapter = ItemAdapter(listOf())
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun observeViewState() {
        viewModel.getViewStateObservable().observe(viewLifecycleOwner, { viewState ->
            when (viewState) {
                is ProductsViewState.ShowProducts -> showProducts(viewState.products)
            }
        })
    }

    private fun showProducts(products: List<Product>) {
        (binding.rvItems.adapter as ItemAdapter).addProducts(products)
    }

    private fun setupSearchListener() {
        binding.svSearchField.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.getProducts(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?) = true
            }
        )
    }
}
