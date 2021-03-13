package com.guerrero.melichallenge.login.business

import android.net.Uri

interface UriHelper {

    fun getParam(uriString: String, key: String): String
}
