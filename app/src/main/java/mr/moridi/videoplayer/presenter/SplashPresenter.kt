package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.ext.SplashModel
import mr.moridi.videoplayer.ext.SplashView

class SplashPresenter(private val view: SplashView, private val model: SplashModel) {
    fun checkLoginStatus() {
        /*
        if (model.isConnected()) {
            if (model.isUserLoggedIn()) {
                view.navigateToMain()
            } else {
                view.navigateToLogin()
            }
        } else {
            view.showNoInternetDialog()
        }
        */
        // فیک کردن لاگین و بررسی اینترنت
        if (model.isUserLoggedIn()) {
            view.navigateToMain()
        } else {
            view.navigateToLogin()
        }
    }
}
