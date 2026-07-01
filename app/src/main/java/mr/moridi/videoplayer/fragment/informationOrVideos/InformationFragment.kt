package mr.moridi.videoplayer.fragment.informationOrVideos

import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentPlayVideoInformationBinding
import mr.moridi.videoplayer.ext.InformationView
import mr.moridi.videoplayer.presenter.InformationPresenter

class InformationFragment : Fragment(R.layout.fragment_play_video_information), InformationView {

    private lateinit var binding: FragmentPlayVideoInformationBinding
    private lateinit var presenter: InformationPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayVideoInformationBinding.inflate(inflater)
        presenter = InformationPresenter(this, requireContext())

        presenter.setupTextShaders(
            binding.txtAboutTeacher.textSize,
            binding.txtAboutProfile.textSize,
            binding.txtAboutClock.textSize,
            binding.txtAboutUniversity.textSize
        )

        return binding.root
    }

    override fun applyTextShaderTeacher(shader: Shader) {
        binding.txtAboutTeacher.paint.shader = shader
    }

    override fun applyTextShaderStudent(shader: Shader) {
        binding.txtAboutProfile.paint.shader = shader
    }

    override fun applyTextShaderClock(shader: Shader) {
        binding.txtAboutClock.paint.shader = shader
    }

    override fun applyTextShaderUniversity(shader: Shader) {
        binding.txtAboutUniversity.paint.shader = shader
    }
}
