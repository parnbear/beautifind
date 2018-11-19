package ppp.beautifind.buyer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_product.*
import ppp.beautifind.R
import ppp.beautifind.persistence.IMAGES
import ppp.beautifind.persistence.Product
import ppp.beautifind.persistence.Products
import ppp.helpers.enforceInputs
import java.text.NumberFormat

class ProductActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        enforceInputs(EXTRA_PRODUCT_ID)
        setupView()
    }

    private fun setupView() {
        FirebaseFirestore.getInstance()
            .collection(Products.PATH)
            .document(intent.getStringExtra(EXTRA_PRODUCT_ID))
            .get()
            .addOnSuccessListener {
                val product = it.toObject(Product::class.java)!!

                name_Product.text = product.name
                info.text = product.info
                description.text = product.description
                price.text = NumberFormat.getIntegerInstance().format(product.price)

                if (product.image_default.isNotBlank()) {
                    val imageRef = FirebaseStorage.getInstance()
                        .getReference(IMAGES.PATH)
                        .child(product.image_default + ".jpg")

                    imageRef.downloadUrl.addOnSuccessListener { url ->
                        Glide.with(this@ProductActivity).load(url).into(photo)
                    }
                }
            }
    }

}
