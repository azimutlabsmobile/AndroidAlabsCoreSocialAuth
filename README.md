<h1 align="center">Привет, с Вами Android команда, компании <a href="https://alabs.team/" target="_blank">alabs.team</a>
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

Эта библиотека помогает авторизоваться с помощью социальных сетей
=====================
Начнем!

Прежде всего, рекомендую Вам ознакомиться с документацией для авторизации(интеграции) через ```GoogleSignIn``` и ```FacebookLogin```.

##### Официальная документация: [GoogleSignIn](https://developers.google.com/identity/sign-in/android/start-integrating?hl=ru), [FacebookLogin](https://developers.facebook.com/docs/facebook-login/android/)

Применение
---
Добавьте следующую библиотеку в раздел зависимостей файла ```build.gradle``` уровня приложения вашего проекта:
---
Ссылка на актуальную версию библиотеки: [version](https://github.com/azimutlabsmobile/AndroidAlabsCoreSocialAuth/releases)
```groovy
dependencies {
	        implementation 'com.github.azimutlabsmobile:AndroidAlabsCoreSocialAuth:$version'
	}
```

Пример использования:
-----------------------------------

Для ```FacebookLogin```, так же нужно инициализировать в Application классе, пример:
```kotlin
  override fun onCreate() {
    super.onCreate()
    FacebookSdk.sdkInitialize(applicationContext)
    AppEventsLogger.activateApp(this)
  }
```

В ```AndroidManifest.xml``` уровня ```app``` добавляем нужные activity и мета теги внутри тега ```<application>```, пример:
```xml
<activity android:name="com.facebook.FacebookActivity"
            android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation" />

        <activity
            android:name="com.facebook.CustomTabActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="@string/fb_login_protocol_scheme" />
            </intent-filter>
        </activity>

        <meta-data
            android:name="com.google.android.gms.auth.CLIENT_ID"
            android:value="@string/google_sign_in_server_client_id" />

        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id"/>

        <meta-data
            android:name="com.facebook.sdk.ClientToken"
            android:value="@string/facebook_client_token"/>
```

Далее для GoogleSignIn:
```kotlin
  class MainFragment : Fragment(),
    GoogleSignInApi by GoogleSignInApiDelegate(getApplicationContext()) {
    }
```

и для FacebookLogin:
```kotlin
  class MainFragment : Fragment(),
    FacebookSignInApi by FacebookLoginApiDelegate() {
    }
```

Затем инициализируем и добавляемм обработчики на кнопки в ```OnViewCreated()``` методе:
```kotlin
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signGoogleButton.setOnClickListener {
            onSignInWithGoogleClick(this@MainFragment)
        }

        signFacebookButton.setOnClickListener {
            onSignInWithFacebookClick(this@MainFragment)
        }

        setupGoogleSignIn(resources?.getString(R.string.google_sign_in_server_client_id).orEmpty())
        
        setupFacebookSignIn()
    }
```

Реализовываем колбеки:
```kotlin
private fun setupDelegateCallBacks() {
        onFacebookSignInSuccess = {
//            Логика получения токена и запрос на авторизацию
        }
        onGoogleSignInSuccess = {
//            Логика получения токена и запрос на авторизацию
        }
        onGoogleSignInErrorMessage = {
//            Показываем ошибку
        }
        onFacebookSignInErrorMessage = {
//            Показываем ошибку
        }
    }
```

Перезаписываем метод ```onActivityResult()```:
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onGoogleSignInDelegateActivityResult(requestCode, resultCode, data) // GoogleSignIn
        onFacebookDelegateActivityResult(requestCode, resultCode, data) // FacebookLogin
        super.onActivityResult(requestCode, resultCode, data)
    }
```

Удачи!!!
