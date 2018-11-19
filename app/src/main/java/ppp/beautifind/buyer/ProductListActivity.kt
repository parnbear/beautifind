package ppp.beautifind.buyer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_product_list.*
import ppp.beautifind.R
import ppp.beautifind.buyer.ProductListActivity.Companion.MODES.*
import ppp.beautifind.persistence.Product
import ppp.beautifind.persistence.Products
import ppp.helpers.enforceInputs

class ProductListActivity : AppCompatActivity() {

    companion object {
        private val TAG = ProductListActivity::class.java.canonicalName
        const val EXTRA_MODE = "EXTRA_MODE"
        const val EXTRA_QUERY_PARAM = "EXTRA_QUERY_PARAM"

        enum class MODES {
            CATEGORY,
            BRAND,
            SEARCH
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        enforceInputs(EXTRA_QUERY_PARAM)
        setupView()
    }

    private fun setupView() {
        // Setup query
        val param = intent.getStringExtra(EXTRA_QUERY_PARAM)
        val baseQuery = FirebaseFirestore.getInstance().collection(Products.PATH)

        val query = when (valueOf(intent.getStringExtra(EXTRA_MODE))) {
            CATEGORY -> baseQuery.whereArrayContains(Products.CATEGORIES, param)
            BRAND -> baseQuery.whereEqualTo(Products.BRAND, param)
            SEARCH -> baseQuery.whereArrayContains(Products.TERMS, param)
        }

        // Setup recycler
        val options = FirestoreRecyclerOptions.Builder<Product>()
            .setQuery(query) { it.toObject(Product::class.java)!!.apply { id = it.id } }
            .setLifecycleOwner(this@ProductListActivity)
            .build()

        searchResults.apply {
            setHasFixedSize(true)
            //this.layoutManager = GridLayoutManager(this@ProductListActivity, 2)
            //this.adapter = ProductAdapter(this@ProductListActivity, options)
        }
    }

}