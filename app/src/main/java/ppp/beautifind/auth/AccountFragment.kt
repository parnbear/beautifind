package ppp.beautifind.auth


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_account.*

import ppp.beautifind.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment() {

    private var ctx: Context? = null
    private var a: Activity? = null

    private var buttonBuy: Button? = null
    private var buttonSell: Button? = null
    private var buttonAddProd: Button? = null

    private var linearBuy: LinearLayout? = null
    private var linearSell: LinearLayout? = null

    fun accountInit(context: Context) {
        ctx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val layoutManager = LinearLayoutManager(ctx)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val layoutManager2 = LinearLayoutManager(ctx)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        buttonBuy = view.findViewById(R.id.btn_buy)
        buttonSell = view.findViewById(R.id.btn_sell)
        linearBuy = view.findViewById(R.id.linear_buyer)
        linearSell = view.findViewById(R.id.linear_seller)
        buttonAddProd = view.findViewById(R.id.btn_add_product)

        linearBuy!!.visibility = View.VISIBLE
        linearSell!!.visibility = View.GONE

        buttonBuy!!.setOnClickListener({
            linearBuy!!.visibility = View.VISIBLE
            linearSell!!.visibility = View.GONE

            buttonBuy!!.setBackgroundColor(Color.parseColor("#37bda5"))
            buttonSell!!.setBackgroundColor(Color.parseColor("#6dead4"))
        })

        buttonSell!!.setOnClickListener( {
            linearBuy!!.visibility = View.GONE
            linearSell!!.visibility = View.VISIBLE

            buttonBuy!!.setBackgroundColor(Color.parseColor("#6dead4"))
            buttonSell!!.setBackgroundColor(Color.parseColor("#37bda5"))
        })

        buttonAddProd!!.setOnClickListener({
            var intent: Intent = Intent(ctx, UpdateProductActivity::class.java)
            startActivity(intent)
        })

//        buyerInit()

        var recycleView = view.findViewById(R.id.recycleView) as RecyclerView
        recycleView.layoutManager = layoutManager

        val adapter = SellerAdapter(this!!.ctx!!, SellerList.sellers)
        recycleView.adapter = adapter

        var recyclerView2 = view.findViewById(R.id.recycleView2) as RecyclerView
        recyclerView2.layoutManager = layoutManager2

        val adapter2 = BuyerAdapter(this!!.ctx!!, BuyerList.buyers)
        recyclerView2.adapter = adapter2

        return view
    }

    companion object {
        fun newInstance(): AccountFragment = AccountFragment()
    }

    fun setContext(context: Context) {
        ctx = context
    }
}
