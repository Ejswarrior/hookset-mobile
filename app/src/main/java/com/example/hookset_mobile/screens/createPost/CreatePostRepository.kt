package com.example.hookset_mobile.screens.createPost

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

class CreatePostRepository(val httpClient: HttpClient, val authService: AuthService): ViewModel() {

//    adding form data to response
    //        val client = HttpClient(Apache) {}
//
//        val chatId = "123"
//
//        client.submitFormWithBinaryData(
//            url = "https://api.telegram.org/bot<token>/sendDocument?chat_id=$chatId",
//            formData = formData {
//                append("document", file.readBytes(), Headers.build {
//                    append(HttpHeaders.ContentDisposition, "filename=${file.name}")
//                })
//            }
//        )
    suspend fun createPost(fileUrl:Uri) {
        val file = File(fileUrl.toString())
        Log.d("file", file.toString())
        val response = httpClient.submitFormWithBinaryData(
                url = "https://10.0.2.2:7225/posts/images",
                formData = formData() {
                    append("document", file.readBytes(), Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                }
        )

        Log.d("imageUpload", response.body<String>().toString())

    }
}