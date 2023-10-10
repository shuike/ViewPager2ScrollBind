package com.skit.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skit.demo.databinding.FragmentHeadBinding

class HeadFragment : Fragment() {
    private lateinit var binding: FragmentHeadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadBinding.inflate(inflater, container, false)
        return binding.root
    }
}