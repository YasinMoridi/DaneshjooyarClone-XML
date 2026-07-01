package mr.moridi.videoplayer.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import mr.moridi.videoplayer.databinding.ActivityPlayFinalBinding
import mr.moridi.videoplayer.ext.DialogHelper
import mr.moridi.videoplayer.ext.PlayFinalContract
import mr.moridi.videoplayer.presenter.PlayFinalPresenter

class PlayFinalActivity : AppCompatActivity(), PlayFinalContract.View {
    //اتصال پرزنتر
    private lateinit var presenter: PlayFinalPresenter

    //تعریف بایندینگ
    private lateinit var binding: ActivityPlayFinalBinding

    //آرایه ای برای ذخیره لیست ویدیو ها
    private lateinit var videoList: IntArray

    // متغیر عضو برای لیست عناوین ویدیوها
    private lateinit var videoTitles: Array<String>

    //متغیری برای ذخیره ایندکس فعلی
    private var currentVideoIndex: Int = 0

    //این متغیر برای ذخیره مدت زمان کل ویدیو استفاده می‌شود. مقدار آن در لحظه آماده شدن ویدیو تنظیم می‌شود و می‌تواند برای محاسبه درصد پیشرفت ویدیو و انجام وظایف دیگر استفاده شود.
    private var videoDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //پر کردن بایندینگ
        binding = ActivityPlayFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //اتصال نهایی پرزنتر
        presenter = PlayFinalPresenter(this)
        presenter.attachView(this)

