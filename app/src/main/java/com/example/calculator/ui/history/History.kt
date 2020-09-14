package com.example.calculator.ui.history

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.R
import com.example.calculator.data.HistoryList
import kotlinx.android.synthetic.main.history_fragment.*

class History : Fragment() {

    private  val TAG = "History"
    private lateinit var viewModel: HistoryViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         navController= Navigation.findNavController(view)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        // TODO: Use the ViewModel

        val historyList = arguments?.getParcelable<HistoryList>("historyList")
        Log.d(TAG, "onActivityCreated: ${historyList?.size}")
        if(historyList?.isEmpty()!!) {
                showDialog()
        }
        rv_history.apply {
            layoutManager =  LinearLayoutManager(activity)
             adapter  = HistoryListAdapter(historyList)
        }

    }

    fun showDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.request_error_dialog_title))
            .setMessage(getString(R.string.no_history_title))
            .setPositiveButton(getString(R.string.request_error_dialog_ok_btn)) { dialog, whichButton ->
                dialog.dismiss()
                navController.popBackStack()
            }
            .setCancelable(false)

        val alertDialog = builder.create()
        alertDialog.show()
    }
}