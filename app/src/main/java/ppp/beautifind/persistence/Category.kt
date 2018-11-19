package ppp.beautifind.persistence

import com.google.firebase.firestore.Exclude

data class Category(
    @Exclude var id: String = "",
    var code: String = "",
    var name: String = ""
)