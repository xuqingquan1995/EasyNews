package top.xuqingquan.easynews.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import top.xuqingquan.easynews.R
import top.xuqingquan.easynews.databinding.ActivityEasyNewsBinding

class EasyNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEasyNewsBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    lateinit var searchBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_easy_news)
        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.toolbar)
        navController = Navigation.findNavController(this, R.id.news_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        binding.navigationView.setupWithNavController(navController)
        searchBox = binding.searchBox
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
