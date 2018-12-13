package top.xuqingquan.easynews.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout

import top.xuqingquan.easynews.databinding.FragmentNewsBinding
import top.xuqingquan.easynews.utils.InjectData
import top.xuqingquan.easynews.view.adapter.FragmentAdapter
import top.xuqingquan.easynews.viewModel.ChannelViewModel

class NewsFragment : Fragment() {

    private val TAG by lazy { NewsFragment::class.java.simpleName }

    private lateinit var binding: FragmentNewsBinding
    private lateinit var model: ChannelViewModel
    private lateinit var searchBox: TextView
    private lateinit var adapter: FragmentAdapter

    private var isClickOnce = false
    private var mSelectedPage = 0
    private val DOUBLE_PRESS_INTERVAL = 500L
    private var mLastPressTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        initView()
        val factory = InjectData.provideChannleViewModelFactory(activity!!.applicationContext)
        model = ViewModelProviders.of(this, factory)[ChannelViewModel::class.java]
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val selectedPosition = tab?.position
                if (selectedPosition == mSelectedPage) {
                    val currentTime = System.currentTimeMillis()
                    if (isClickOnce && currentTime - mLastPressTime <= DOUBLE_PRESS_INTERVAL) {
                        isClickOnce = false
                        //TODO doubleClick
                    } else {
                        isClickOnce = true
                        mLastPressTime = currentTime
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                isClickOnce = false
                mSelectedPage = tab!!.position
            }
        })
        initViewPager()
        return binding.root
    }

    private fun initView() {
        searchBox = (activity as EasyNewsActivity).searchBox
        searchBox.visibility = View.VISIBLE
        searchBox.setOnClickListener {
            val searchView = NewsFragmentDirections.actionNewsToSearchView()
            findNavController().navigate(searchView)
        }
    }

    private fun initViewPager() {
        val title = arrayListOf<String>()
        model.showChannelList.observe(viewLifecycleOwner, Observer {
            it.forEach { channel ->
                title.add(channel.name)
            }
            binding.viewpager.offscreenPageLimit = 2
            adapter = FragmentAdapter(childFragmentManager, title)
            binding.viewpager.adapter = adapter
            binding.tabs.setupWithViewPager(binding.viewpager)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchBox.visibility = View.GONE
    }

}
