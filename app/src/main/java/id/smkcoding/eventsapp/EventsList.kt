package id.smkcoding.eventsapp
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class EventsList(
    @SerializedName("status")
    val status: String,
    @SerializedName("alert")
    val alert: Alert,
    @SerializedName("events")
    val events: List<Event>
): Serializable {
    data class Event(
        @SerializedName("title")
        val title: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("latitude")
        val latitude: Double,
        @SerializedName("longitude")
        val longitude: Double
    ): Serializable

    data class Alert(
        @SerializedName("code")
        val code: Int,
        @SerializedName("message")
        val message: String
    ): Serializable
}