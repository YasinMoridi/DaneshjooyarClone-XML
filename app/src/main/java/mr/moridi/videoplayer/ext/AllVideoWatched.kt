package mr.moridi.videoplayer.ext

import android.content.Context
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.activity.PlayFinalActivity

object AllVideoWatched {
    fun areAllVideosWatched(context: Context): Boolean {
        val videoList = intArrayOf(
            R.raw.what_is_php,
            R.raw.php_versions,
            R.raw.php_usecase,
            R.raw.pre_requirement
        )
        return PlayFinalActivity.allVideoWatched(context, videoList)
    }
}
