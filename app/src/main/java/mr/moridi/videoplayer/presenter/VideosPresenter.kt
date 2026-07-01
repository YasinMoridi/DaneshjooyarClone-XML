package mr.moridi.videoplayer.presenter

import android.app.Activity
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.ext.AllVideoWatched
import mr.moridi.videoplayer.recyclerView.DataCurseVideos
import mr.moridi.videoplayer.recyclerView.RecyclerCurseVideos
import mr.moridi.videoplayer.view.VideosView

class VideosPresenter(private val view: VideosView, private val activity: Activity) {

    private val dataMoreVideos = arrayOf(
        DataCurseVideos("php چیست", R.drawable.img_curses_git),
        DataCurseVideos("ورژن های php", R.drawable.img_curses_git),
        DataCurseVideos("php usecase", R.drawable.img_curses_git),
        DataCurseVideos("نیازمندی های php", R.drawable.img_curses_git)
    )

    fun checkAllVideosWatched() {
        val allWatched = AllVideoWatched.areAllVideosWatched(activity)
        if (allWatched) {
            view.showEndCursesImage()
        } else {
            view.hideEndCursesImage()
        }
    }

    fun setupRecyclerMoreVideos() {
        val adapter = RecyclerCurseVideos(activity, dataMoreVideos)
        view.setRecyclerMoreVideos(adapter)
    }
}
