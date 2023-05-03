package com.thies.hw6_exercise1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.thies.hw6_exercise1.databinding.ListItemCrimeBinding
import java.text.DateFormat


class CrimeHolder(private val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = DateFormat.getDateInstance().format(crime.date)

        // detecting clicks in CrimeHolder
        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.crimeSolved.visibility = if(crime.isSolved) {
            View.VISIBLE
        }else{
            View.GONE
        }
    }
}

// sits in between RecyclerView and data set; RecyclerView does not know anything about Crimes
class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {

    // creates a binding to display, wraps it in view holder, and returns the result in a new instance of CrimeHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    // populates holder with crime from given position
    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size
}

