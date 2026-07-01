package mr.moridi.videoplayer.view

import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.recyclerView.DataProductsTop

interface HomeView {
    fun setupRecyclerView(dataTop: Array<DataProductsTop>)
    fun navigateToFragment(fragment: Fragment, product: DataProductsTop)
}
