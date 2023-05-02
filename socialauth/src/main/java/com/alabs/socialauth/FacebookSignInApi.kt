package com.alabs.socialauth

import android.content.Intent
import androidx.fragment.app.Fragment

interface FacebookSignInApi {

    /**
     * Инициализируем facebook sign in
     */
    fun setupFacebookSignIn()

    /**
     * Отрабатываем клик на кнопку
     */
    fun onSignInWithFacebookClick(fragment: Fragment)

    /**
     * Отлавливаем результат при onDelegateActivityResult
     */
    fun onFacebookDelegateActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    /**
     * Получаем событие если успешно распознан qrCode
     */
    var onFacebookSignInSuccess: ((String) -> Unit)?

    /**
     * Получаем событие если успешно распознан qrCode
     */
    var onFacebookSignInErrorMessage: ((String) -> Unit)?
}