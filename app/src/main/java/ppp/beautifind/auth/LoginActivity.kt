package ppp.beautifind.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import ppp.beautifind.R
import ppp.beautifind.TempHome

class LoginActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null

    private var editId: EditText? = null
    private var editPassword: EditText? = null
    private var btnSignIn: Button? = null
    private var btnCreate: Button? = null

    companion object {
        private val TAG = LoginActivity::class.java.canonicalName
        const val REQUEST_GOOGLE_SIGN_IN = 8
    }

    private val auth = FirebaseAuth.getInstance()
    private val googleAuthClient by lazy {
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
        setContentView(R.layout.activity_login)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)


        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("message")

        InitialBindingView()

        val id = "admin"
        val pwd = "admin"

        btnSignIn!!.setOnClickListener( {
            if (id == editId!!.text.toString() && pwd == editPassword!!.text.toString()) {
                val intent = Intent(this@LoginActivity, BottomNav::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "Email or Password are incorrect.", Toast.LENGTH_SHORT).show()
            }
        })

        btnCreate!!.setOnClickListener({
            var intent = Intent(this@LoginActivity, Signin::class.java)
            startActivity(intent)
        })

        val email = "tongtananut@gmailcom"

        //mDatabase!!.child("email").setValue("Tananut")
        //mDatabase!!.child("email:"+email).child("name").setValue("Tananut Panyagosa")
        //mDatabase!!.child("email:"+email).child("username").setValue("trewzaki")
        //mDatabase!!.child("email:"+email).child("password").setValue("1234")

        setupViews()
    }

    public fun InitialBindingView() {
        editId = findViewById(R.id.edit_id) as EditText
        editPassword = findViewById(R.id.edit_password) as EditText
        btnSignIn = findViewById(R.id.btn_sign_in) as Button
        btnCreate = findViewById(R.id.create_new_account) as Button
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) return
        when (requestCode) {
            REQUEST_GOOGLE_SIGN_IN -> googleAuth(data)
        }
    }

    public override fun onStart() {
        super.onStart()
        updateUI(auth.currentUser) // check if already logged in
    }

    private fun setupViews() {
        loginGoogle.setOnClickListener {
            startActivityForResult(googleAuthClient.signInIntent, REQUEST_GOOGLE_SIGN_IN)
        }
    }

    private fun googleAuth(data: Intent) {
        try {
            GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
                ?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
        }
    }

    private fun firebaseAuthWithGoogle(googleAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                updateUI(auth.currentUser)
            }
            .addOnFailureListener {
                Log.w(TAG, "signInWithCredential:failure", it)
                Snackbar.make(
                    this@LoginActivity.findViewById(android.R.id.content),
                    "Authentication Failed.",
                    Snackbar.LENGTH_SHORT
                ).show()
                updateUI(null)
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        Log.d("DEBUG::", "UpdateUI triging!")
        if (user != null) return startActivity(Intent(this@LoginActivity, MapsActivity::class.java))
    }

}