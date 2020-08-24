package com.example.simplemovieapp.di

import com.example.simplemovieapp.fragments.moviedetail.viewmodel.DetailMoveViewModel
import com.example.simplemovieapp.fragments.popular.viewmodel.PopularMoveViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PopularMoveViewModel(get()) }
    viewModel { DetailMoveViewModel(get()) }
}
