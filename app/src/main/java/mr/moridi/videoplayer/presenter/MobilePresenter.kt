package mr.moridi.videoplayer.presenter

import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.ext.MobileContract
import mr.moridi.videoplayer.recyclerView.DataProductsHome

class MobilePresenter(private val view: MobileContract.View) : MobileContract.Presenter {
    private val dataMobile = arrayOf(
        DataProductsHome("دوره آموزش Git و GitHub جامع با نسخه 2024", R.drawable.img_curses_git),
        DataProductsHome("جامع ترین دوره آموزش برنامه نویسی اندروید (۱۵۰ ساعت با پشتیبانی ۲۴ ساعته)", R.drawable.img_curses_android),
        DataProductsHome("دوره طلایی آموزش بازی سازی با یونیتی عملی و پروژه محور", R.drawable.img_curses_unity),
        DataProductsHome("دوره ی مستر تدوینگر آموزش پریمیر با بیش از ۲۰ پروژه عملی ویژه بازار کار", R.drawable.img_curses_premere)
    )

    override fun loadData() {
        // در اینجا ممکن است داده‌ها از یک منبع خارجی بارگذاری شوند.
        // در اینجا داده‌ها به طور مستقیم به View ارسال می‌شود.
        view.showData(dataMobile)
    }
}
