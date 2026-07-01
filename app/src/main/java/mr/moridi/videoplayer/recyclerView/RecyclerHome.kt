package mr.moridi.videoplayer.recyclerView

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.activity.PlayVideoActivity
import mr.moridi.videoplayer.databinding.ListRecyclerHomeBinding

class RecyclerHome(
    private val activity: Activity,
    private val products: Array<DataProductsHome>
) : RecyclerView.Adapter<RecyclerHome.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListRecyclerHomeBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.binding.txtRecyclerHome.text = product.title
        holder.binding.imageViewRecyclerHome.setImageResource(product.imgAddress)
        holder.itemView.setOnClickListener {
            activity.startActivity(Intent(activity, PlayVideoActivity::class.java))
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(val binding: ListRecyclerHomeBinding) : RecyclerView.ViewHolder(binding.root)
}

