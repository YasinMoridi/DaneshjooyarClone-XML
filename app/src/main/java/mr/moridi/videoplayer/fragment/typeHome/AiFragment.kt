package mr.moridi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentHomeAiBinding
import mr.moridi.videoplayer.presenter.AiPresenter
import mr.moridi.videoplayer.recyclerView.DataProductsHome
import mr.moridi.videoplayer.recyclerView.RecyclerHome
import mr.moridi.videoplayer.view.AiView


class AiFragment : Fragment(R.layout.fragment_home_ai), AiView {

    private lateinit var binding: FragmentHomeAiBinding
    private lateinit var presenter: AiPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAiBinding.inflate(inflater)
        presenter = AiPresenter(this)
        presenter.loadProducts()
        return binding.root
    }

    override fun displayProducts(products: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), products)
        binding.recyclerViewAi.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewAi.adapter = adapter
    }
}
