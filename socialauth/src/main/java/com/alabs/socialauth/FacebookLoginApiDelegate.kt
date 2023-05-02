package com.alabs.socialauth

import android.content.Intent
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class FacebookLoginApiDelegate : FacebookSignInApi {

    override var onFacebookSignInSuccess: ((String) -> Unit)? = null
    override var onFacebookSignInErrorMessage: ((String) -> Unit)? = null

    private lateinit var callbackManager: CallbackManager

    override fun setupFacebookSignIn() {
        LoginManager.getInstance().logOut()
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                onFacebookSignInSuccess?.invoke(result?.accessToken?.token.toString())
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {
                onFacebookSignInErrorMessage?.invoke(error?.message.toString())
            }
        })
    }

    override fun onSignInWithFacebookClick(fragment: Fragment) {
        LoginManager.getInstance().logInWithReadPermissions(fragment, listOf("public_profile"))
    }

    override fun onFacebookDelegateActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}