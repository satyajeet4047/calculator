package com.example.calculator.ui.calculatorfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.R

class calculatordashboard : Fragment() {

    companion object {
        fun newInstance() =
            calculatordashboard()
    }

    private lateinit var viewModel: CalculatordashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calcuatordashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalculatordashboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}