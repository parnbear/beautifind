package ppp.beautifind.auth

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_product.view.*
import ppp.beautifind.R

class SellerAdapter(val context: Context, val sellers: List<Seller>): RecyclerView.Adapter<SellerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sellers.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val seller = sellers[p1]
        p0.setData(seller, p1)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setData(seller: Seller?, pos: Int) {
            itemView.price.text = seller!!.price.toString() + " บาท"
            itemView.productName.text = seller!!.productName
            itemView.productPhoto.setImageResource(seller!!.pictureUrl)
            itemView.seller_Name.text = seller!!.sellerName

            itemView.removeButton.setOnClickListener( {
                itemView.visibility = View.GONE
            })
        }
    }
}