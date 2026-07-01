package mr.moridi.videoplayer.ext

interface PlayFinalContract {
    interface View {
        fun showPlaybackProgress(progress: Int)
        fun showCompletion()
        fun showToastMessage(message: String)
        fun getVideoList(): IntArray
        fun getCurrentVideoIndex(): Int
        fun updateCurrentVideoIndex(index: Int)
        fun getVideoDuration(): Int
        fun loadVideoIntoPlayer(videoId: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onPlayNext()
        fun onPlayPrevious()
        fun onLoadVideo(videoId: Int)
        fun onUpdateWatchedTime(currentPosition: Long)
        fun onPlaybackCompleted()
    }
}
