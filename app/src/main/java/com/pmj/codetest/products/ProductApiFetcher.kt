package com.pmj.codetest.products


import io.reactivex.Observable

class ProductApiFetcher(private var backend: ProductBackend) {
    fun getProducts(): Observable<ProductResponse> {
        return backend.productsApi()
    }
}