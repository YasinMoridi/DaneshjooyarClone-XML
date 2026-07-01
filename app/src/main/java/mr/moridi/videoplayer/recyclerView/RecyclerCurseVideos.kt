package mr.moridi.videoplayer.recyclerView

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.activity.PlayFinalActivity
import mr.moridi.videoplayer.databinding.ListRecyclerInformationBinding

class RecyclerCurseVideos(
    private val context: Activity,
    private val curseVideos: Array<DataCurseVideos>
) : RecyclerView.Adapter<RecyclerCurseVideos.ProductOrderViewHolder>() {

    private val listVideo = listOf(
        R.raw.what_is_php,
        R.raw.php_versions,
        R.raw.php_usecase,
        R.raw.pre_requirement
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOrderViewHolder {
        val binding = ListRecyclerInformationBinding.inflate(context.layoutInflater, parent, false)
        return ProductOrderViewHolder(binding)
    }

    override fun getItemCount(): Int = curseVideos.size

    override fun onBindViewHolder(
        holder: ProductOrderViewHolder,
        position: Int
    ) {
        holder.setData(curseVideos[position], position)
    }

    inner class ProductOrderViewHolder(private val binding: ListRecyclerInformationBinding) :

        RecyclerView.ViewHolder(binding.root) {

        fun setData(product: DataCurseVideos, position: Int) {
            binding.txtRecyclerInformation.text = product.title
            binding.imgRecyclerInformation.setImageResource(product.imgAddress)

            // دریافت درصد تماشای ویدیو
            val videoId = listVideo[position]
            val watchPercentage = PlayFinalActivity.getVideoWatchPercentage(context, videoId)

            // تغییر بک گراند بر اساس درصد تماشای ویدیو
            if (watchPercentage >= 99.5) {
                binding.root.setBackgroundResource(R.drawable.background_seen_video)
                val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0, 25, 0, 0)  // افزودن فاصله 5dp به بالا
                binding.root.layoutParams = layoutParams
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, PlayFinalActivity::class.java)
                intent.putExtra("videoList", listVideo.toIntArray())
                intent.putExtra("currentVideoIndex", position)
                intent.putExtra("VIDEO_ID", listVideo[position])
                intent.putExtra("ITEM_POSITION", position)
                intent.putExtra("ITEM_TITLE", product.title)
                // افزودن عنوان‌ها به اینتنت
                intent.putExtra("videoTitles", curseVideos.map { it.title }.toTypedArray())
                context.startActivity(intent)
                Log.d("ttttdtt", product.title)
            }


        }

    }

}
