package mr.moridi.videoplayer.presenter

import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.fragment.typeHome.AiFragment
import mr.moridi.videoplayer.fragment.typeHome.HtmlFragment
import mr.moridi.videoplayer.fragment.typeHome.MobileFragment
import mr.moridi.videoplayer.fragment.typeHome.WebsiteFragment
import mr.moridi.videoplayer.fragment.typeHome.WordpressFragment
import mr.moridi.videoplayer.model.HomeModel
import mr.moridi.videoplayer.recyclerView.DataProductsTop
import mr.moridi.videoplayer.view.HomeView

class HomePresenter(private val view: HomeView, private val model: HomeModel) {
    fun loadData() {
        val dataTop = model.getDataTop()
        view.setupRecyclerView(dataTop)
        selectDefaultFragment(dataTop)
    }

    private fun selectDefaultFragment(dataTop: Array<DataProductsTop>) {
        val defaultProduct = dataTop.first { it.title == "وبسایت" }
        view.navigateToFragment(createFragment(defaultProduct), defaultProduct)
    }

    private fun createFragment(product: DataProductsTop): Fragment {
        return when (product.title) {
            "وبسایت" -> WebsiteFragment()
            "موبایل" -> MobileFragment()
            "Ai" -> AiFragment()
            "HTML" -> HtmlFragment()
            else -> WordpressFragment()
        }
    }
}
