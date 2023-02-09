package com.samar.pulsesolutionpreviewinterview.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samar.pulsesolutionpreviewinterview.R

@Composable
fun ScreenAnimation(
    modifier: Modifier = Modifier.fillMaxSize(),
    isAnimatable: Boolean = true,
    resources: Int = R.raw.failure
) {

    if (isAnimatable) {
        Box(modifier = modifier) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resources))
            LottieAnimation(
                composition,
                modifier = modifier,
                contentScale = ContentScale.Inside,
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}