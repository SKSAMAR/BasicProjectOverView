package com.samar.pulsesolutionpreviewinterview.presentation.productDetails.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.samar.pulsesolutionpreviewinterview.R
import com.samar.pulsesolutionpreviewinterview.common.ScreenAnimation
import com.samar.pulsesolutionpreviewinterview.data.dto.ImagesItem
import com.samar.pulsesolutionpreviewinterview.util.Constant
import com.samar.pulsesolutionpreviewinterview.util.sdp


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomPager(
    modifier: Modifier = Modifier,
    list: ArrayList<ImagesItem?>?
) {

    val pageState = rememberPagerState()
    Column(
        modifier = modifier
    ) {
        if (!list.isNullOrEmpty()) {
            HorizontalPager(
                count = list.size,
                state = pageState,
                modifier = Modifier.fillMaxSize().weight(1f),
            ) { items ->
                SubcomposeAsyncImage(
                    modifier = modifier,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ScreenAnimation(resources = R.raw.loading)
                        }
                    },
                    error = {
                        ScreenAnimation(resources = R.raw.failure)
                    },
                    model = list[items]?.imageURL ?: Constant.NO_IMAGE,
                    contentDescription = "product_image"
                )

            }
            HorizontalPagerIndicator(
                pagerState = pageState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.sdp)
            )
        } else {
            SubcomposeAsyncImage(
                modifier = modifier,
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ScreenAnimation(resources = R.raw.loading)
                    }
                },
                error = {
                    ScreenAnimation(resources = R.raw.failure)
                },
                model = Constant.NO_IMAGE,
                contentDescription = "product_image"
            )
        }
    }
}
