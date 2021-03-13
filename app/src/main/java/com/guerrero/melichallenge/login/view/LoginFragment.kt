package com.guerrero.melichallenge.login.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.guerrero.melichallenge.base.APP_ID
import com.guerrero.melichallenge.base.REDIRECT_URI
import com.guerrero.melichallenge.base.SHARED_PREFERENCE_APP_NAME
import com.guerrero.melichallenge.base.SHARED_PREFERENCE_TOKEN_KEY
import com.guerrero.melichallenge.base.ViewModelFactory
import com.guerrero.melichallenge.databinding.FragmentLoginBinding
import com.guerrero.melichallenge.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val WEB_AUTH_URL = "http://auth.mercadolibre.com.CO/authorization?response_type=code&client_id=$APP_ID&redirect_uri=$REDIRECT_URI"

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: LoginViewModel by viewModels { factory }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnLoginListener()
        observerViewState()
    }

    private fun setupBtnLoginListener() {
        binding.btnLogin.setOnClickListener {
            launchWebAuth()
        }
    }

    private fun observerViewState() {
        viewModel.getViewStateObservable().observe(viewLifecycleOwner, { viewState ->
            when (viewState) {
                is LoginViewState.Loading -> showStateLoading()
                is LoginViewState.Normal -> showStateNormal()
                is LoginViewState.WebAuthSuccessful -> onWebAuthSuccessful(viewState.token)
            }
        })
    }

    private fun showStateLoading() {
        binding.run {
            btnLogin.isEnabled = false
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showStateNormal() {
        binding.run {
            btnLogin.isEnabled = true
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun onWebAuthSuccessful(accessToken: String) {
        saveToken(accessToken)
        navigateToProductsFragment()
    }

    private fun saveToken(accessToken: String) {
        with(requireActivity().getSharedPreferences(SHARED_PREFERENCE_APP_NAME, Context.MODE_PRIVATE).edit()) {
            putString(SHARED_PREFERENCE_TOKEN_KEY, accessToken)
            apply()
        }
    }

    private fun navigateToProductsFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToProductsFragment()
        findNavController().navigate(action)
    }

    private fun launchWebAuth() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB_AUTH_URL))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.login(requireActivity().intent.dataString.orEmpty())
    }
}
