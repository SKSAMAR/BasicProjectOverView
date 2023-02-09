package com.samar.pulsesolutionpreviewinterview.presentation.productDetails

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.samar.pulsesolutionpreviewinterview.common.BaseScaffold
import com.samar.pulsesolutionpreviewinterview.data.dto.ProductsItem
import com.samar.pulsesolutionpreviewinterview.presentation.productDetails.component.CustomPager
import com.samar.pulsesolutionpreviewinterview.presentation.ui.theme.PulseSolutionPreviewInterviewTheme
import com.samar.pulsesolutionpreviewinterview.util.Constant
import com.samar.pulsesolutionpreviewinterview.util.sdp
import com.samar.pulsesolutionpreviewinterview.util.textSdp

class ProductDetailsActivity : ComponentActivity() {

    var item: ProductsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initializer()
        super.onCreate(savedInstanceState)
        setContent {
            PulseSolutionPreviewInterviewTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    ProductDetailsContent()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ProductDetailsContent(

    ) {
        BaseScaffold(
            isNetworkAvailable = true,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Product Details")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onBackPressedDispatcher.onBackPressed() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack, contentDescription = "back"
                            )
                        }
                    })
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.sdp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(5.sdp))
                    CustomPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.sdp),
                        list = item?.images
                    )
                    Spacer(modifier = Modifier.height(5.sdp))
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 1.sdp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item?.title ?: "Product Name",
                            fontSize = 14.textSdp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "$${item?.price ?: ""}",
                            fontSize = 14.textSdp,
                        )

                        Text(
                            text = "Dealer: ${item?.dealer ?: ""}",
                            fontSize = 14.textSdp,
                        )

                        Text(
                            text = "Description: ${item?.description ?: ""}",
                            fontSize = 14.textSdp,
                        )
                    }
                }
            }
        }
    }


    @Suppress("DEPRECATION")
    private fun initializer() {
        item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constant.PRODUCT, ProductsItem::class.java)
        } else {
            intent.getParcelableExtra(Constant.PRODUCT) as ProductsItem?
        }
    }


}