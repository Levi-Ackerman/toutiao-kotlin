/**
 * generate by AAMVVM: https://github.com/HeadingMobile/AAMVVM
 */
package mikasa.ackerman.ttd.host.home

import android.widget.RadioGroup
import androidx.lifecycle.Observer
import mikasa.ackerman.ttd.host.R
import mikasa.ackerman.ttd.host.base.fragment.BaseFragment
import mikasa.ackerman.ttd.host.databinding.HomeFragmentBinding
import mikasa.ackerman.ttd.host.home.viewmodel.HomeViewModel
import mikasa.ackerman.ttd.host.index.IndexFragment
import mikasa.ackerman.ttd.host.video.VideoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

/**
 * description: HomeFragment
 * @see HomeViewModel
 * @date 2020/05/08
 */
class HomeFragment : BaseFragment<HomeFragmentBinding>(), RadioGroup.OnCheckedChangeListener {

    private val mViewModel: HomeViewModel by viewModel()

    private var indexFragmentRef: WeakReference<IndexFragment>? = null
    private var videoFragmentRef: WeakReference<VideoFragment>? = null

    private val indexFragment: IndexFragment
        get() {
            if (indexFragmentRef?.get() == null) {
                indexFragmentRef = WeakReference(IndexFragment())
            }
            return indexFragmentRef!!.get()!!
        }
    private val videoFragment:VideoFragment
        get() {
            if(videoFragmentRef?.get() == null){
                videoFragmentRef = WeakReference(VideoFragment())
            }
            return videoFragmentRef!!.get()!!
        }

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun bindVm() {
        mViewModel.checkedTabIndex.observe(this, Observer { index ->
            if(index == 1){
                childFragmentManager.beginTransaction().replace(R.id.subFragment, videoFragment).commit()
            }else if (index == 0) {
                childFragmentManager.beginTransaction().replace(R.id.subFragment, indexFragment).commit()
            }
        })
        mBinding.let {
            it.vm = mViewModel
            it.presenter = this
        }
    }

    override fun loadData(isRefresh: Boolean) {
        mViewModel.requestDynamicTabs()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        mViewModel.onTabClicked(checkedId)
    }
}
