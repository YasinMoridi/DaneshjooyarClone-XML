package mr.moridi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentHomeHtmlBinding
import mr.moridi.videoplayer.presenter.HtmlPresenter
import mr.moridi.videoplayer.recyclerView.DataProductsHome
import mr.moridi.videoplayer.recyclerView.RecyclerHome
import mr.moridi.videoplayer.view.HtmlView

class HtmlFragment : Fragment(R.layout.fragment_home_html), HtmlView {

    private lateinit var binding: FragmentHomeHtmlBinding
    private lateinit var presenter: HtmlPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeHtmlBinding.inflate(inflater)
        presenter = HtmlPresenter(this)
        presenter.loadProducts()
        return binding.root
    }

    override fun displayProducts(products: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), products)
        binding.recyclerViewHtml.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewHtml.adapter = adapter
    }
}
