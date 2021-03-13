package com.guerrero.melichallenge.base.di

import androidx.lifecycle.ViewModel
import com.guerrero.melichallenge.login.viewmodel.LoginViewModel
import com.guerrero.melichallenge.products.viewmodel.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap


@Module
@InstallIn(FragmentComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    abstract fun bindProductsViewModel(productsViewModel: ProductsViewModel): ViewModel
}
