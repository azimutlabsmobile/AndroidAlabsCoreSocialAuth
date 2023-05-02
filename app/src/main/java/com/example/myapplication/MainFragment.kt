package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.alabs.socialauth.FacebookLoginApiDelegate
import com.alabs.socialauth.FacebookSignInApi
import com.alabs.socialauth.GoogleSignInApi
import com.alabs.socialauth.GoogleSignInApiDelegate
import com.facebook.FacebookSdk.getApplicationContext

class MainFragment : Fragment(),
    GoogleSignInApi by GoogleSignInApiDelegate(getApplicationContext()),
    FacebookSignInApi by FacebookLoginApiDelegate() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signGoogleButton = view.findViewById<RelativeLayout>(R.id.signInWithGoogleButton)
        val signFacebookButton = view.findViewById<RelativeLayout>(R.id.signInWithFacebookButton)

        signGoogleButton.setOnClickListener {
            signInGoogle()
        }

        signFacebookButton.setOnClickListener {
            signInFacebook()
        }

        setupGoogleSignIn(context?.resources?.getString(R.string.google_sign_in_server_client_id).orEmpty())
        setupFacebookSignIn()

        setupDelegateCallBacks()
    }

    private fun signInGoogle() {
        onSignInWithGoogleClick(this@MainFragment)
    }

    private fun signInFacebook() {
        onSignInWithFacebookClick(this@MainFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onDelegateActivityResult(requestCode, resultCode, data)
        onFacebookDelegateActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupDelegateCallBacks() {
        onFacebookSignInSuccess = {
//            viewModel.onSocialNetworkSignInSuccess(token = it, isGoogleAuth = false)
        }
        onGoogleSignInSuccess = {
//            viewModel.onSocialNetworkSignInSuccess(token = it, isGoogleAuth = true)
        }
        showSignInErrorMessage = {
//            errorMessage(it)
        }
        onFacebookSignInErrorMessage = {
//            errorMessage(it)
        }
    }
}