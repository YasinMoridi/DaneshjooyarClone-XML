package mr.moridi.videoplayer.view

import mr.moridi.videoplayer.recyclerView.DataProductsHome

interface HtmlView {
    fun displayProducts(products: Array<DataProductsHome>)
}
