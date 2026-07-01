package mr.moridi.videoplayer.ext

import mr.moridi.videoplayer.recyclerView.DataProductsHome

interface MobileContract {
    interface View {
        fun showData(data: Array<DataProductsHome>)
    }

    interface Presenter {
        fun loadData()
    }
}
