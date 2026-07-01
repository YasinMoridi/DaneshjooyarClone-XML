package mr.moridi.videoplayer.ext

import android.content.Context

// EvidenceModel.kt
class EvidenceModel {
    fun areAllVideosWatched(context: Context): Boolean {
        return AllVideoWatched.areAllVideosWatched(context)
    }
}

