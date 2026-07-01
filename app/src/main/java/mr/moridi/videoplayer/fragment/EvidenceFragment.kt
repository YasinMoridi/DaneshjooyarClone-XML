package mr.moridi.videoplayer.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.activity.MainActivity
import mr.moridi.videoplayer.databinding.FragmentEvidenceBinding
import mr.moridi.videoplayer.ext.EvidenceModel
import mr.moridi.videoplayer.presenter.EvidencePresenter
import mr.moridi.videoplayer.view.EvidenceView

class EvidenceFragment : Fragment(R.layout.fragment_evidence), EvidenceView {

    private lateinit var binding: FragmentEvidenceBinding
    private lateinit var presenter: EvidencePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvidenceBinding.inflate(inflater)
        presenter = EvidencePresenter(this, EvidenceModel())

        binding.btnViewCourse.setOnClickListener {
            presenter.onViewCourseButtonClicked()
        }

        presenter.checkVideoStatus(requireContext())

        return binding.root
    }

    override fun showNoEvidenceLayout() {
        binding.FrameLayoutNoEvidence.visibility = View.VISIBLE
        binding.constraintLayoutGiveEvidence.visibility = View.GONE
    }

    override fun showGiveEvidenceLayout() {
        binding.FrameLayoutNoEvidence.visibility = View.GONE
        binding.constraintLayoutGiveEvidence.visibility = View.VISIBLE
    }

    override fun navigateToMainActivity() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}
