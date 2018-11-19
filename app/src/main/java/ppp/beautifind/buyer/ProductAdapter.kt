package ppp.beautifind.buyer

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_product.view.*
import ppp.beautifind.R
import ppp.beautifind.persistence.IMAGES
import ppp.beautifind.persistence.Product

/*class ProductAdapter(private val context: Context, options: FirestoreRecyclerOptions<Product>) :
    FirestoreRecyclerAdapter<Product, ProductAdapter.ProductRowViewHolder>(options) {

    override fun onBindViewHolder(holder: ProductRowViewHolder, position: Int, model: Product) = holder.bind(model)
    override fun onCreateViewHolder(group: ViewGroup, i: Int) =
        ProductRowViewHolder(context, LayoutInflater.from(group.context).inflate(R.layout.item_product, group, false))

    class ProductRowViewHolder(private val context: Context, private val row: View) : RecyclerView.ViewHolder(row) {
        /al photo: ImageView = row.photo
        val name: TextView = row.name_Product

        fun bind(product: Product) {
            row.setOnClickListener {
                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra(ProductActivity.EXTRA_PRODUCT_ID, product.id)
                context.startActivity(intent)
            }

            name.text = product.name

            if (product.image_default.isNotBlank()) {
                val imageRef = FirebaseStorage.getInstance()
                    .getReference(IMAGES.PATH)
                    .child(product.image_default + ".jpg")

                imageRef.downloadUrl.addOnSuccessListener { url ->
                    Glide.with(context).load(url).into(photo)
                }
            }
        }
    }
}*/