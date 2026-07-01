package mr.moridi.videoplayer.presenter

import android.content.Context
import android.graphics.Color
import mr.moridi.videoplayer.fragment.AboutFragment
import mr.moridi.videoplayer.fragment.EvidenceFragment
import mr.moridi.videoplayer.fragment.HomeFragment
import mr.moridi.videoplayer.view.MainView

class MainPresenter(private val view: MainView) {

    fun checkUserLogin() {
        val sharedPreferences = (view as Context).getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("check", "yes")
        editor.apply()
    }

    fun onHomeClicked() {
        view.selectFragment(HomeFragment())
        view.updateNavigationColors(Color.parseColor("#000080"), Color.parseColor("#757575"), Color.parseColor("#757575"))
        view.updateNavigationIcons(homeVisible = true, evidenceVisible = false, aboutVisible = false)
    }

    fun onEvidenceClicked() {
        view.selectFragment(EvidenceFragment())
        view.updateNavigationColors(Color.parseColor("#757575"), Color.parseColor("#000080"), Color.parseColor("#757575"))
        view.updateNavigationIcons(homeVisible = false, evidenceVisible = true, aboutVisible = false)
    }

    fun onAboutClicked() {
        view.selectFragment(AboutFragment())
        view.updateNavigationColors(Color.parseColor("#757575"), Color.parseColor("#757575"), Color.parseColor("#000080"))
        view.updateNavigationIcons(homeVisible = false, evidenceVisible = false, aboutVisible = true)
    }
}