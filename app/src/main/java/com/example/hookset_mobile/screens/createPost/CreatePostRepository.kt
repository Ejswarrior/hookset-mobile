package com.example.hookset_mobile.screens.createPost

import android.content.Context
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

class CreatePostRepository(val httpClient: HttpClient, val authService: AuthService,val context: Context): ViewModel() {

    private fun readBytes(context: Context, uri: Uri): ByteArray? {
        var bytes:ByteArray? = null
         context.contentResolver.openInputStream(uri)?.buffered()?.use { bytes = it.readBytes() }
        return bytes
    }
    suspend fun uploadPostImage(fileUrl:Uri) {
        try {
            val bytes = readBytes(context,fileUrl)
            if(bytes !== null) {
                val response = httpClient.submitFormWithBinaryData(
                    url = "https://10.0.2.2:7225/posts/images",
                    formData = formData() {
                        append("imageFile", bytes, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                        })
                    }
                )
                Log.d("imageUpload", response.body<String>().toString())

            }
        }
        catch (e:Exception) {
            Log.e("fileUpload", e.toString())
        }
    }

    suspend fun uploadPost() {

    }
}