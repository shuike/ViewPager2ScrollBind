package com.skit.demo

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import com.skit.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.headViewPager.apply {
//            isUserInputEnabled = false
            adapter = HeadAdapter(this@MainActivity)
            setPageTransformer(MarginPageTransformer(12.dp))
            (getChildAt(0) as? RecyclerView)?.also {
                val padding = 40.dp
                it.setPadding(padding, 0, padding, 0)
                it.clipToPadding = false
            }
        }
        binding.contentViewPager.apply {
            adapter = ContentAdapter(this@MainActivity)
        }

        // 上面的ViewPager2绑定到下面的滑动变化回调
        ViewPagerBinder(binding.headViewPager, binding.contentViewPager).bind()
        // 下面的ViewPager2绑定到上面的滑动变化回调
        ViewPagerBinder(binding.contentViewPager, binding.headViewPager).bind()
    }

    class ContentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return ContentFragment()
        }
    }

    class HeadAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return HeadFragment()
        }

    }
}

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()