package ppp.beautifind.auth

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.item_product.view.*
import ppp.beautifind.R

class BuyerAdapter(val context: Context, val buyers: MutableList<Buyer>): RecyclerView.Adapter<BuyerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return buyers.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val seller = buyers[p1]
        p0.setData(seller, p1)

//        val eiei: List<String>
//        eiei.

        p0.itemView.removeButton.setOnClickListener( {
            buyers.removeAt(p1)
            notifyItemRemoved(p1)
            notifyItemRangeChanged(p1,buyers.size);
        })
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setData(buyer: Buyer?, pos: Int) {
            itemView.price.text = buyer!!.price.toString() + " บาท"
            itemView.productName.text = buyer!!.productName
            itemView.productPhoto.setImageResource(buyer!!.pictureUrl)
            itemView.seller_Name.text = buyer!!.sellerName
        }
    }
}