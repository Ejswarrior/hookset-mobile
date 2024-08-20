package com.example.hookset_mobile

class ApiBuilder {
    var url: String? = null
        private set

    fun url(requestURl: String) {
        this.url = requestURl
    }

    fun addBody(String jsonBody) {

    }
}