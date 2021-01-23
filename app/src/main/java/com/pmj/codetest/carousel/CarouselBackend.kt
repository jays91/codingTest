package com.pmj.codetest.carousel


import com.pmj.codetest.ApiUrl
import io.reactivex.Observable
import retrofit2.http.GET

interface CarouselBackend {

    @GET(ApiUrl.CAROUSEL_URL)
    fun carouselApi(): Observable<CarouselResponse>
}