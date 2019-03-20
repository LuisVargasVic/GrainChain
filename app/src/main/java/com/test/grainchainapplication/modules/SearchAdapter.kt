package com.test.grainchainapplication.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.grainchainapplication.R
import com.test.grainchainapplication.models.Contact
import java.util.*

/**
 * Created by Luis Vargas on 3/19/19.
 */

class SearchAdapter(context: Context, private var mOriginalValues: ArrayList<Contact>, val listener: OnContactLongClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    internal inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(contact: Contact?) {
            val imageView = view.findViewById<ImageView>(R.id.image)
            imageView.setImageBitmap(contact?.profile)
            val nameTextView = view.findViewById<TextView>(R.id.tvName)
            nameTextView.text = contact?.name ?: ""
            val lastNameTextView = view.findViewById<TextView>(R.id.tvLastName)
            lastNameTextView.text = contact?.lastName ?: ""
            val ageTextView = view.findViewById<TextView>(R.id.tvAge)
            ageTextView.text = contact?.age ?: ""
            val phoneTextView = view.findViewById<TextView>(R.id.tvPhone)
            phoneTextView.text = contact?.phone ?: ""

            view.setOnLongClickListener {
                listener.onLongClick(contact!!)
                return@setOnLongClickListener true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_search_contact, parent, false) as View

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDisplayedValues!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(mDisplayedValues?.get(position))
    }

    private var mDisplayedValues: ArrayList<Contact>? = null    // Values to be displayed
    private var inflater: LayoutInflater

    init {
        this.mDisplayedValues = mOriginalValues
        inflater = LayoutInflater.from(context)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun publishResults(constraint: CharSequence, results: FilterResults) {

                mDisplayedValues = results.values as ArrayList<Contact> // has the filtered values
                notifyDataSetChanged()  // notifies the data with new filtered values
            }

            @Suppress("NAME_SHADOWING")
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var constraint = constraint
                val results = FilterResults()        // Holds the results of a filtering operation in values
                val filteredArrList = ArrayList<Contact>()

                if (mOriginalValues == null) {
                    mOriginalValues = ArrayList(mDisplayedValues!!) // saves the original data in mOriginalValues
                }

                if (constraint == null || constraint.isEmpty()) {

                    // set the Original result to return
                    results.count = mOriginalValues.size
                    results.values = mOriginalValues
                } else {
                    constraint = constraint.toString().toLowerCase()
                    for (i in mOriginalValues.indices) {
                        val data = mOriginalValues[i].name
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            filteredArrList.add(mOriginalValues[i])
                        }
                    }
                    // set the Filtered result to return
                    results.count = filteredArrList.size
                    results.values = filteredArrList
                }
                return results
            }
        }
    }
}