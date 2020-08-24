package com.example.domain.di

import com.example.domain.interactors.*
import com.example.domain.usecases.*
import org.koin.dsl.module

val interactorModule = module {
    factory <PopularMoveInteractor> { PopularMoveUseCase(get()) }
    single <DetailMoveInteractor> { DetailMoveUseCase(get()) }
}
