package mr.moridi.videoplayer.view

import mr.moridi.videoplayer.recyclerView.RecyclerCurseVideos

interface VideosView {
    fun showEndCursesImage()
    fun hideEndCursesImage()
    fun setRecyclerMoreVideos(adapter: RecyclerCurseVideos)
}
