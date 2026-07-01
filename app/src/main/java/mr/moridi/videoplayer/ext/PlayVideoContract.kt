package mr.moridi.videoplayer.ext

import android.net.Uri
import androidx.fragment.app.Fragment

interface PlayVideoContract {
    interface View {
        fun showVideo(uri: Uri)
        fun updateFragment(fragment: Fragment)
        fun restoreVideoPosition(position: Int)
        fun showFragmentSelectionAnimation(isInformationSelected: Boolean)
    }

    interface Presenter {
        fun onVideoReady()
        fun onSaveVideoPosition(position: Int)
        fun onFragmentSelected(fragmentType: FragmentType)
        fun onActivityCreated()
    }

    enum class FragmentType {
        INFORMATION, VIDEOS
    }
}


