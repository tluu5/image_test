package com.example.image_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.image_test.R
import com.example.image_test.databinding.MovieDetailsFragmentLayoutBinding
import com.example.image_test.model.MovieResponse
import com.squareup.picasso.Picasso

class FragmentDetails: Fragment() {
    companion object{
        const val MOVIE_RESPONSE_EXTRA = "MOVIE_RESPONSE_EXTRA"
        // Factory Fragment Function
        fun newInstance(dataItem: MovieResponse): FragmentDetails{
            val fragment = FragmentDetails()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_RESPONSE_EXTRA, dataItem)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: MovieDetailsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super .onCreateView(inflater, container, savedInstanceState)
        binding = MovieDetailsFragmentLayoutBinding.inflate(inflater, container, false)
        arguments?.getParcelable<MovieResponse>(MOVIE_RESPONSE_EXTRA)?.let {
           initViews(it)
        }
        return binding.root
    }

    fun initViews(it: MovieResponse) {
        binding.tvMovieGenreDetails.text = getString(R.string.movie_detail_genre,
            it.genre.toString())
        binding.tvMovieRatingDetails.text = getString(R.string.movie_detail_rating,
            it.rating)
        binding.tvMovieReleaseDetails.text = getString(R.string.movie_detail_release,
            it.releaseYear)
        binding.tvMovieTitleDetails.text = it.title
        Picasso.get().load(it.image).into(binding.ivMoviePosterDetails)
    }
}