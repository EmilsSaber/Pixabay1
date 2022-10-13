package kg.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kg.example.pixabay.adapter.ImageAdapter
import kg.example.pixabay.app.App
import kg.example.pixabay.databinding.ActivityMainBinding
import kg.example.pixabay.model.ImageModel
import kg.example.pixabay.model.PixModel
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var imageAdapter = ImageAdapter(arrayListOf())
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

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    doRequest()
                    ++page

                }
            }
        })

    }

    private fun doRequest() {
        App.api.getImages(keyWorld = binding.search.text.toString(), page = page).enqueue(object:Callback<PixModel>{
            override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                if (response.isSuccessful) {
                    Log.e("ololo", "onResponse: ${response.body()?.hits!![0].largeImageURL}")
                    val list  = arrayListOf<ImageModel>()
                    response.body()?.hits?.forEach{
                        imageAdapter.addImage(it)
                    }
                    binding.recycler.adapter = imageAdapter
                }
            }

            override fun onFailure(call: Call<PixModel>, t: Throwable) {
                Log.e("ololo", "onLohhhhhh:")
            }
        })
    }
}