package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private lateinit var playRepository: PlayRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val plays = arrayListOf<Play>()
    private val playAdapter = PlayAdapter(plays)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playRepository = PlayRepository(requireContext())
        getHistory()
        initViews()

        btnBack.setOnClickListener{findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)}
        btnClear.setOnClickListener{removeHistory()}
    }

    fun initViews(){
        rvHistory.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = playAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    fun getHistory(){
        mainScope.launch {
            val playList = withContext(Dispatchers.IO){
                playRepository.getAllPlays()
            }
            this@HistoryFragment.plays.clear()
            this@HistoryFragment.plays.addAll(playList)
            this@HistoryFragment.playAdapter.notifyDataSetChanged()
        }

    }

    fun removeHistory(){
        mainScope.launch{
            withContext(Dispatchers.IO){
                playRepository.deleteAllProducts()
            }
        }
        getHistory()
    }

}