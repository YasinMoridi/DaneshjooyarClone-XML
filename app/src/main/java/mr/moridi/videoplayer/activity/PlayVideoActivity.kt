package mr.moridi.videoplayer.activity

import android.graphics.Typeface
import mr.moridi.videoplayer.ext.PlayVideoContract
import mr.moridi.videoplayer.presenter.PlayVideoPresenter
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.ActivityPlayVideoBinding
import mr.moridi.videoplayer.ext.DialogHelper

class PlayVideoActivity : AppCompatActivity(), PlayVideoContract.View {

    private lateinit var binding: ActivityPlayVideoBinding
    private lateinit var playVideoSample: VideoView
    private lateinit var presenter: PlayVideoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = PlayVideoPresenter(this, this)

        binding.supportTicket.setOnClickListener {
            DialogHelper.showSupportTicketDialog(this)
        }

        playVideoSample = binding.playVideoSample
        presenter.onVideoReady()

        binding.cardViewInformation.setOnClickListener {
            presenter.onFragmentSelected(PlayVideoContract.FragmentType.INFORMATION)
        }

        binding.icBack.setOnClickListener {
            finish()
        }

        binding.cardViewMoreVideo.setOnClickListener {
            presenter.onFragmentSelected(PlayVideoContract.FragmentType.VIDEOS)
        }

        presenter.onActivityCreated()
    }

    override fun showVideo(uri: Uri) {
        playVideoSample.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(playVideoSample)
        playVideoSample.setMediaController(mediaController)
    }

    override fun updateFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewInformationOrVideos, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveVideoPosition(playVideoSample.currentPosition)
        outState.putInt("position", playVideoSample.currentPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val position = savedInstanceState.getInt("position")
        restoreVideoPosition(position)
    }

    override fun restoreVideoPosition(position: Int) {
        playVideoSample.seekTo(position)
    }

    override fun showFragmentSelectionAnimation(isInformationSelected: Boolean) {
        if (isInformationSelected) {
            binding.frameLayoutInformation.setBackgroundColor(getColor(R.color.blueSelectVideo))
            binding.frameLayoutMoreVideo.setBackgroundColor(getColor(R.color.white))
            binding.frameLayoutShadowInformation.setBackgroundResource(R.drawable.shadow_select_video)
            binding.frameLayoutShadowMoreVideo.setBackgroundColor(getColor(R.color.white))
            binding.txtInformation.setTypeface(null,Typeface.BOLD)
            binding.txtMoreVideo.setTypeface(null,Typeface.NORMAL)
        } else {
            binding.frameLayoutMoreVideo.setBackgroundColor(getColor(R.color.blueSelectVideo))
            binding.frameLayoutInformation.setBackgroundColor(getColor(R.color.white))
            binding.frameLayoutShadowMoreVideo.setBackgroundResource(R.drawable.shadow_select_video)
            binding.frameLayoutShadowInformation.setBackgroundColor(getColor(R.color.white))
            binding.txtMoreVideo.setTypeface(null,Typeface.BOLD)
            binding.txtInformation.setTypeface(null,Typeface.NORMAL)
        }
    }
}

