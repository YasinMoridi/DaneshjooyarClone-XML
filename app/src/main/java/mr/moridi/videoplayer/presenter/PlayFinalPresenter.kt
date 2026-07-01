package mr.moridi.videoplayer.presenter

import android.content.Context
import mr.moridi.videoplayer.ext.PlayFinalContract
import androidx.core.content.edit
import kotlin.math.ceil
import kotlin.math.min

class PlayFinalPresenter(private val context: Context) : PlayFinalContract.Presenter {
    //اتصال ویو
    private var view: PlayFinalContract.View? = null
    //متغیر ذخیره کل ثانیه های دیده شده
    private var totalWatchedTime = 0L
    //ذخیره ثانیه های دیده شده
    private var watchedSeconds = mutableSetOf<Int>()
    //اخرین پوزیشن دیده شده
    private var lastPosition = 0L
    //گرفتن شیر پریفنسز
    private val prefs = context.getSharedPreferences("video_prefs", Context.MODE_PRIVATE)
    //متغیر هیدی ویدیو ها
    private var videoId: Int = -1

    //این متد، ویو مورد نظر را به پرزنتر متصل می‌کند. وقتی اکتیویتی یا فرگمنت آماده نمایش شد، این متد فراخوانی می‌شود تا پرزنتر به ویو دسترسی داشته باشد. در اینجا، متغیر view به متغیر عضو view در کلاس پرزنتر تخصیص داده می‌شود.
    override fun attachView(view: PlayFinalContract.View) {
        this.view = view
    }

    //این متد برای جدا کردن ویو از پرزنتر استفاده می‌شود. معمولاً زمانی که اکتیویتی یا فرگمنت از بین می‌رود، این متد فراخوانی می‌شود تا هرگونه ارتباط با ویو قطع شده و از بروز مشکلات حافظه جلوگیری شود. این کار با تنظیم view به null انجام می‌شود.
    override fun detachView() {
        this.view = null
    }

    //این متد هر بار که موقعیت پخش ویدیو تغییر می‌کند فراخوانی می‌شود. ابتدا موقعیت فعلی بر حسب ثانیه محاسبه می‌شود. اگر ثانیه فعلی با آخرین موقعیت ثبت شده متفاوت باشد و قبلاً ثبت نشده باشد، به مجموعه watchedSeconds اضافه می‌شود. سپس، totalWatchedTime به‌روزرسانی شده و با استفاده از saveWatchedSeconds() در SharedPreferences ذخیره می‌شود. در نهایت، پیشرفت پخش ویدیو با استفاده از view?.showPlaybackProgress(getProgressPercentage()) به ویو اطلاع داده می‌شود.
    override fun onUpdateWatchedTime(currentPosition: Long) {
        val currentSecond = (currentPosition / 1000).toInt()
        if (currentSecond != lastPosition.toInt() && !watchedSeconds.contains(currentSecond)) {
            watchedSeconds.add(currentSecond)
            totalWatchedTime = watchedSeconds.size * 1000L
            saveWatchedSeconds()
            view?.showPlaybackProgress(getProgressPercentage())
        }
        lastPosition = currentPosition
    }

    //این متد، درصد پیشرفت مشاهده ویدیو را محاسبه می‌کند. با تقسیم totalWatchedTime بر videoDuration و ضرب نتیجه در 100، درصد پیشرفت محاسبه می‌شود. از ceil برای رند کردن به بالا و از min برای اطمینان از عدم تجاوز از 100 استفاده می‌شود.
    fun getProgressPercentage(): Int {
        val videoDuration = view?.getVideoDuration()?.toDouble() ?: 1.0
        val progress = (totalWatchedTime.toDouble() / videoDuration) * 100
        return min(100.0, ceil(progress)).toInt()
    }

    //ذخیره ثانیه های دیده شده
    private fun saveWatchedSeconds() {
        prefs.edit {
            putStringSet("watched_seconds_$videoId", watchedSeconds.map { it.toString() }.toSet())
        }
    }

