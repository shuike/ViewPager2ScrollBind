package com.skit.demo

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.roundToInt

class ViewPagerBinder(
    private val headViewPager: ViewPager2,
    private val contentViewPager: ViewPager2
) {
    fun bind() {
        contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                (headViewPager.getChildAt(0) as? RecyclerView)?.let { rv ->
                    (rv.layoutManager as? LinearLayoutManager)?.apply {
                        val width = this.getChildAt(0)?.measuredWidth ?: 0
                        scrollToPositionWithOffset(
                            position,
                            (-positionOffset * width).roundToInt()
                        )
                    }
                }
            }
        })
    }
}