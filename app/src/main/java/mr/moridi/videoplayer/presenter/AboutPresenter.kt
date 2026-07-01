package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.ext.AboutContract

class AboutPresenter(private val view: AboutContract.View) : AboutContract.Presenter {

    override fun onInstagramClicked() {
        view.openInstagram()
    }

    override fun onYouTubeClicked() {
        view.openYouTube()
    }
}
