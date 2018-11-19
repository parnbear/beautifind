package ppp.beautifind.auth

import android.graphics.drawable.Drawable
import ppp.beautifind.R

data class Buyer(var sellerName: String,
                  var productName: String,
                  var price: Int,
                  var pictureUrl: Int)

object BuyerList {
    val buyers = listOf<Buyer> (
        Buyer("Faa shop", "Foong", 860, R.drawable.foong),
        Buyer("Nay shop", "Body cream", 590, R.drawable.body)
    )
}