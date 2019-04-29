package id.smkcoding.eventsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.smkcoding.eventsapp.util.convertStringDate
import kotlinx.android.synthetic.main.activity_event_detail.*


class EventsDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var event: EventsList.Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        initView()
    }


    private fun initView() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        ambilData()

        fabShareEvent.setOnClickListener { shareEvent() }
    }

    private fun shareEvent() {
        val shareIntent = Intent()

        val latitude = event?.latitude
        val longitude = event?.longitude

        val googleMapsLink = "http://maps.google.com/maps?daddr=" + latitude + "," + longitude
        val shareContent = "Mari kunjungi " + event?.title + "\n" + googleMapsLink

        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent)

        startActivity(Intent.createChooser(shareIntent, "Bagikan ke"))
    }

    private fun ambilData() {
        val bundle = intent.extras
        event = bundle.getSerializable("data") as EventsList.Event

        val image = event?.image
        val eventName = event?.title
        val eventDate = event?.date
        val eventDesc = event?.description

        Glide.with(this).load(image).into(imgEvent)

        txtEventName.text = eventName

        val dateFormated = convertStringDate(eventDate!!, "EEE, dd/MM/yyyy")
        txtEventDate.text = dateFormated

        txtEventDesc.text = eventDesc

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        //-6.898231, 107.623073
        val location = LatLng(-6.898231, 107.623073)

        val cameraPosition = CameraPosition.Builder()
            .target(location)      // Sets the center of the map to location
            .zoom(17f)                   // Sets the zoom
            .bearing(90f)                // Sets the orientation of the camera to east
            .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
            .build()

        googleMap?.addMarker(MarkerOptions().position(location).title(""))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(location))
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}
