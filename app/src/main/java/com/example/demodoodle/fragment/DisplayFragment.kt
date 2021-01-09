package com.example.demodoodle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demodoodle.R
import com.example.demodoodle.ViewModel.DisplayViewModel

class DisplayFragment : Fragment() {


    companion object {
        fun newInstance(counter: Int) = DisplayFragment(

        )
    }

    private lateinit var viewModel: DisplayViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}