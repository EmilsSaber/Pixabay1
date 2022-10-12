package kg.example.pixabay.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

   private val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/").
            addConverterFactory(GsonConverterFactory.create()).build()



    fun getApi() = retrofit.create(ApiServise::class.java)
}