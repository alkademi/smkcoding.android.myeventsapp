package id.smkcoding.eventsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.smkcoding.githubuserapp.data.EventsService
import id.smkcoding.githubuserapp.data.retrofitRequest
import id.smkcoding.githubuserapp.data.httpClient
import id.smkcoding.githubuserapp.util.dismissLoading
import id.smkcoding.githubuserapp.util.showLoading
import id.smkcoding.githubuserapp.util.tampilToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApiGetEvents()
    }

    private fun callApiGetEvents() {
        showLoading(applicationContext, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = retrofitRequest<EventsService>(httpClient)

        val call = apiRequest.getEvents()
        call.enqueue(object : Callback<EventsList> {

            override fun onFailure(call: Call<EventsList>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)

            }

            override fun onResponse(call: Call<EventsList>, response: Response<EventsList>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->

                        when {
                            response.body()!!.status.equals("OK") ->

                                tampilListEvent(response.body()!!.events)

                            else -> {
                                tampilToast(applicationContext, "")
                            }
                        }

                    else -> {
                        tampilToast(applicationContext, "")
                    }
                }

            }
        })
    }

    private fun tampilListEvent(events: List<EventsList.Event>) {
        listEvent.layoutManager = LinearLayoutManager(this)
        listEvent.adapter = EventsListAdapter(this, events) {

            val bundle = Bundle()
            bundle.putSerializable("data", it)

            val intent = Intent(this, EventsDetailActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)

        }
    }
}
