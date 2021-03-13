package com.guerrero.melichallenge.login.business

import android.net.Uri

class UriHelperImpl : UriHelper {

    override fun getParam(uriString: String, key: String): String {
        return Uri.parse(uriString).getQueryParameter(key) ?: ""
    }
}
