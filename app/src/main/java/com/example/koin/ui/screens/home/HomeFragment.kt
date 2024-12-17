package com.example.koin.ui.screens.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koin.ui.base.BaseFragment
import com.example.koin.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class.java) {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun setupViews() {
        val bannerImageView = view?.findViewById<ImageView>(R.id.bannerImageView)
        val productRecyclerView = view?.findViewById<RecyclerView>(R.id.productRecyclerView)
        val profileButton = view?.findViewById<Button>(R.id.profileButton)

        productRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        profileButton?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_profile)
        }
    }

    override fun setupObservers() {
        homeViewModel.homeData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                response.data?.let { data ->
                    val bannerImageView = view?.findViewById<ImageView>(R.id.bannerImageView)
                    val firstBanner = data.banners.firstOrNull()?.image
                    if (bannerImageView != null) {
                        Glide.with(this).load(firstBanner).into(bannerImageView)
                    }

                    val productRecyclerView = view?.findViewById<RecyclerView>(R.id.productRecyclerView)
                    productRecyclerView?.adapter = ProductAdapter(data.products)
                }
            }
        })

        homeViewModel.fetchHomeData()
    }
}
