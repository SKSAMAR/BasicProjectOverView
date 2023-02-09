package com.samar.pulsesolutionpreviewinterview.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewResponseDto(
    @field:SerializedName("flgIsSuccess")
    val flgIsSuccess: Boolean? = null,
    @field:SerializedName("Products")
    val products: ArrayList<ProductsItem?>? = null,
    @field:SerializedName("stMessage")
    val stMessage: String? = null
) : Parcelable{
    override fun toString(): String {
        return "ReviewResponseDto(flgIsSuccess=$flgIsSuccess, products=$products, stMessage=$stMessage)"
    }
}

@Parcelize
data class ProductsItem(
    @field:SerializedName("Description")
    val description: String? = null,
    @field:SerializedName("Price")
    val price: Float? = null,
    @field:SerializedName("Images")
    val images: ArrayList<ImagesItem?>? = null,
    @field:SerializedName("Title")
    val title: String? = null,
    @field:SerializedName("ProductId")
    val productId: Int? = null,
    @field:SerializedName("Dealer")
    val dealer: String? = null
) : Parcelable

@Parcelize
data class ImagesItem(
    @field:SerializedName("ImageURL")
    val imageURL: String? = null
) : Parcelable
