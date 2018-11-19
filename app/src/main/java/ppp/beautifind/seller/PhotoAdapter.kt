package ppp.beautifind.seller

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_product_update_photo.view.*
import ppp.beautifind.R

class PhotoAdapter(private val recycler: RecyclerView, private val data: MutableList<Uri>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    fun addItems(vararg newItems: Uri) {
        val previousCount = data.count()
        data.addAll(newItems)
        notifyItemRangeInserted(previousCount, newItems.size)
        recycler.smoothScrollToPosition(data.size - 1)
    }

    fun removeItem(uri: Uri) {
        val position = data.indexOf(uri)
        if (position >= 0) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = data.count()
    override fun onBindViewHolder(holder: PhotoHolder, position: Int) = holder.bind(data[position])
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product_update_photo, parent, false),
        this@PhotoAdapter
    )

    class PhotoHolder(itemView: View, private val adapter: PhotoAdapter) : RecyclerView.ViewHolder(itemView) {
        private val photo = itemView.photo
        private val photoDelete = itemView.photoDelete

        fun bind(imageUri: Uri) {
            photo.setImageURI(imageUri)
            photoDelete.setOnClickListener { adapter.removeItem(imageUri) }
        }
    }

}