package ppp.beautifind.auth

import android.graphics.drawable.Drawable
import ppp.beautifind.R
import ppp.beautifind.auth.BuyerList.buyers

data class Buyer(var sellerName: String,
                  var productName: String,
                  var price: Int,
                  var pictureUrl: Int)

object BuyerList {
    val buyers = mutableListOf<Buyer> (
        Buyer("Faa shop", "Foong", 860, R.drawable.foong),
        Buyer("Nay shop", "Body cream", 590, R.drawable.body),
        Buyer("Tong shop", "Foong", 860, R.drawable.foong),
        Buyer("Koon shop", "Body cream", 590, R.drawable.body),
        Buyer("Gib shop", "Foong", 860, R.drawable.foong),
        Buyer("Gorn shop", "Body cream", 590, R.drawable.body)
    )
}

//fun buyerInit() {
//    var model1: Buyer? = null
//    model1!!.sellerName = "Faa shop"
//    model1!!.productName = "Foong"
//    model1!!.price = 860
//    model1!!.pictureUrl = R.drawable.foong
//
//    var model2: Buyer? = null
//    model2!!.sellerName = "Nay shop"
//    model2!!.productName = "Body cream"
//    model2!!.price = 590
//    model2!!.pictureUrl = R.drawable.body
//
//    buyers!!.add(model1)
//    buyers!!.add(model2)
//}