        //برگشت به اکتیویتی قبلی و بروزرسانی آن در صورتی که کاربر روی دکمه برگشت گوشی زد
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToPreviousActivity()
            }
        })

        //برگشت به اکتیویتی قبلی و بروزرسانی آن در صورتی که کاربر روی دکمه برگشت خود اپلیکیشن زد
        binding.icBack.setOnClickListener {
            navigateToPreviousActivity()
        }

        //باز کردن دیالوگ و ارسال متن های کاربر به ربات نوتفیکیتور با کمک یک آبجکت که DialogHelper انجام میشود ( به ابجکت بروید تا کامنت گذاری ان را بخوانید)
        binding.supportTicket.setOnClickListener {
            DialogHelper.showSupportTicketDialog(this)
        }

        //گرفتن ویدیو لیست و ایندکس ان و تایتل ها
        videoList = intent.getIntArrayExtra("videoList") ?: intArrayOf()
        currentVideoIndex = intent.getIntExtra("currentVideoIndex", 0)
        videoTitles = intent.getStringArrayExtra("videoTitles") ?: arrayOf()

        //اجرای فانگشن زیر
        titlePosition()

        //رفتن به ویدیو بعدی در صورت کلیک روی باتن ان
        binding.btnNext.setOnClickListener {
            presenter.onPlayNext()
        }

        //رفتن به ویدیو قبلی در صورت کلیک روی باتن ان
        binding.btnPrevious.setOnClickListener {
            presenter.onPlayPrevious()
        }

        //اتصال سیکبار
        binding.seekBar.setOnTouchListener { _, _ -> true }

        //شرط اینکه اگر ویدیو لیست خالی نبود ویدیو مد نظر را لود کند
        if (videoList.isNotEmpty()) {
            presenter.onLoadVideo(videoList[currentVideoIndex])
        }

        //محاسبه درصد با هندلر (دادن یک ثانیه تاخیر)
        val handler = Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    if (binding.videoViewFinal.isPlaying) {
                        presenter.onUpdateWatchedTime(binding.videoViewFinal.currentPosition.toLong())
                    }
                } catch (e: Exception) {
                    Log.e("EXCEPTION_UPDATE_TIMER", e.toString())
                }
                handler.postDelayed(this, 1000)
            }
        }, 0)
    }

    //فانگشن ست کردن تایتل ویدیو ها
    private fun titlePosition() {
        // دریافت عنوان بر اساس ایندکس فعلی ویدیو
        val titlePosition = videoTitles.getOrNull(currentVideoIndex)
        //ست کردن تایتل مد نظر
        binding.txtTitleVideo.text = titlePosition ?: "عنوان نامشخص"
    }

    //اپدیت ایندکس ویدیو ها (برای وقای به ویدیو بعدی یا قبلی میرویم)
    override fun updateCurrentVideoIndex(index: Int) {
        currentVideoIndex = index
        titlePosition()  // به‌روزرسانی عنوان بعد از تغییر ایندکس ویدیو
    }

    //مقدار تکست درصد و پروگرس سیکبار را ست میکنیم
    override fun showPlaybackProgress(progress: Int) {
        binding.text.text = "درصد پخش شده: $progress%"
        binding.seekBar.progress = progress
    }

    //این فانگشن وقتی فراخوانی میشود که ویدیو 100 درصد کامل دیده شود
    override fun showCompletion() {
        binding.text.text = "درصد پخش شده: 100%"
        binding.seekBar.progress = 100
    }

    //نشان دادن پیام به کاربر برای وقتی درون ویدیو اول یا اخر است و دکمه بعدی ی قبلی کار نمیکند
    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //فانگشن چرخه حیاط ( در صورت اتمام اکتیویتی)
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    //فانگشن برگردادن لیست ویدیو ها
    override fun getVideoList(): IntArray {
        return videoList
    }

    //فانگشن برگردادن ایندکس ویدیو ها
    override fun getCurrentVideoIndex(): Int {
        return currentVideoIndex
    }

    //فانگشن برگردادن ثانیه های دیده شده ویدیو ها
    override fun getVideoDuration(): Int {
        return videoDuration
    }

    //فانگشن لود کردن هر ویدیو
    override fun loadVideoIntoPlayer(videoId: Int) {

        //ست کردن url هر ویدیو با ایدی ویدیو
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + videoId)
        binding.videoViewFinal.setVideoURI(videoUri)
        //ساخت کنترلر برای ویدیو(دکمه های عقب جلو کردن و استپ ویدیو)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoViewFinal)
        binding.videoViewFinal.setMediaController(mediaController)

        //ین لیسنر به ویدیو ویو اختصاص داده شده است که وقتی ویدیو آماده پخش شد، فراخوانی می‌شود. در اینجا، مدت زمان ویدیو در متغیر videoDuration ذخیره می‌شود و پخش ویدیو شروع می‌شود.
        binding.videoViewFinal.setOnPreparedListener {
            videoDuration = it.duration
            it.start()
        }

        //این لیسنر زمانی که پخش ویدیو به پایان می‌رسد، فراخوانی می‌شود. در اینجا، تابع onPlaybackCompleted در پرزنتر فراخوانی می‌شود تا وظایف لازم پس از اتمام پخش ویدیو انجام شود
        binding.videoViewFinal.setOnCompletionListener {
            presenter.onPlaybackCompleted()
        }
    }

    //ذخیره ویدیو برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //ذخیره ویدیو لیست برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
        outState.putIntArray("videoList", videoList)
        //ذخیره ایندکس ویدیو برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
        outState.putInt("currentVideoIndex", currentVideoIndex)
        //این کد مقدار videoDuration را در شیء outState ذخیره می‌کند. این کار به منظور ذخیره‌سازی وضعیت فعلی ویدیو در هنگام چرخاندن صفحه یا هر موقعیت دیگری که اکتیویتی دوباره ساخته می‌شود انجام می‌شود.
        outState.putInt("videoDuration", videoDuration)
    }

    //گرفتن و ست کردن ویدیو برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //گرفتن و ست کردن ویدیو لیست برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
        videoList = savedInstanceState.getIntArray("videoList") ?: intArrayOf()
        //گرفتن و ست کردن ایندکس ویدیو برای وقتی که اکتیویتی از اول ساخته میشود(مثل چرخاندن صفحه هنگام دیدن ویدیو)
        currentVideoIndex = savedInstanceState.getInt("currentVideoIndex", 0)
        //این خط کد، مقدار videoDuration را از شیء savedInstanceState بازیابی می‌کند. اگر مقدار videoDuration موجود نباشد، مقدار پیش‌فرض 0 را برمی‌گرداند. این کار برای حفظ وضعیت برنامه هنگام تغییرات مانند چرخاندن صفحه یا بستن و باز کردن مجدد برنامه انجام می‌شود
        videoDuration = savedInstanceState.getInt("videoDuration", 0)
    }

    //فانگشن برگشتن به اکتیویتی قبلی و بدست اوردن اطلاعات جدید اکتیویتی
    private fun navigateToPreviousActivity() {
        //ذخیره اینتنت اکتیویتی بعدی
        val intent = Intent(this, PlayVideoActivity::class.java)
        //این دستور، دو فلگ FLAG_ACTIVITY_CLEAR_TOP و FLAG_ACTIVITY_NEW_TASK را به اینتنت اضافه می‌کند. FLAG_ACTIVITY_CLEAR_TOP تمام اکتیویتی‌های بالای اکتیویتی هدف را از استک حذف می‌کند، در حالی که FLAG_ACTIVITY_NEW_TASK اکتیویتی هدف را به عنوان یک وظیفه جدید شروع می‌کند، یا اگر آن وظیفه وجود داشته باشد، آن را به صدر استک می‌آورد
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        //رفتن به اکتیویتی قبلی
        startActivity(intent)
        //هنگام رفتن به اکتیویتی قبلی این اکتیویتی را برای همیشه ببند تا با زدن دکمه برگشت به ان برنگردیم
        finish()
    }

    //این متد، یک Handler برای بروزرسانی رابط کاربری با اطلاعات مربوط به پخش ویدیو راه‌اندازی می‌کند. در هر ثانیه، وضعیت پخش ویدیو بررسی شده و درصد پیشرفت پخش به‌روزرسانی می‌شود. اگر ویدیو در حال پخش باشد، درصد پخش شده نمایش داده شده و پروگرس بار به‌روزرسانی می‌شود. همچنین، Listener برای پایان پخش ویدیو تنظیم شده است که بعد از اتمام ویدیو، فانکشن onPlaybackCompleted در پرزنتر را فراخوانی می‌کند.
    private fun setupHandlers() {
        val handler = Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    if (binding.videoViewFinal.isPlaying) {
                        presenter.onUpdateWatchedTime(binding.videoViewFinal.currentPosition.toLong())
                        val progress = presenter.getProgressPercentage()
                        binding.text.text = "درصد پخش شده: ${progress}%"
                        binding.seekBar.progress = progress
                    }
                } catch (e: Exception) {
                    Log.e("EXCEPTION_UPDATE_TIMER", e.toString())
                }
                handler.postDelayed(this, 1000)
            }
        }, 0)

        binding.videoViewFinal.setOnCompletionListener {
            presenter.onPlaybackCompleted()
        }
    }

    // فانگشن بالا را هر لحظه در این اکتیویتی بروزرسانی کن
    override fun onResume() {
        super.onResume()
        setupHandlers()
        presenter.onLoadVideo(videoList[currentVideoIndex])
    }

    //هنگام pause ویدیوdetachView را فراخوانی کن
    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    //استاتیک کردن دو فانگشن زیر
    companion object {

        //وقتی که تمام ویدیو ها نشان داده شد این فانگشن true را برمیگرداند
        fun allVideoWatched(context: Context, videoList: IntArray): Boolean {
            //گرفتن شیرپریفنسز
            val prefs = context.getSharedPreferences("video_prefs", MODE_PRIVATE)
            //حلقه تکرار که همه ویدیو هارو چک کند
            for (videoId in videoList) {
                //گرفتن ایدی ویدیو
                val watchedPercentage = prefs.getInt("video_percentage_$videoId", 0)
                //اگر درصد پخش شده یکی از ایدی ها کمتر از 100 بود false را برگردان
                if (watchedPercentage < 100) {
                    return false
                }
            }
            //اگر هیچوقت if بالا درست نباشد ادامه فانگشن اجرا میشود و true برمیگردد
            return true
        }

        //این متد از SharedPreferences برای بازیابی درصد دیده‌شده‌ی یک ویدیو استفاده می‌کند. با استفاده از videoId، درصد دیده‌شده ویدیو از SharedPreferences استخراج شده و برگردانده می‌شود.
        fun getVideoWatchPercentage(context: Context, videoId: Int): Int {
            val prefs = context.getSharedPreferences("video_prefs", MODE_PRIVATE)
            return prefs.getInt("video_percentage_$videoId", 0)
        }
    }
}
