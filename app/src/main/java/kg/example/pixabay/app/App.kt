package kg.example.pixabay.app

import android.app.Application
import kg.example.pixabay.remote.ApiServise
import kg.example.pixabay.remote.RetrofitClient

class App: Application() {

    companion object{
        lateinit var api: ApiServise
    }

    override fun onCreate() {
        super.onCreate()
        api = RetrofitClient().getApi()
    }
}