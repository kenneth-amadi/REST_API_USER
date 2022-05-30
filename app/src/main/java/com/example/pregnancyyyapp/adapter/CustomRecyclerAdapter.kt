package com.example.pregnancyyyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancyyyapp.R
import com.example.pregnancyyyapp.model.UserInfoItem
import kotlinx.android.synthetic.main.single_list_item.view.*

class CustomRecyclerAdapter(ctx: Context?, user: ArrayList<UserInfoItem>?) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    private var context = ctx
    private var mUser: ArrayList<UserInfoItem>? = user

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.single_list_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = mUser!![position]
        val currentItem: UserInfoItem = mUser!![position]
        holder.itemView.nameInput.text = "Name: ${currentItem.name}"
        //holder.itemView.useridInput.text = "${currentItem.userid}"
        holder.itemView.AddressInput.text = "Address: ${currentItem.address}"
        holder.itemView.phoneInput.text = "Mobile: ${currentItem.mobile}"
        holder.itemView.emailInput.text = "Email: ${currentItem.email}"
        holder.itemView.roleInput.text = "Role: ${currentItem.role}"
        holder.itemView.joiningDateInput.text = "Joining Date: ${currentItem.joiningdate}"
        holder.itemView.qualificationInput.text = "Qualification: ${currentItem.qualification}"
        holder.itemView.jobPositionInput.text = "Job Position: ${currentItem.jobposition}"
        holder.itemView.salaryInput.text = "Salary: ${currentItem.salary}"
    }

    override fun getItemCount(): Int {
        return mUser!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { view ->
                val cpu: UserInfoItem = view.tag as UserInfoItem
                Toast.makeText(view.context, "${cpu.name} ${cpu.userid} is ${cpu.jobposition}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}