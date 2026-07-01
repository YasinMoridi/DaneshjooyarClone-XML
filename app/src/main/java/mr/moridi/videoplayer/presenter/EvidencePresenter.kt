package mr.moridi.videoplayer.presenter

import android.content.Context
import mr.moridi.videoplayer.ext.EvidenceModel
import mr.moridi.videoplayer.view.EvidenceView

class EvidencePresenter(private val view: EvidenceView, private val model: EvidenceModel) {
    fun checkVideoStatus(context: Context) {
        val allWatched = model.areAllVideosWatched(context)
        if (allWatched) {
            view.showGiveEvidenceLayout()
        } else {
            view.showNoEvidenceLayout()
        }
    }

    fun onViewCourseButtonClicked() {
        view.navigateToMainActivity()
    }
}
