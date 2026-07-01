package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.ext.PlayVideoContract
import mr.moridi.videoplayer.fragment.informationOrVideos.InformationFragment
import mr.moridi.videoplayer.fragment.informationOrVideos.VideosFragment
import android.content.Context
import android.net.Uri
import mr.moridi.videoplayer.R

class PlayVideoPresenter(
    private val view: PlayVideoContract.View,
    private val context: Context
) : PlayVideoContract.Presenter {

    private var videoPosition: Int = 0

    override fun onVideoReady() {
        val videoPath = Uri.parse("android.resource://${context.packageName}/${R.raw.what_algo}")
        view.showVideo(videoPath)
    }

    override fun onSaveVideoPosition(position: Int) {
        videoPosition = position
    }

    override fun onFragmentSelected(fragmentType: PlayVideoContract.FragmentType) {
        val fragment = when (fragmentType) {
            PlayVideoContract.FragmentType.INFORMATION -> InformationFragment()
            PlayVideoContract.FragmentType.VIDEOS -> VideosFragment()
        }
        view.updateFragment(fragment)
        view.showFragmentSelectionAnimation(fragmentType == PlayVideoContract.FragmentType.INFORMATION)
    }

    override fun onActivityCreated() {
        onFragmentSelected(PlayVideoContract.FragmentType.INFORMATION)
    }
}

