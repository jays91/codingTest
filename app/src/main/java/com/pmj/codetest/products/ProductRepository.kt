package com.pmj.codetest.products


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function


class ProductRepository(private var apiFetcher: ProductApiFetcher) {

    fun getProductImages(): Observable<ProductViewState> {
        return apiFetcher.getProducts()
            .map(ProductViewStateConverter())
            .startWith(ProductViewState.Loading)
            .compose(ProductViewStateErrorConverter())

    }

    /**
     * Converter for success state
     * */
    class ProductViewStateConverter :
        Function<ProductResponse?, ProductViewState> {
        override fun apply(response: ProductResponse): ProductViewState {
            return ProductViewState.Success(response)
        }
    }

    /**
     * Converter for error state
     * */
    class ProductViewStateErrorConverter :
        ObservableTransformer<ProductViewState, ProductViewState> {
        override fun apply(upstream: Observable<ProductViewState>): ObservableSource<ProductViewState> {
            return upstream.onErrorResumeNext(Function<Throwable, ObservableSource<ProductViewState>> {
                Observable.just(ProductViewState.ErrorWithMessage("An error occurred. Please try again."))
            })
        }
    }
}