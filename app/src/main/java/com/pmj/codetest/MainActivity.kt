package com.pmj.codetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.pmj.codetest.carousel.CarouselAdapter
import com.pmj.codetest.carousel.CarouselViewState
import com.pmj.codetest.products.ProductAdapter
import com.pmj.codetest.products.ProductViewState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_carousel.view.*
import kotlinx.android.synthetic.main.item_collection.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val carouselAdapter = CarouselAdapter()
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Glide.with(CodeTestApplication.instance).load("https://tinyurl.com/y2gersqn").into(ivBanner)

        homeViewModel.getCarouselImages()
        homeViewModel.getProducts()

        with(homeViewModel) {
            carouselViewState.observe(
                this@MainActivity,
                { viewState ->
                    handleCarouselViewState(viewState)
                })
            productViewState.observe(
                this@MainActivity,
                { viewState ->
                    handleProductViewState(viewState)
                })
        }

        viewPagerCarousel.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPagerCarousel.adapter = carouselAdapter
        TabLayoutMediator(tabIndicatorCarousel, viewPagerCarousel) { _, _ ->
            //Some implementation
        }.attach()

        rvProducts.layoutManager = GridLayoutManager(this, 2)
        rvProducts.adapter = productAdapter
    }

    private fun handleCarouselViewState(viewState: CarouselViewState) {
        when (viewState) {
            is CarouselViewState.Loading -> {
                //show progress dialog
            }
            is CarouselViewState.Success -> {
                if (viewState.response.carousel.isNullOrEmpty()) {
                    Toast.makeText(
                        this,
                        "Something went wrong. Please try later.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    carouselAdapter.setItem(viewState.response.carousel)
                }
            }
            is CarouselViewState.ErrorWithMessage -> {
                Toast.makeText(this, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleProductViewState(viewState: ProductViewState) {
        when (viewState) {
            is ProductViewState.Loading -> {
                //show progress dialog
            }
            is ProductViewState.Success -> {
                if (viewState.response.products.isNullOrEmpty()) {
                    Toast.makeText(
                        this,
                        "Something went wrong. Please try later.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    productAdapter.setItem(viewState.response.products)
                }
            }
            is ProductViewState.ErrorWithMessage -> {
                Toast.makeText(this, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}