package ppp.beautifind.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.w3c.dom.Text
import ppp.beautifind.R
import java.lang.Exception

class BottomNav : AppCompatActivity(), OnMapReadyCallback {

    val manager = supportFragmentManager

    private lateinit var map: GoogleMap

    private var testText : TextView? = null
    private var googleMap : MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        val navigationView = findViewById<View>(R.id.nav) as BottomNavigationView


        val bottomNavigation: BottomNavigationView = (findViewById(R.id.nav))
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = HomeFragment.newInstance()
        openFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{ item ->
        when (item.itemId) {
            R.id.home -> {
                val fragment = HomeFragment.newInstance()
                openFragment(fragment)

                Toast.makeText(this@BottomNav, "Home is Clicked", Toast.LENGTH_SHORT).show()

                return@setOnNavigationItemSelectedListener true
            }
            R.id.category -> {
                val fragment = CategoryFragment.newInstance()
                openFragment(fragment)

                Toast.makeText(this@BottomNav, "Category is Clicked", Toast.LENGTH_SHORT).show()

                return@setOnNavigationItemSelectedListener true
            }
            R.id.map -> {
                val fragment = MapFragment.newInstance()
                openFragment(fragment)

                Toast.makeText(this@BottomNav, "Map is Clicked", Toast.LENGTH_SHORT).show()

                return@setOnNavigationItemSelectedListener true
            }
            R.id.account -> {
                val fragment = AccountFragment.newInstance()
                fragment.accountInit(this@BottomNav)
                openFragment(fragment)

                Toast.makeText(this@BottomNav, "Account is Clicked", Toast.LENGTH_SHORT).show()

                return@setOnNavigationItemSelectedListener true
            }


        }
        false
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /*fun createHomeFragment() {
        val transaction = manager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createCategoryFragment() {
        val transaction = manager.beginTransaction()
        val fragment = CategoryFragment()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createMapFragment() {
        val transaction = manager.beginTransaction()
        val fragment = MapFragment()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createAccountFragment() {
        val transaction = manager.beginTransaction()
        val fragment = AccountFragment()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }*/
}
