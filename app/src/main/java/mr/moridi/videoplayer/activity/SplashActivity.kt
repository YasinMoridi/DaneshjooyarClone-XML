package mr.moridi.videoplayer.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.NoInternetBinding
import mr.moridi.videoplayer.model.SplashModelImpl
import mr.moridi.videoplayer.presenter.SplashPresenter
import mr.moridi.videoplayer.ext.SplashView

class SplashActivity : AppCompatActivity(), SplashView {

    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val model = SplashModelImpl(this)
        presenter = SplashPresenter(this, model)
        presenter.checkLoginStatus()
    }

    override fun navigateToMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun navigateToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 1000)
    }

    override fun showNoInternetDialog() {
        val dialog = BottomSheetDialog(this)
        val view = NoInternetBinding.inflate(layoutInflater)

        view.btnOnCreate.setOnClickListener { recreate() }
        view.btnSetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }

        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.show()
    }
}
