package mr.moridi.videoplayer.ext

interface LoginContract {
    interface View {
        fun showError()
        fun hideError()
        fun clearInput()
        fun enableLoginButton(enable: Boolean)
        fun navigateToAuthentication(phoneNumber: String)
        fun updatePhoneNumber(phoneNumber: String) // اضافه کردن متد جدید
    }

    interface Presenter {
        fun onClearButtonClicked()
        fun onPhoneNumberChanged(phoneNumber: String)
        fun onLoginButtonClicked(phoneNumber: String) // اضافه کردن متد جدید
    }
}
