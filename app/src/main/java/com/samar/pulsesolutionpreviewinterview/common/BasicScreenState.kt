package com.samar.pulsesolutionpreviewinterview.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.samar.pulsesolutionpreviewinterview.R
import com.samar.pulsesolutionpreviewinterview.domain.model.ScreenState
import com.samar.pulsesolutionpreviewinterview.util.sdp
import com.samar.pulsesolutionpreviewinterview.util.textSdp

@Composable
fun <T> BasicScreenState(
    modifier: Modifier = Modifier,
    outerModifier: Modifier = Modifier,
    state: State<ScreenState<T>>,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = outerModifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.value.isLoading) {
            ScreenAnimation(
                resources = R.raw.loading
            )
        } else if (state.value.error.trim().isNotEmpty()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = androidx.compose.ui.Modifier.padding(25.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ScreenAnimation(
                            modifier = Modifier.size(85.sdp),
                            resources = R.raw.failure
                        )
                        Spacer(modifier = Modifier.size(15.sdp))
                        Text(
                            text = state.value.error ?: "",
                            fontSize = 18.textSdp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = modifier,
                content = content
            )
        }

    }
}