package top.xuqingquan.easynews.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import top.xuqingquan.easynews.view.NewsListFragment
import top.xuqingquan.easynews.view.NewsListFragmentArgs

/**
 * Created by 许清泉 on 2018/11/24 00:05
 */
class FragmentAdapter(
    fm: FragmentManager,
    private val title: ArrayList<String>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundle =
            NewsListFragmentArgs.Builder().setPosition(position).setType(title[position]).build().toBundle()
        return NewsListFragment.newInstance(bundle)
    }

    override fun getCount(): Int = title.size

    override fun getPageTitle(position: Int): CharSequence? =title[position]

}