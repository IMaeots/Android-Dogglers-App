package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initializing the data using the List found in data/DataSource
    val data = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Declaring and initializing all of the list item UI components
        val imageView: ImageView = view.findViewById(R.id.dog_image)
        val nameView: TextView = view.findViewById(R.id.dog_name)
        val ageView: TextView = view.findViewById(R.id.dog_age)
        val hobbyView: TextView = view.findViewById(R.id.dog_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // Using a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.
        if (layout == Layout.GRID) {
            val adapterGridLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
            // Reflect the inflated layout.
            return DogCardViewHolder(adapterGridLayout)
        } else {
            val adapterBasicLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
            // Reflect the inflated layout.
            return DogCardViewHolder(adapterBasicLayout)
        }
    }

    override fun getItemCount(): Int = data.size // returns the size of the data set

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val item = data[position]
        // Set the image resource for the current dog
        holder.imageView.setImageResource(item.imageResourceId)
        // Set the text for the current dog's name
        holder.nameView.text = item.name

        val resources = context?.resources // to get string resources more easily

        // Set the text for the current dog's age
        holder.ageView.text = resources?.getString(R.string.dog_age, item.age)
        // Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.hobbyView.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
