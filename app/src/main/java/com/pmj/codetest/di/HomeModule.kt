package com.pmj.codetest.di

import com.pmj.codetest.HomeViewModel
import com.pmj.codetest.carousel.CarouselRepository
import com.pmj.codetest.carousel.CarouselApiFetcher
import com.pmj.codetest.carousel.CarouselBackend
import com.pmj.codetest.products.ProductApiFetcher
import com.pmj.codetest.products.ProductBackend
import com.pmj.codetest.products.ProductRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

var homeModule = module {

    single { carouselBackend(get()) }
    single { CarouselApiFetcher(get()) }
    factory { CarouselRepository(get()) }

    single { productBackend(get()) }
    single { ProductApiFetcher(get()) }
    factory { ProductRepository(get()) }

    viewModel {
        HomeViewModel(get(),get())
    }

}

fun carouselBackend(retrofit: Retrofit): CarouselBackend {
    return retrofit.create(CarouselBackend::class.java)
}

fun productBackend(retrofit: Retrofit): ProductBackend {
    return retrofit.create(ProductBackend::class.java)
}
