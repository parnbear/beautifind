package ppp.beautifind.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ppp.beautifind.R

class Signin : AppCompatActivity() {

    private var signupButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signupButton = findViewById(R.id.btn_sign_up)

        signupButton!!.setOnClickListener({
            var intent: Intent = Intent(this@Signin, BottomNav::class.java)
            startActivity(intent)
        })
    }
}
