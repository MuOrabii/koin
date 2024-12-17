package com.example.koin.ui.screens.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.koin.R
import com.example.koin.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileViewModel>(ProfileViewModel::class.java) {
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun setupViews() {}

    override fun setupObservers() {
        profileViewModel.profileData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                response.data?.let { data ->
                    val profileImage = view?.findViewById<ImageView>(R.id.profileImage)
                    val profileName = view?.findViewById<TextView>(R.id.profileName)
                    val profileEmail = view?.findViewById<TextView>(R.id.profileEmail)
                    val profilePhone = view?.findViewById<TextView>(R.id.profilePhone)

                    if (profileImage != null) {
                        Glide.with(this).load(data.image).into(profileImage)
                    }
                    profileName?.text = data.name
                    profileEmail?.text = data.email
                    profilePhone?.text = data.phone
                }
            }
        })

        profileViewModel.fetchProfile()
    }
}
