package mr.moridi.videoplayer.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import mr.moridi.videoplayer.ext.SplashModel


class SplashModelImpl(private val context: Context) : SplashModel {
    override fun isUserLoggedIn(): Boolean {
        val sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val check = sharedPreferences.getString("check", "")
        return check == "yes"
    }
    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkInfo) ?: return false

        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
