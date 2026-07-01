package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.recyclerView.DataProductsHome
import mr.moridi.videoplayer.view.AiView


class AiPresenter(private val view: AiView) {

    fun loadProducts() {
        val dataWebsite = arrayOf(
            DataProductsHome(
                "جامع ترین دوره آموزش برنامه نویسی اندروید (۱۵۰ ساعت با پشتیبانی ۲۴ ساعته)",
                R.drawable.img_curses_android
            ),
            DataProductsHome(
                "دوره آموزش Git و GitHub جامع با نسخه 2024",
                R.drawable.img_curses_git
            ),
            DataProductsHome(
                " دوره ی مستر تدوینگر آموزش پریمیر با بیش از ۲۰ پروژه عملی ویژه بازار کار",
                R.drawable.img_curses_premere
            ),
            DataProductsHome(
                "دوره طلایی آموزش بازی سازی با یونیتی عملی و پروژه محور",
                R.drawable.img_curses_unity
            )
        )
        view.displayProducts(dataWebsite)
    }
}
