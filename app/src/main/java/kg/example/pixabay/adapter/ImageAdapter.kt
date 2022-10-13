package kg.example.pixabay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kg.example.pixabay.databinding.ItemPixabayBinding
import kg.example.pixabay.model.ImageModel

class ImageAdapter(private val list: ArrayList<ImageModel>):RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemPixabayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }
    fun addImage(imageModel: ImageModel){
        list.add(imageModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ImageViewHolder(private val binding: ItemPixabayBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)
        }
    }
}