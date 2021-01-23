package com.pmj.codetest


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmj.codetest.carousel.CarouselRepository
import com.pmj.codetest.carousel.CarouselViewState
import com.pmj.codetest.products.ProductRepository
import com.pmj.codetest.products.ProductViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private var carouselRepository: CarouselRepository,
    private var productRepository: ProductRepository
) : ViewModel() {

    private var disposable = Disposables.empty()
    var carouselViewState = MutableLiveData<CarouselViewState>()
    var productViewState = MutableLiveData<ProductViewState>()

    fun getCarouselImages() {
        disposable =
            carouselRepository.getCarouselImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> carouselViewState.postValue(response) },
                    { error -> println("${error.message}") })

    }

    fun getProducts() {
        disposable =
            productRepository.getProductImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> productViewState.postValue(response) },
                    { error -> println("${error.message}") })

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}