package com.samar.pulsesolutionpreviewinterview.presentation.landing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.samar.pulsesolutionpreviewinterview.R
import com.samar.pulsesolutionpreviewinterview.common.BaseScaffold
import com.samar.pulsesolutionpreviewinterview.common.BasicScreenState
import com.samar.pulsesolutionpreviewinterview.common.ScreenAnimation
import com.samar.pulsesolutionpreviewinterview.data.dto.ProductsItem
import com.samar.pulsesolutionpreviewinterview.presentation.landing.component.ProductRow
import com.samar.pulsesolutionpreviewinterview.presentation.ui.theme.PulseSolutionPreviewInterviewTheme
import com.samar.pulsesolutionpreviewinterview.util.Utils.isScrolledToTheEnd
import com.samar.pulsesolutionpreviewinterview.util.Utils.showToast
import com.samar.pulsesolutionpreviewinterview.util.sdp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : ComponentActivity() {

    private val viewModel by viewModels<LandingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PulseSolutionPreviewInterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LandingContent()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun LandingContent() {
        val listState = rememberLazyListState()
        val networkState = viewModel.connectionLiveData.observeAsState(initial = true)
        LaunchedEffect(key1 = listState.isScrolledToTheEnd()) {
            viewModel.state.value.receivedResponse?.lastOrNull()?.let {
                if (!viewModel.isFetchingMore) {
                    viewModel.fetchAllProducts(initialFetch = false) {
                        showToast(it)
                    }
                }
            }
        }

        BaseScaffold(isNetworkAvailable = networkState.value, topBar = {
            TopAppBar(title = {
                Text(text = "Landing Page")
            })
        }) {
            BasicScreenState(state = viewModel.state) {
                Column {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .padding(horizontal = 10.sdp)
                    ) {
                        viewModel.state.value.receivedResponse?.let {
                            items(it) { item: ProductsItem? ->
                                item?.let {
                                    ProductRow(
                                        productsItem = it,
                                        modifier = Modifier.padding(vertical = 10.sdp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (viewModel.isFetchingMore) {
                ScreenAnimation(
                    resources = R.raw.loading, modifier = Modifier.align(Alignment.Center)
                )
            }

        }

    }


}
