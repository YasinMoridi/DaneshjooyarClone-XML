package mr.moridi.videoplayer.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mr.moridi.videoplayer.ext.LoginContract
import mr.moridi.videoplayer.presenter.LoginPresenter
import mr.moridi.videoplayer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)

        binding.imgClose.setOnClickListener {
            presenter.onClearButtonClicked()
        }

        // محدود کردن ورودی به فقط اعداد و حداکثر 11 کاراکتر
        val numberFilter = InputFilter { source, start, end, dest, dstart, dend ->
            if (source.toString().matches("\\d*".toRegex())) {
                val newLength = dest.length + (end - start) - (dend - dstart)
                if (newLength <= 11) {
                    null // معتبر است
                } else {
                    "" // نامعتبر است
                }
            } else {
                "" // نامعتبر است
            }
        }
        // اعمال فیلتر به EditText
        binding.edtInputPhone.filters = arrayOf(numberFilter)

        binding.edtInputPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.onPhoneNumberChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.edtInputPhone.text.toString()
            presenter.onLoginButtonClicked(phoneNumber)
        }
    }

    override fun showError() {
        binding.txtErrorPhone.visibility = View.VISIBLE
    }

    override fun hideError() {
        binding.txtErrorPhone.visibility = View.INVISIBLE
    }

    override fun clearInput() {
        binding.edtInputPhone.text = null
    }

    override fun enableLoginButton(enable: Boolean) {
        binding.btnLogin.alpha = if (enable) 1f else 0.5f
        binding.btnLogin.isEnabled = enable
    }

    override fun navigateToAuthentication(phoneNumber: String) {
        val intent = Intent(this, AuthenticationLoginActivity::class.java)
        intent.putExtra("NUMBER_USER", phoneNumber)
        startActivity(intent)
    }

    override fun updatePhoneNumber(phoneNumber: String) {
        binding.edtInputPhone.setText(phoneNumber)
        binding.edtInputPhone.setSelection(phoneNumber.length)
    }
}
