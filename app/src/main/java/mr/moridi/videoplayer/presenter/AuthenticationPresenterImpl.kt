package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.view.AuthenticationView

class AuthenticationPresenterImpl(private val view: AuthenticationView) : AuthenticationPresenter {

    override fun onCodeTextChanged(code1: String, code2: String, code3: String, code4: String, code5: String) {
        /*
        val allFilled = code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty() && code4.isNotEmpty() && code5.isNotEmpty()
        view.updateButtonState(allFilled)
        */
        view.updateButtonState(true) // دکمه همیشه فعال باشد
    }

    override fun onLoginButtonClicked(code1: String, code2: String, code3: String, code4: String, code5: String) {
        /*
        if ((code1 == "1" || code1 == "۱") &&
            (code2 == "2" || code2 == "۲") &&
            (code3 == "3" || code3 == "۳") &&
            (code4 == "4" || code4 == "۴") &&
            (code5 == "5" || code5 == "۵")
        ) {
            view.startMainActivity()
        } else {
            view.showError()
        }
        */
        // لاگین فیک شد
        view.startMainActivity()
    }

    fun onTextChanged(index: Int, text: String) {
        if (text.length == 1) {
            view.moveToNextField(index)
        } else if (text.isEmpty()) {
            view.moveToPreviousField(index)
        }
    }
}




