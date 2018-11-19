package ppp.beautifind

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_temp_home.*
import ppp.beautifind.auth.LoginActivity
import ppp.beautifind.buyer.ProductListActivity
import ppp.beautifind.seller.ProductUpdateActivity

class TempHome : AppCompatActivity() {

    private val googleAuth by lazy {
        GoogleSignIn.getClient(
            this,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_home)

        tempBrowseCategories.setOnClickListener {
            //startActivity(Intent(this@TempHome, CategoryActivity::class.java))
        }

        tempAddProduct.setOnClickListener {
            startActivity(Intent(this@TempHome, ProductUpdateActivity::class.java))
        }

        tempSearchProducts.setOnClickListener {
            startActivity(Intent(this@TempHome, ProductListActivity::class.java).apply {
                putExtra(ProductListActivity.EXTRA_MODE, ProductListActivity.Companion.MODES.CATEGORY.toString())
                putExtra(ProductListActivity.EXTRA_QUERY_PARAM, "electronics")
            })
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            googleAuth.signOut().addOnSuccessListener { _ ->
                startActivity(Intent(this@TempHome, LoginActivity::class.java))
            }
        }
    }

}
