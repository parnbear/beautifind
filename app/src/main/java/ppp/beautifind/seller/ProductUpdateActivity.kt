package ppp.beautifind.seller

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_ALLOW_MULTIPLE
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_product_update.*
import ppp.beautifind.R
import ppp.beautifind.persistence.IMAGES
import ppp.beautifind.persistence.Products
import java.util.*
import kotlin.collections.HashMap

class ProductUpdateActivity : AppCompatActivity() {

    companion object {
        private val TAG = ProductUpdateActivity::class.java.canonicalName
        const val EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID"
        const val REQUEST_IMAGE_GET = 8
    }

    private val photoUris: MutableList<Uri> = mutableListOf()
    private val adapter by lazy { PhotoAdapter(photos, photoUris) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_update)

        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) return
        when (resultCode) {
            REQUEST_IMAGE_GET -> receiveImage(data)
        }
    }

    private fun setupViews() {
        imageView.setOnClickListener { requestImage() }
        productSubmit.setOnClickListener { submit() }

        photos.apply {
            layoutManager = LinearLayoutManager(this@ProductUpdateActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ProductUpdateActivity.adapter
            setHasFixedSize(true)
        }
    }

    private fun requestImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra(EXTRA_ALLOW_MULTIPLE, true)
        }

        if (intent.resolveActivity(packageManager) != null) startActivityForResult(intent, REQUEST_IMAGE_GET)
    }

    private fun receiveImage(data: Intent) {
        val clipData = data.clipData
        if (clipData != null) {
            // >1 photo
            adapter.addItems(*Array(clipData.itemCount) { clipData.getItemAt(it).uri })
        } else {
            // 1 photo
            val uri = data.data
            if (uri != null) adapter.addItems(uri)
        }
    }

    private fun submit() {
        val baseRef = FirebaseFirestore.getInstance().collection(Products.PATH)
        val ref =
            if (intent.hasExtra(EXTRA_PRODUCT_ID)) baseRef.document(intent.getStringExtra(EXTRA_PRODUCT_ID))
            else baseRef.document()

        // Images > Storage
        val imagesDefault = UUID.randomUUID().toString()
        val imagesData = HashMap<String, Int>(photoUris.size)
        val imagesFolderRef = FirebaseStorage.getInstance().getReference(IMAGES.PATH)

        for (i in 0 until photoUris.size) {
            // Random IDs
            val randomId = if (i == 0) imagesDefault else UUID.randomUUID().toString()
            imagesData[randomId] = i

            // Upload
            val imageRef = imagesFolderRef.child("$randomId.jpg")
            val uploadTask = imageRef.putFile(photoUris[i])

            // TODO - Handle failed uploads
            uploadTask.addOnFailureListener { exception ->
                Log.e(TAG, "Image upload failed", exception)
            }.addOnSuccessListener { taskSnapshot ->
                Log.d(TAG, "Upload successful: ${taskSnapshot.uploadSessionUri}")
            }
        }

        // Data > FireStore
        val product = HashMap<String, Any>()
        product[Products.NAME] = name_Product.text.toString()
        product[Products.DESCRIPTION] = description.text.toString()
        product[Products.PRICE] = price_Input.text.toString().toInt()
        product[Products.IMAGES] = imagesData
        product[Products.IMAGE_DEFAULT] = imagesDefault

        ref.set(product, SetOptions.merge())
            .addOnSuccessListener { _ ->
                Log.d(TAG, "Update complete")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Could not save", exception)
            }

        // TODO: redirect to list view
    }

}
