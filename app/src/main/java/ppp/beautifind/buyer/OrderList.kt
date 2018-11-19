package ppp.beautifind.buyer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_account.*
import ppp.beautifind.R

class OrderList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        btn_sell.setOnClickListener{
            //val intent = Intent(this,)
        }
    }
}
