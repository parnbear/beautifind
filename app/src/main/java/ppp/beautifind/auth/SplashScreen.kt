package ppp.beautifind.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*
import ppp.beautifind.R

class SplashScreen : AppCompatActivity() {

    fun OnDestroy(){
        super.onDestroy()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in))

        Handler().postDelayed({
            iv_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))

            Handler().postDelayed({
                iv_logo.visibility = View.GONE
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            },500)
        },2500)
    }
}
