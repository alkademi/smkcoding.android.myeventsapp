package id.smkcoding.githubuserapp.data

import id.smkcoding.eventsapp.EventsList
import retrofit2.Call
import retrofit2.http.GET

interface EventsService {

    @GET("events")
    fun getEvents(): Call<EventsList>

}