package com.alabs.socialauth

import android.content.Intent
import androidx.fragment.app.Fragment

interface GoogleSignInApi {

    /**
     * Инициализируем google sign in
     */
    fun setupGoogleSignIn(clientId: String)

    /**
     * Отрабатываем клик на кнопку
     */
    fun onSignInWithGoogleClick(fragment: Fragment)

    /**
     * Отлавливаем результат при onDelegateActivityResult
     */
    fun onGoogleSignInDelegateActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    /**
     * Получаем событие если успешно распознан qrCode
     */
    var onGoogleSignInSuccess: ((String) -> Unit)?

    /**
     * Получаем событие если успешно распознан qrCode
     */
    var onGoogleSignInErrorMessage: ((String) -> Unit)?
}