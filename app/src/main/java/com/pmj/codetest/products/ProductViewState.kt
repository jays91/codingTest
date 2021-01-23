package com.pmj.codetest.products

sealed class ProductViewState {

    object Loading : ProductViewState()

    data class Success(val response: ProductResponse) : ProductViewState()

    data class ErrorWithMessage(val errorMessage: String) : ProductViewState()

}
