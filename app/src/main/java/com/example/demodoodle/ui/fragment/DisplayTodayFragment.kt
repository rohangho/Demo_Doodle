package com.example.demodoodle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demodoodle.R
import com.example.demodoodle.pojos.TodayBaseResponse
import com.example.demodoodle.pojos.TodayResponse
import com.example.demodoodle.ui.adapter.DisplayerAdapter
import com.example.demodoodle.viewModel.SharedViewModel
import com.example.demodoodle.viewModel.TodayViewModel
import com.example.sampleweather.pojos.Coordinates

class DisplayTodayFragment(var coordinates: Coordinates) : Fragment() {


    private lateinit var viewModel: TodayViewModel
    private lateinit var listDisplayer: RecyclerView
    private lateinit var displayerAdapter: DisplayerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var sharedViewModel: SharedViewModel
    private var todayList: ArrayList<TodayResponse> = ArrayList()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.display_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listDisplayer = view.findViewById(R.id.recyclerDisplayer)
        linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        displayerAdapter = DisplayerAdapter(todayList)
        listDisplayer.adapter = displayerAdapter
        listDisplayer.layoutManager = linearLayoutManager

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TodayViewModel::class.java)
        viewModel = ViewModelProvider(this).get(TodayViewModel::class.java)
        viewModel.setCityId(coordinates)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.getRefreshStatus().observe(requireActivity(), {
            viewModel.setCityId(coordinates)
        })

        viewModel.getTodayWeather().observe(viewLifecycleOwner, {

            updateList(it)
        })

    }

    private fun updateList(it: TodayBaseResponse?) {
        val todayRespose = TodayResponse(it?.main?.temp, it?.main?.tempMin, it?.main?.tempMax)
        todayList.add(todayRespose)
        displayerAdapter.notifyDataSetChanged()

    }

}