    //این متد، درصد پیشرفت مشاهده را در SharedPreferences ذخیره می‌کند. کلید ذخیره‌سازی از ترکیب video_percentage_ با videoId ایجاد می‌شود.
    private fun saveWatchPercentage(percentage: Int) {
        prefs.edit {
            putInt("video_percentage_$videoId", percentage)
        }
    }

    //این متد به هنگام کلیک بر روی دکمه "Next" برای پخش ویدیوی بعدی فراخوانی می‌شود. اگر ویدیوی فعلی آخرین ویدیو باشد، پیغام "شما در حال دیدن آخرین ویدیو این دوره هستید" نشان داده می‌شود.
    override fun onPlayNext() {
        view?.let { view ->
            val videoList = view.getVideoList()
            val currentVideoIndex = view.getCurrentVideoIndex()
            if (currentVideoIndex < videoList.size - 1) {
                val nextVideoIndex = currentVideoIndex + 1
                val nextVideoId = videoList[nextVideoIndex]
                view.updateCurrentVideoIndex(nextVideoIndex)
                onLoadVideo(nextVideoId)
            } else {
                view.showToastMessage("شما در حال دیدن آخرین ویدیو این دوره هستید")
            }
        }
    }

    //این متد به هنگام کلیک بر روی دکمه "Previous" برای پخش ویدیوی قبلی فراخوانی می‌شود. اگر ویدیوی فعلی اولین ویدیو باشد، پیغام "شما در حال دیدن اولین ویدیو این دوره هستید" نشان داده می‌شود.
    override fun onPlayPrevious() {
        view?.let { view ->
            val videoList = view.getVideoList()
            val currentVideoIndex = view.getCurrentVideoIndex()
            if (currentVideoIndex > 0) {
                val previousVideoIndex = currentVideoIndex - 1
                val previousVideoId = videoList[previousVideoIndex]
                view.updateCurrentVideoIndex(previousVideoIndex)
                onLoadVideo(previousVideoId)
            } else {
                view.showToastMessage("شما در حال دیدن اولین ویدیو این دوره هستید")
            }
        }
    }

    //این متد برای بارگذاری ویدیو با شناسه مشخص فراخوانی می‌شود. ابتدا شناسه ویدیو (videoId) ذخیره می‌شود. سپس، متغیرهای totalWatchedTime، watchedSeconds و lastPosition بازنشانی می‌شوند. متد loadWatchedSeconds() برای بازیابی ثانیه‌های دیده شده قبلی فراخوانی می‌شود. در نهایت، ویدیو به ویو ارسال می‌شود تا در پلیر بارگذاری شود.
    override fun onLoadVideo(videoId: Int) {
        this.videoId = videoId
        totalWatchedTime = 0L
        watchedSeconds.clear()
        lastPosition = 0L
        loadWatchedSeconds()

        view?.let { view ->
            view.loadVideoIntoPlayer(videoId)
        }
    }


    //این متد، ثانیه‌های دیده شده را از SharedPreferences بازیابی می‌کند و آن‌ها را در watchedSeconds ذخیره می‌کند. همچنین totalWatchedTime بر اساس تعداد ثانیه‌های دیده شده محاسبه می‌شود.
    private fun loadWatchedSeconds() {
        prefs.getStringSet("watched_seconds_$videoId", emptySet())?.let { set ->
            watchedSeconds = set.map { it.toInt() }.toMutableSet()
            totalWatchedTime = watchedSeconds.size * 1000L
        }
    }

    //این متد در پایان پخش ویدیو فراخوانی می‌شود. اگر درصد پیشرفت مشاهده بیشتر از 99 باشد، مقدار 100 درصد را در SharedPreferences ذخیره می‌کند و متد showCompletion() از ویو را فراخوانی می‌کند.
    override fun onPlaybackCompleted() {
        view?.let { view ->
            val progress = getProgressPercentage()
            if (progress >= 99) {
                saveWatchPercentage(100)
                view.showCompletion()
            }
        }
    }
}
