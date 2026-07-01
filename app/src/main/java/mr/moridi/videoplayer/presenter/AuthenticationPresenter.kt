package mr.moridi.videoplayer.presenter


interface AuthenticationPresenter {
    fun onCodeTextChanged(code1: String, code2: String, code3: String, code4: String, code5: String)
    fun onLoginButtonClicked(code1: String, code2: String, code3: String, code4: String, code5: String)
}



