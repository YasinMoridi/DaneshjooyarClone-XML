package mr.moridi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentHomeWebsiteBinding
import mr.moridi.videoplayer.ext.WebsiteContract
import mr.moridi.videoplayer.presenter.WebsitePresenter
import mr.moridi.videoplayer.recyclerView.DataProductsHome
import mr.moridi.videoplayer.recyclerView.RecyclerHome

class WebsiteFragment : Fragment(R.layout.fragment_home_website), WebsiteContract.View {
    private lateinit var binding: FragmentHomeWebsiteBinding
    private lateinit var presenter: WebsiteContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeWebsiteBinding.inflate(inflater)
        presenter = WebsitePresenter(this)
        presenter.loadData()
        return binding.root
    }

    override fun showData(data: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), data)
        binding.recyclerViewWebsite.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewWebsite.adapter = adapter
    }
}
