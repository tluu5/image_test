package com.example.image_test.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.image_test.R
import com.example.image_test.databinding.MovieItemLayoutBinding
import com.example.image_test.model.MovieResponse
import com.squareup.picasso.Picasso

private const val TAG = "MovieAdapter"

class MovieAdapter(private  var dataSet: List<MovieResponse>, private val openDetails: (MovieResponse) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(private  val binding: MovieItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(dataItem: MovieResponse, openDetails: (MovieResponse) -> Unit){
            Log.d(TAG, "onBind: ${dataItem.image}")
            Picasso.get().load(dataItem.image)
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.ivPosterItem)

            binding.tvTitleItem.text = dataItem.title
            binding.root.setOnClickListener {
                openDetails(dataItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                MovieItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(response: List<MovieResponse>) {
        dataSet = response
        notifyDataSetChanged()
    }
}