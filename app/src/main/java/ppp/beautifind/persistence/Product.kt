package ppp.beautifind.persistence

import com.google.firebase.firestore.Exclude

data class Product(
    @Exclude var id: String = "",
    var name: String = "",
    var description: String = "",
    var info: String = "",
    var price: Int = 0,
    var sellerId: String = "",
    var manufacturer: String = "",
    var images: MutableMap<String, Int> = mutableMapOf(),
    var image_default: String = ""
)