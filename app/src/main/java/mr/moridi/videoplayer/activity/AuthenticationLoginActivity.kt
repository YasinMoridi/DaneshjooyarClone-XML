package mr.moridi.videoplayer.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mr.moridi.videoplayer.databinding.ActivityAuthenticationLoginBinding
import mr.moridi.videoplayer.view.AuthenticationView
import java.util.Locale
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.presenter.AuthenticationPresenter
import mr.moridi.videoplayer.presenter.AuthenticationPresenterImpl

class AuthenticationLoginActivity : AppCompatActivity(), AuthenticationView {
    private lateinit var binding: ActivityAuthenticationLoginBinding
    private lateinit var presenter: AuthenticationPresenter
    private lateinit var editTexts: List<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = AuthenticationPresenterImpl(this)

        binding.edtInputCode1.requestFocus()

        setupTextWatchers()
        setupButtonListeners()
        countdownText(binding.txtTimer)

        val numberPhone = intent.getStringExtra("NUMBER_USER")
        binding.txtSendCodeToPhone.text = "کد تایید برای شماره موبایل $numberPhone ارسال شد."

        editTexts = listOf(
            binding.edtInputCode1,
            binding.edtInputCode2,
            binding.edtInputCode3,
            binding.edtInputCode4,
            binding.edtInputCode5
        )

        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    (presenter as AuthenticationPresenterImpl).onTextChanged(index, s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                presenter.onCodeTextChanged(
                    binding.edtInputCode1.text.toString(),
                    binding.edtInputCode2.text.toString(),
                    binding.edtInputCode3.text.toString(),
                    binding.edtInputCode4.text.toString(),
                    binding.edtInputCode5.text.toString()
                )
            }
        }

        binding.edtInputCode1.addTextChangedListener(textWatcher)
        binding.edtInputCode2.addTextChangedListener(textWatcher)
        binding.edtInputCode3.addTextChangedListener(textWatcher)
        binding.edtInputCode4.addTextChangedListener(textWatcher)
        binding.edtInputCode5.addTextChangedListener(textWatcher)
    }

    private fun setupButtonListeners() {
        binding.btnLoginCode.setOnClickListener {
            presenter.onLoginButtonClicked(
                binding.edtInputCode1.text.toString(),
                binding.edtInputCode2.text.toString(),
                binding.edtInputCode3.text.toString(),
                binding.edtInputCode4.text.toString(),
                binding.edtInputCode5.text.toString()
            )
        }

        binding.imgEditNumber.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.txtEditNumber.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun updateButtonState(enabled: Boolean) {
        binding.btnLoginCode.alpha = if (enabled) 1f else 0.5f
        binding.btnLoginCode.isEnabled = enabled
    }

    override fun startMainActivity() {
        binding.edtInputCode.setBackgroundColor(getColor(R.color.white))
        binding.txtErrorCode.visibility = View.INVISIBLE
        binding.edtInputCode1.setBackgroundResource(R.drawable.background_green_ok_code_login)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.edtInputCode2.setBackgroundResource(R.drawable.background_green_ok_code_login)
        }, 100)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.edtInputCode3.setBackgroundResource(R.drawable.background_green_ok_code_login)
        }, 200)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.edtInputCode4.setBackgroundResource(R.drawable.background_green_ok_code_login)
        }, 300)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.edtInputCode5.setBackgroundResource(R.drawable.background_green_ok_code_login)
        }, 400)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@AuthenticationLoginActivity, MainActivity::class.java))
            finish()
        }, 500)
    }

    override fun showError() {
        binding.edtInputCode.setBackgroundResource(R.drawable.back_code_error_login)
        binding.txtErrorCode.visibility = View.VISIBLE
    }


    override fun moveToNextField(currentIndex: Int) {
        if (currentIndex < editTexts.size - 1) {
            editTexts[currentIndex + 1].requestFocus()
        }
    }

    override fun moveToPreviousField(currentIndex: Int) {
        if (currentIndex > 0) {
            editTexts[currentIndex - 1].requestFocus()
        }
    }

    private fun countdownText(textView: TextView) {
        val countDownTimer = object : CountDownTimer(2 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / (60 * 1000)
                val seconds = (millisUntilFinished % (60 * 1000)) / 1000
                textView.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                textView.text = "00:00"
            }
        }
        countDownTimer.start()
    }
}

