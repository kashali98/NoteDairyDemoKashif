package com.kashifali.notesdemoapp.ui.Fragments


import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kashifali.notesdemoapp.Model.Notes
import com.kashifali.notesdemoapp.R
import com.kashifali.notesdemoapp.ViewModel.NotesViewModel
import com.kashifali.notesdemoapp.databinding.FragmentCreateNotesBinding

import java.util.*

class CreateNotesFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var binding:FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener {
            priority="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        
        binding.btnSaveNotes.setOnClickListener { 
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title =binding.edtTitle.text.toString()
        val subTitle =binding.edtSubTitle.text.toString()
        val notes =binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy",d.time)

        val data = Notes(null, title = title, subTitle = subTitle, notes = notes, date = notesDate.toString(),priority)
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Create Notes Succesfuly", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)

    }

}