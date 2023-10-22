package com.example.movie.tabtv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.adapter.TvAdapter
import com.example.core.vo.Resource
import com.example.movie.R
import com.example.movie.databinding.FragmentTvBinding
import com.example.movie.detail.DetailTvActivity
import com.example.movie.detail.DetailTvActivity.Companion.TV_SH0WS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding

    private val tvShowsViewModel: TvViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val tvShowsAdapter = TvAdapter()
            tvShowsAdapter.onItemClick = {
                val intent = Intent(activity, DetailTvActivity::class.java)
                intent.putExtra(TV_SH0WS, it)
                startActivity(intent)
            }

            tvShowsViewModel.tvShows.observe(viewLifecycleOwner) { listTvShows ->
                if (listTvShows != null) {
                    when (listTvShows) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            tvShowsAdapter.setData(listTvShows.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                context,
                                "Kesalahan Ketika Memuat Data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            with(binding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressTvShows.visibility = View.VISIBLE
        } else{
            binding.progressTvShows.visibility = View.GONE
        }
    }

}