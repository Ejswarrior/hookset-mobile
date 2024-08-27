package com.example.hookset_mobile

class ApiBuilder private constructor() {
    private val httpService: HttpService = HttpService.instance
    companion object {
        val instance:ApiBuilder by lazy {
            ApiBuilder()
        }

    }






}