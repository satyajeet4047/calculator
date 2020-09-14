package com.example.calculator.ui.calculation

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.calculator.R
import com.example.calculator.databinding.CalcuatordashboardFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CalculatorFragment : Fragment() {


    private val TAG = "CalculatorFragment"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CalculatorViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onViewCreated: createview")

        val calculatorBinding : CalcuatordashboardFragmentBinding  = DataBindingUtil.inflate(inflater,R.layout.calcuatordashboard_fragment, container, false)
        calculatorBinding.viewModel = viewModel
        calculatorBinding.lifecycleOwner = viewLifecycleOwner
        return calculatorBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onViewCreated: activity created")

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: start")
        val navController = Navigation.findNavController(view)
        viewModel.checkLoginStatus()
        viewModel.getloginStatus().observe(viewLifecycleOwner, Observer { status ->
            if(status){
                Log.d(TAG, "onViewCreated: LoginFragment Success" )
                viewModel.getHistory()
            }else{
                Log.d(TAG, "onViewCreated: Not logged ")
                navController.navigate(R.id.action_calculatordashboard_to_login)
            }
        })

    }

    override fun onDestroy() {
        viewModel.uploadHistory()
        super.onDestroy()
    }


}

