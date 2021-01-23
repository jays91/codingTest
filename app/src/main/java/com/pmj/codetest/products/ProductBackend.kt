package com.pmj.codetest.products


import com.pmj.codetest.ApiUrl
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductBackend {

    @GET(ApiUrl.PRODUCTS_URL)
    fun productsApi(): Observable<ProductResponse>
}