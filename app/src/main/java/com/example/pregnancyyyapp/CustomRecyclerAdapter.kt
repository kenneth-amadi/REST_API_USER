package com.example.pregnancyyyapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(ctx: Context?, user: List<User>?) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    private var context = ctx
    private var mUser: List<User>? = user

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.single_list_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = mUser!![position]
        val currentItem: User = mUser!![position]
        holder.pName.text = "${currentItem.name} - ${currentItem.userid}"
        holder.pJobProfile.text = "Job Position: ${currentItem.jobposition}"
    }

    override fun getItemCount(): Int {
        return mUser!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pName: TextView = itemView.findViewById<View>(R.id.pNametxt) as TextView
        var pJobProfile: TextView = itemView.findViewById<View>(R.id.pJobProfiletxt) as TextView

        init {
            itemView.setOnClickListener { view ->
                val cpu: User = view.tag as User
                Toast.makeText(view.context, "${cpu.name} ${cpu.userid} is ${cpu.jobposition}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}