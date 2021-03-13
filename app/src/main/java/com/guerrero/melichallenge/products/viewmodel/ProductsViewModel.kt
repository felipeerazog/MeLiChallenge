package com.guerrero.melichallenge.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.melichallenge.products.business.GetProductsUseCase
import com.guerrero.melichallenge.products.business.Product
import com.guerrero.melichallenge.products.view.ProductsViewState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val viewState = MutableLiveData<ProductsViewState>().apply { value = ProductsViewState.Normal() }
    fun getViewStateObservable(): LiveData<ProductsViewState> = viewState

    fun getProducts(query: String) {
        viewState.value = ProductsViewState.Loading()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getProductsUseCase(query)
            }
            viewState.value = ProductsViewState.ShowProducts(result)
        }
    }
}
