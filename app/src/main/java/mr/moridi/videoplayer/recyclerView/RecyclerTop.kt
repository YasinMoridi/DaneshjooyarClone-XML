package mr.moridi.videoplayer.recyclerView

import android.app.Activity
import android.util.TypedValue
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.ListRecyclerTopBinding
import mr.moridi.videoplayer.ext.OnItemClickListener

class RecyclerTop(
    private val context: Activity,
    private val productsTop: Array<DataProductsTop>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerTop.ProductTopViewHolder>() {

    private var selectedItemPosition: Int = productsTop.indexOfFirst { it.title == "وبسایت" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTopViewHolder {
        val binding = ListRecyclerTopBinding.inflate(context.layoutInflater, parent, false)
        return ProductTopViewHolder(binding)
    }

    override fun getItemCount(): Int = productsTop.size

    override fun onBindViewHolder(holder: ProductTopViewHolder, position: Int) {
        holder.setData(productsTop[position], position == selectedItemPosition)
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedItemPosition)
            selectedItemPosition = holder.adapterPosition
            notifyItemChanged(selectedItemPosition)
            itemClickListener.onItemClick(productsTop[holder.adapterPosition])
        }
    }

    inner class ProductTopViewHolder(private val binding: ListRecyclerTopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(product: DataProductsTop, isSelected: Boolean) {
            binding.txtRecyclerTop.text = product.title
            binding.imageViewRecyclerTop.setImageResource(product.imgAddress)

            val widthInDp = if (isSelected) 100 else 147
            val widthInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                widthInDp.toFloat(),
                context.resources.displayMetrics
            ).toInt()

            val params = binding.root.layoutParams
            params.width = widthInPx
            binding.root.layoutParams = params

            val backgroundResource = if (isSelected) {
                R.drawable.background_select_recycler_top
            } else {
                R.drawable.background_un_select_recycler_top
            }

            binding.frameLayoutRecyclerTop.setBackgroundResource(backgroundResource)

            val textColor = if (isSelected) {
                ContextCompat.getColor(context, R.color.white)
            } else {
                ContextCompat.getColor(context, R.color.black)
            }

            binding.txtRecyclerTop.setTextColor(textColor)
        }
    }
}
