package id.smkcoding.eventsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.smkcoding.eventsapp.util.convertStringDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.events_list_item.*

class EventsListAdapter(private val context: Context, private val items: List<EventsList.Event>,
                        private val listener: (EventsList.Event) -> Unit) :
    RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.events_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val context: Context, override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: EventsList.Event, listener: (EventsList.Event) -> Unit) {
            txtEventName.text = item.title

            val dateFormated = convertStringDate(item.date, "EEE, dd/MM/yyyy")
            txtEventDate.text = dateFormated

            Glide.with(context).load(item.image).into(imgEvent)

            containerView.setOnClickListener { listener(item) }
        }

    }
}