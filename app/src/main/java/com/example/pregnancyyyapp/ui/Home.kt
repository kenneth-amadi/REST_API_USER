package com.example.pregnancyyyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.pregnancyyyapp.R
import com.example.pregnancyyyapp.adapter.CustomRecyclerAdapter
import com.example.pregnancyyyapp.model.UserInfo
import com.example.pregnancyyyapp.model.UserInfoItem
import com.example.pregnancyyyapp.network.ApiInterface
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    var mAdapter: CustomRecyclerAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    var personUtilsList: ArrayList<UserInfoItem>? = null
    var rq: RequestQueue? = null
    private var requestUrl = "https://myofficerest.herokuapp.com/office/user/getall/empl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rq = Volley.newRequestQueue(this)

        recycleViewContainer!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycleViewContainer!!.layoutManager = layoutManager
        personUtilsList = ArrayList()
        loadUserInfo()
        //sendRequest()
    }

    private fun loadUserInfo() {
        val call: Call<UserInfo> = ApiInterface.getClient().fetchUserInfo()
        call.enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.isSuccessful) {
                    if (!response.body()!!.isEmpty()) {
                        personUtilsList = response.body()
                        mAdapter = CustomRecyclerAdapter(this@Home, personUtilsList)
                        recycleViewContainer.adapter = mAdapter
                    }
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }

        })
    }


    private fun sendRequest() {
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, requestUrl, null, { response ->
            for (i in 0 until response.length()) {
                try {
                    response.getJSONObject(i).run {
                        personUtilsList!!.add(
                            UserInfoItem(
                                getString("address"),
                                getString("email"),
                                getInt("id"),
                                getString("jobposition"),
                                getString("joiningdate"),
                                getLong("mobile"),
                                getString("name"),
                                getString("password"),
                                getString("qualification"),
                                getString("role"),
                                getInt("salary"),
                                getString("userid")
                            )
                        )
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            mAdapter = CustomRecyclerAdapter(this@Home, personUtilsList)
            recycleViewContainer.adapter = mAdapter

        }) { error -> Log.i("Volley Error: ", error.toString()) }
        rq!!.add(jsonArrayRequest)
    }
}