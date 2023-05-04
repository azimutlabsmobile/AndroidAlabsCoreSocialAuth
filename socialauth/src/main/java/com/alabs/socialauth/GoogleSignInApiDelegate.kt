package com.alabs.socialauth

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class GoogleSignInApiDelegate(
    private val context: Context
) : GoogleSignInApi {

    companion object {
        const val RC_SIGN_IN = 1002
    }

    override var onGoogleSignInSuccess: ((String) -> Unit)? = null
    override var onGoogleSignInErrorMessage: ((String) -> Unit)? = null

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun setupGoogleSignIn(clientId: String) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
            .requestServerAuthCode(clientId)
            .requestIdToken(clientId)
            .requestEmail()
            .requestProfile()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        googleSignInClient.signOut()
    }

    override fun onSignInWithGoogleClick(fragment: Fragment) {
        val signInIntent = googleSignInClient.signInIntent
        fragment.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onGoogleSignInDelegateActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            onGoogleSignInSuccess?.invoke(account?.idToken.toString())
            // Signed in successfully, show authenticated UI.
            // You can use the account to access Google services on behalf of the user.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            onGoogleSignInErrorMessage?.invoke(e.message.toString())
        }
    }
}