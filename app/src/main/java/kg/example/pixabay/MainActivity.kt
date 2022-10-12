package kg.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kg.example.pixabay.adapter.ImageAdapter
import kg.example.pixabay.app.App
import kg.example.pixabay.databinding.ActivityMainBinding
import kg.example.pixabay.model.PixModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var imageAdapter = ImageAdapter(mutableListOf())
    var page = 1
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {

        binding.changePage.setOnClickListener{
            doRequest()
            ++page
        }
        binding.btnClick.setOnClickListener {
            doRequest()
        }
    }

    private fun doRequest() {
        App.api.getImages(keyWorld = binding.search.text.toString(), page = page).enqueue(object:Callback<PixModel>{
            override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                if (response.isSuccessful) {
                    Log.e("ololo", "onResponse: ${response.body()?.hits!![0].largeImageURL}")
                    imageAdapter = ImageAdapter(response.body()?.hits!!)
                    binding.recycler.adapter = imageAdapter
                }
            }

            override fun onFailure(call: Call<PixModel>, t: Throwable) {
                Log.e("ololo", "onLohhhhhh:")
            }
        })
    }
}