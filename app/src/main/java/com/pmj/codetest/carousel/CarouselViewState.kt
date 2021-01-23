package com.pmj.codetest.carousel


sealed class CarouselViewState {

    object Loading : CarouselViewState()

    data class Success(val response: CarouselResponse) : CarouselViewState()

    data class ErrorWithMessage(val errorMessage: String) : CarouselViewState()

}
