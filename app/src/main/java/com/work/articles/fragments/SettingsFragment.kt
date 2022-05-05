package com.work.articles.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.work.articles.databinding.FragmentSettingsBinding
import com.work.articles.logic.Constants
import com.work.articles.logic.filters.custom.DateWrapper


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    private val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initUiViews()
        return view
    }

    private fun initUiViews() {
        arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            Constants.TOPICS
        )

        binding.dropDownTopic.setAdapter(arrayAdapter)
        binding.pickDatesButton.setOnClickListener {
            datePicker.addOnPositiveButtonClickListener {
                binding.selectedDatesTextView.text = datePicker.headerText
            }
            datePicker.show(childFragmentManager, "DatePicker")
        }
        binding.doneButton.setOnClickListener {

            if (!verifyInputs()) {
                Toast.makeText(requireContext(), "Please fill all inputs", Toast.LENGTH_SHORT)
                    .show()
            } else {
                datePicker.selection?.let {
                    viewModel.saveAndApplySettings(
                        DateWrapper(it.first, it.second),
                        binding.dropDownTopic.text.toString(),
                        binding.titleSearchQuery.text.toString()
                    )
                }

            }
        }
    }

    private fun verifyInputs() =
        datePicker.selection != null && !binding.dropDownTopic.text.equals("select topic") && binding.titleSearchQuery.text.toString()
            .isNotEmpty()
}