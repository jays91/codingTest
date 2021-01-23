package com.pmj.codetest.carousel


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function


class CarouselRepository(private var apiFetcher: CarouselApiFetcher) {

    fun getCarouselImages(): Observable<CarouselViewState> {
        return apiFetcher.getCarousel()
            .map(CarouselViewStateConverter())
            .startWith(CarouselViewState.Loading)
            .compose(CarouselViewStateErrorConverter())

    }

    /**
     * Converter for success state
     * */
    class CarouselViewStateConverter :
        Function<CarouselResponse?, CarouselViewState> {
        override fun apply(response: CarouselResponse): CarouselViewState {
            return CarouselViewState.Success(response)
        }
    }

    /**
     * Converter for error state
     * */
    class CarouselViewStateErrorConverter :
        ObservableTransformer<CarouselViewState, CarouselViewState> {
        override fun apply(upstream: Observable<CarouselViewState>): ObservableSource<CarouselViewState> {
            return upstream.onErrorResumeNext(Function<Throwable, ObservableSource<CarouselViewState>> {
                Observable.just(CarouselViewState.ErrorWithMessage("An error occurred. Please try again."))
            })
        }
    }
}