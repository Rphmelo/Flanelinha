// codigo vindo do tutorial do firebase site
package br.com.flanelinha.app.messaging

FirebaseInstanceId.getInstance().instanceId
.addOnCompleteListener(OnCompleteListener { task ->
    if (!task.isSuccessful) {
        Log.w(TAG, "getInstanceId failed", task.exception)
        return@OnCompleteListener
    }

    // Get new Instance ID token
    val token = task.result?.token

    // Log and toast
    val msg = getString(R.string.msg_token_fmt, token)
    Log.d(TAG, msg)
    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
})

/**
 * Called if InstanceID token is updated. This may occur if the security of
 * the previous token had been compromised. Note that this is called when the InstanceID token
 * is initially generated so this is where you would retrieve the token.
 */

override fun onNewToken(token: String?) {
    Log.d(TAG, "Refreshed token: $token")

    // If you want to send messages to this application instance or
    // manage this apps subscriptions on the server side, send the
    // Instance ID token to your app server.
    sendRegistrationToServer(token)
}

FirebaseMessaging.getInstance().isAutoInitEnabled = true
