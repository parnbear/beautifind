package ppp.beautifind.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ppp.beautifind.R

class UpdateProductActivity : AppCompatActivity() {

    private var subbitBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        subbitBtn = findViewById(R.id.productSubmit) as Button

        subbitBtn!!.setOnClickListener({
            finish()
        })
    }
}
