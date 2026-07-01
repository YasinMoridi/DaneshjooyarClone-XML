package mr.moridi.videoplayer.view

interface AuthenticationView {
    fun updateButtonState(enabled: Boolean)
    fun startMainActivity()
    fun showError()
    fun moveToNextField(currentIndex:Int)
    fun moveToPreviousField(currentIndex:Int)
}
