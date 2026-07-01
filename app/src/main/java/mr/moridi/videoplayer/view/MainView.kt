package mr.moridi.videoplayer.view

import androidx.fragment.app.Fragment

interface MainView {
    fun selectFragment(fragment: Fragment)
    fun updateNavigationColors(homeColor: Int, evidenceColor: Int, aboutColor: Int)
    fun updateNavigationIcons(homeVisible: Boolean, evidenceVisible: Boolean, aboutVisible: Boolean)
}
