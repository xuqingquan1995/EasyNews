package top.xuqingquan.easynews.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import top.xuqingquan.easynews.databinding.FragmentNewsListBinding
import top.xuqingquan.easynews.model.entity.NetworkStatus
import top.xuqingquan.easynews.utils.InjectData
import top.xuqingquan.easynews.view.adapter.NewsAdapter
import top.xuqingquan.easynews.viewModel.NewsListViewModel

class NewsListFragment : Fragment() {

    private lateinit var type: String
    private var position: Int = -1
    private lateinit var viewModel: NewsListViewModel
    private lateinit var binding: FragmentNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(TYPE)!!
            position = it.getInt(POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        viewModel =
                ViewModelProviders.of(this, InjectData.provideNewsListViewModelFactory())[NewsListViewModel::class.java]
        viewModel.setChannel(type)
        initAdapter()
        initSwipeToRefresh()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = NewsAdapter{
            viewModel.retry()
        }
        binding.newsList.adapter = adapter
        viewModel.newsList.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initSwipeToRefresh() {
        viewModel.refreshState.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = it == NetworkStatus.RUNNING
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    companion object {

        private const val TYPE = "type"
        private const val POSITION = "position"

        private val TAG by lazy { NewsListFragment::class.java.simpleName }

        private lateinit var fragment:NewsListFragment

        @JvmStatic
        fun newInstance(bundle: Bundle):NewsListFragment {
            fragment=NewsListFragment()
            return fragment.apply {
                arguments = bundle
            }
        }
    }
}
