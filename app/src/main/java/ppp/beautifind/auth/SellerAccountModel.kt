package ppp.beautifind.auth

import android.graphics.drawable.Drawable
import ppp.beautifind.R

data class Seller(var sellerName: String,
                  var productName: String,
                  var price: Int,
                  var pictureUrl: Int)

object SellerList {
    val sellers = mutableListOf<Seller> (
        Seller("Pingpong", "Zhecosmetics", 290, R.drawable.add_picture),
        Seller("Nay", "Run", 790, R.drawable.add_picture),
        Seller("Nai", "Walk", 590, R.drawable.add_picture),
        Seller("Mansome", "Jogging", 50, R.drawable.add_picture)
    )
}