package mr.moridi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentHomeWordpressBinding
import mr.moridi.videoplayer.ext.WordpressContract
import mr.moridi.videoplayer.presenter.WordpressPresenter
import mr.moridi.videoplayer.recyclerView.DataProductsHome
import mr.moridi.videoplayer.recyclerView.RecyclerHome

class WordpressFragment : Fragment(R.layout.fragment_home_wordpress), WordpressContract.View {
    private lateinit var binding: FragmentHomeWordpressBinding
    private lateinit var presenter: WordpressContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeWordpressBinding.inflate(inflater)
        presenter = WordpressPresenter(this)
        presenter.loadData()
        return binding.root
    }

    override fun showData(data: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), data)
        binding.recyclerViewWordpress.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewWordpress.adapter = adapter
    }
}
