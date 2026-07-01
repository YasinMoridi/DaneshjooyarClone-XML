package mr.moridi.videoplayer.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.databinding.ActivityMainBinding
import mr.moridi.videoplayer.ext.DialogHelper
import mr.moridi.videoplayer.fragment.HomeFragment
import mr.moridi.videoplayer.presenter.MainPresenter
import mr.moridi.videoplayer.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MainPresenter(this)
        presenter.checkUserLogin()

        selectFragment(HomeFragment())

        binding.frameLayoutHomeFragment.setOnClickListener {
            presenter.onHomeClicked()
        }

        binding.frameLayoutEvidenceFragment.setOnClickListener {
            presenter.onEvidenceClicked()
        }

        binding.frameLayoutAboutFragment.setOnClickListener {
            presenter.onAboutClicked()
        }

        binding.supportTicket.setOnClickListener {
            DialogHelper.showSupportTicketDialog(this)
        }
    }

    override fun selectFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewMain, fragment)
            .commit()
    }

    override fun updateNavigationColors(homeColor: Int, evidenceColor: Int, aboutColor: Int) {
        binding.txtHomeBottomNav.setTextColor(homeColor)
        binding.txtEvidenceBottomNav.setTextColor(evidenceColor)
        binding.txtAboutBottomNav.setTextColor(aboutColor)
    }

    override fun updateNavigationIcons(
        homeVisible: Boolean,
        evidenceVisible: Boolean,
        aboutVisible: Boolean
    ) {
        binding.icHomeBlueBottomNav.visibility = if (homeVisible) View.VISIBLE else View.INVISIBLE
        binding.icHomeBottomNav.visibility = if (homeVisible) View.INVISIBLE else View.VISIBLE
        binding.icEvidenceBlueBottomNav.visibility =
            if (evidenceVisible) View.VISIBLE else View.INVISIBLE
        binding.icEvidenceBottomNav.visibility =
            if (evidenceVisible) View.INVISIBLE else View.VISIBLE
        binding.icAboutBlueBottomNav.visibility = if (aboutVisible) View.VISIBLE else View.INVISIBLE
        binding.icAboutBottomNav.visibility = if (aboutVisible) View.INVISIBLE else View.VISIBLE
    }
}