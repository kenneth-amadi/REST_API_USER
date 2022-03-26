package com.example.pregnancyyyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONException

class Home : AppCompatActivity() {
    var mAdapter: CustomRecyclerAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    var personUtilsList: MutableList<User>? = null
    var rq: RequestQueue? = null
    var request_url = "https://myofficerest.herokuapp.com/office/user/getall/empl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rq = Volley.newRequestQueue(this)

        recycleViewContainer!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycleViewContainer!!.layoutManager = layoutManager
        personUtilsList = ArrayList()
        sendRequest()
    }


    private fun sendRequest() {
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, request_url, null, { response ->
            for (i in 0 until response?.length()!!) {
                //val personUtils = User()
                try {
                    response.getJSONObject(i).run {
                        personUtilsList!!.add(
                            User(
                                getString("address"),
                                getString("email"),
                                getInt("id"),
                                getString("jobposition"),
                                getString("joiningdate"),
                                getInt("mobile"),
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