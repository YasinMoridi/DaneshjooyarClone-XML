package mr.moridi.videoplayer.fragment.informationOrVideos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.FragmentPlayVideoVideosBinding
import mr.moridi.videoplayer.presenter.VideosPresenter
import mr.moridi.videoplayer.recyclerView.RecyclerCurseVideos
import mr.moridi.videoplayer.view.VideosView

class VideosFragment : Fragment(R.layout.fragment_play_video_videos), VideosView {

    private lateinit var binding: FragmentPlayVideoVideosBinding
    private lateinit var presenter: VideosPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayVideoVideosBinding.inflate(inflater)
        presenter = VideosPresenter(this, requireActivity())

        presenter.setupRecyclerMoreVideos()
        presenter.checkAllVideosWatched()

        return binding.root
    }

    override fun showEndCursesImage() {
        binding.imgEndCurses.visibility = View.VISIBLE
    }

    override fun hideEndCursesImage() {
        binding.imgEndCurses.visibility = View.GONE
    }

    override fun setRecyclerMoreVideos(adapter: RecyclerCurseVideos) {
        binding.recyclerViewMoreVideos.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewMoreVideos.adapter = adapter
    }
}
