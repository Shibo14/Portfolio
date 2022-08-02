package com.example.portfolio.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.portfolio.R
import com.example.portfolio.SendActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@SuppressLint("UseCompatLoadingForDrawables")
class FragmentController(
    private val activity: AppCompatActivity,
    private val fragments: List<Fragment>,
) : ViewPager2.OnPageChangeCallback(){

    private var viewPager2: ViewPager2 = activity.findViewById(R.id.pager)
    private var tabLayout: TabLayout

    private var fragmentAdapter: FragmentAdapter =
        FragmentAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)


    init {
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabLayout = activity.findViewById(R.id.tab_layout)
        viewPager2.adapter = fragmentAdapter
        viewPager2.offscreenPageLimit = fragmentAdapter.itemCount

        viewPager2.apply {
            registerOnPageChangeCallback(this@FragmentController)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        tabLayout.setOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 1) {
                    val intent = Intent(activity, SendActivity::class.java)
                    activity.startActivity(intent)
                }
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.to_right_color), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.defColor), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_phone_android_24)
                }

                1 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_add_24)
                }

                2 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_desktop_mac_24)
                }
            }
        }.attach()
    }
}