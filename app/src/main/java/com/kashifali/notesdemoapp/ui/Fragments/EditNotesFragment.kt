package com.kashifali.notesdemoapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kashifali.notesdemoapp.Model.Notes
import com.kashifali.notesdemoapp.R
import com.kashifali.notesdemoapp.ViewModel.NotesViewModel
import com.kashifali.notesdemoapp.databinding.FragmentEditNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding:FragmentEditNotesBinding
    var priority : String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1"->{
                priority="1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)

            }
            "2"->{
                priority="2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {

            updateNotes(it)
        }




        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title =binding.edtTitle.text.toString()
        val subTitle =binding.edtSubTitle.text.toString()
        val notes =binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy",d.time)

        val data = Notes(oldNotes.data.id, title = title, subTitle = subTitle, notes = notes, date = notesDate.toString(),priority)
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Create Update Succesfuly", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            val bottomSheet: BottomSheetDialog= BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                findNavController().popBackStack()
            }

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }



            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}