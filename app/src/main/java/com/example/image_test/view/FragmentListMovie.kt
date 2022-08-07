package com.example.image_test.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.image_test.MovieApplication
import com.example.image_test.R
import com.example.image_test.databinding.MovieListFragmentLayoutBinding
import com.example.image_test.model.MovieResponse
import com.example.image_test.model.Repository
import com.example.image_test.view.adapter.MovieAdapter

private const val TAG = "FragmentListMovie"

class FragmentListMovie: Fragment() {
    private  lateinit var  binding:
            MovieListFragmentLayoutBinding

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MovieListFragmentLayoutBinding.inflate(
            inflater, container, false
        )
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //List<String> some = ArrayList()
        val response = Repository().deSerialize(
            MovieApplication.movieApplication
        )
        updateAdapter(response)
    }

    private fun updateAdapter(response: List<MovieResponse>) {
        Log.d(TAG, "updateAdapter: $response")
        adapter.updateList(response)
    }

    private fun initViews() {
        adapter = MovieAdapter((emptyList())) {
            openDetails(it)
        }
        binding.movieList.adapter = adapter
        binding.movieList.layoutManager = LinearLayoutManager(context)
    }

    private fun openDetails(dataItem: MovieResponse) {
        activity?.openDetails(dataItem)
    }
    private fun FragmentActivity.openDetails(dataItem: MovieResponse){
            supportFragmentManager.findFragmentById(R.id.fragment_details)?.let {
                (it as FragmentDetails).initViews(dataItem)
            } ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentDetails.newInstance(dataItem))
                .addToBackStack(null)
                .commit()

//        val fragmentDetails: FragmentDetails? =
//            supportFragmentManager.findFragmentById(R.id.fragment_details) as FragmentDetails?
//
//        if (fragmentDetails == null)
//            supportFragmentManager.beginTransaction()
//            .replace(R.id.container, FragmentDetails.newInstance(dataItem))
//            .addToBackStack(null)
//            .commit()
//        else
//            fragmentDetails.initViews(dataItem)
    }
}