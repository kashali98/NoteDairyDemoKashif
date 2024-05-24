package com.kashifali.notesdemoapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kashifali.notesdemoapp.Model.Notes
import com.kashifali.notesdemoapp.R
import com.kashifali.notesdemoapp.databinding.ItemNotesBinding
import com.kashifali.notesdemoapp.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){
    fun filtering(newFilteredList:ArrayList<Notes>){
        notesList=newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data =notesList[position]
        holder.binding.apply {
            notesTitle.text=data.title
            notesSubTitle.text=data.subTitle
            notesDate.text=data.date

            when(data.priority){
                "1"->{
                    viewPriority.setBackgroundResource(R.drawable.green_dot)
                }
                "2"->{
                    viewPriority.setBackgroundResource(R.drawable.yellow_dot)
                }
                "3"->{
                    viewPriority.setBackgroundResource(R.drawable.red_dot)
                }
            }
            root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
                Navigation.findNavController(it).navigate(action)

            }
        }


    }

    override fun getItemCount(): Int {
        val notesList = notesList.size
        return notesList
    }
}