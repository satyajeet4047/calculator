package com.example.calculator.ui.authentication

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.calculator.R
import com.example.calculator.databinding.LoginFragmentBinding
import com.example.githubapp.util.RequestStatus
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/*
        LoginFragment Activity
 */
class LoginFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var  login : LoginFragmentBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)
    }

    private lateinit var navController : NavController
    private var alertDialog : AlertDialog? = null
    private var progressDialog : AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        login  = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false)
        login.viewmodel = viewModel
        login.lifecycleOwner = viewLifecycleOwner
        return  login.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setObservables()
    }

    private fun setObservables() {

        viewModel.getRequestStatus().observe(viewLifecycleOwner, Observer { status ->

            when(status){
                RequestStatus.IN_PROGRESS -> showLoadingDialog()
                RequestStatus.INVALID_INPUT -> showErrorToast()
                RequestStatus.SUCCESS -> onLoginSuccess()
                RequestStatus.FAILURE -> onLoginFailure()
            }
        })

    }




    private fun onLoginFailure() {

        dismissLoadingDialog()
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.request_error_dialog_title))
                .setMessage(getString(R.string.request_error_dialog_message))
                .setPositiveButton(getString(R.string.request_error_dialog_ok_btn)) { dialog, whichButton ->
                    dialog.dismiss()
                }
                .setCancelable(false)

            alertDialog = builder.create()
            alertDialog?.show()


    }

    private fun dismissLoadingDialog() {
        progressDialog?.dismiss()
    }

    private fun onLoginSuccess() {
        dismissLoadingDialog()
        navController.popBackStack()
    }

    private fun showErrorToast() {
        login.userEmailError.error = "Email Can not be empty"
        login.userPasswordError.error = "Password can not be empty"
    }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.progress_dialog)
        progressDialog = builder.create()
        progressDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
        progressDialog?.dismiss()
    }

}