package com.samar.pulsesolutionpreviewinterview.presentation.landing.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.samar.pulsesolutionpreviewinterview.R
import com.samar.pulsesolutionpreviewinterview.common.ScreenAnimation
import com.samar.pulsesolutionpreviewinterview.data.dto.ProductsItem
import com.samar.pulsesolutionpreviewinterview.presentation.productDetails.ProductDetailsActivity
import com.samar.pulsesolutionpreviewinterview.util.Constant
import com.samar.pulsesolutionpreviewinterview.util.sdp
import com.samar.pulsesolutionpreviewinterview.util.textSdp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductRow(
    productsItem: ProductsItem,
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Card(
        modifier = modifier.height(70.sdp),
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        onClick = {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(Constant.PRODUCT, productsItem)
            context.startActivity(intent)
        },
    ) {
        Row(
            modifier = modifier.height(70.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(width = 105.sdp, height = 70.sdp),
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ScreenAnimation(resources = R.raw.loading)
                    }
                },
                error = {
                    ScreenAnimation(resources = R.raw.failure)
                },
                model = productsItem.images?.firstOrNull()?.imageURL ?: Constant.NO_IMAGE,
                contentDescription = "product_image"
            )
            Spacer(modifier = Modifier.width(10.sdp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.sdp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = productsItem.title ?: "Product Name",
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "$${productsItem.price ?: ""}",
                    fontSize = 9.textSdp,
                )

                Text(
                    text = "Dealer: ${productsItem.dealer ?: ""}",
                    fontSize = 9.textSdp,
                )
            }
        }
    }
}