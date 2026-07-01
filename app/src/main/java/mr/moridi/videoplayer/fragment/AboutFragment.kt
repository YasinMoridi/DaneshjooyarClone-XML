package mr.moridi.videoplayer.fragment

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentAboutBinding
import mr.moridi.videoplayer.ext.AboutContract
import mr.moridi.videoplayer.presenter.AboutPresenter

class AboutFragment : Fragment(R.layout.fragment_about), AboutContract.View {

    private lateinit var binding: FragmentAboutBinding
    private lateinit var presenter: AboutContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater)
        presenter = AboutPresenter(this)

        setGradientText()

        binding.frameLayoutInstagram.setOnClickListener {
            presenter.onInstagramClicked()
        }
        binding.frameLayoutYotube.setOnClickListener {
            presenter.onYouTubeClicked()
        }

        return binding.root
    }

    override fun setGradientText() {
        val colors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.blueSelectVideo), // رنگ شروع
            ContextCompat.getColor(requireContext(), R.color.endColorGradient)    // رنگ پایان
        )

        // گرادینت
        val textShaderView = LinearGradient(
            0f, 0f, 0f, binding.txtViewInDay.textSize,
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        binding.txtViewInDay.paint.shader = textShaderView

        // گرادینت
        val textShaderCap = LinearGradient(
            0f, 0f, 0f, binding.txtCap.textSize,
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        binding.txtCap.paint.shader = textShaderCap

        // گرادینت
        val textShaderNumberTop = LinearGradient(
            0f, 0f, 0f, binding.txtNumberTop.textSize,
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        binding.txtNumberTop.paint.shader = textShaderNumberTop

        // گرادینت
        val textShaderNumberCurses = LinearGradient(
            0f, 0f, 0f, binding.txtUneversity.textSize,
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        binding.txtUneversity.paint.shader = textShaderNumberCurses
    }

    override fun openInstagram() {
        val uri = Uri.parse("https://www.instagram.com/lrn.ir?igsh=Y2M2YmNodG91cWhy")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")
        try {
            startActivity(intent)
        } catch (e: Exception) {
            // در صورت نبودن اپلیکیشن اینستاگرام، مرورگر باز می‌شود
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/yourusername")
                )
            )
        }
    }

    override fun openYouTube() {
        val uri = Uri.parse("https://www.youtube.com/@alireza-ahmadi")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.youtube")
        try {
            startActivity(intent)
        } catch (e: Exception) {
            // در صورت نبودن اپلیکیشن یوتیوب، مرورگر باز می‌شود
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/yourchannelid")
                )
            )
        }
    }
}
