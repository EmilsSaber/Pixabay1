package kg.example.pixabay.remote

import kg.example.pixabay.model.PixModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServise {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "26611749-4299a9149d8e2493033476561",
        @Query("q") keyWorld: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ):Call<PixModel>
}