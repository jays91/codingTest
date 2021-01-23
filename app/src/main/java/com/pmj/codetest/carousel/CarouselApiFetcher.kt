package com.pmj.codetest.carousel


import io.reactivex.Observable

class CarouselApiFetcher(private var carouselBackend: CarouselBackend) {
    fun getCarousel(): Observable<CarouselResponse> {
        return carouselBackend.carouselApi()
    }
}