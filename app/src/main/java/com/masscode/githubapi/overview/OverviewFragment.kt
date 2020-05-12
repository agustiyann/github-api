package com.masscode.githubapi.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.masscode.githubapi.databinding.FragmentOverviewBinding

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // add recyclerview
        val viewAdapter = ItemAdapter { item -> showDetail(item) }
        binding.recyclerView.adapter = viewAdapter

        viewModel.items.observe(viewLifecycleOwner, Observer { list ->
            viewAdapter.submitList(list)
        })

        return binding.root
    }

    private fun showDetail(username: String) {
        Log.d("debug", "OnClick : $username")
        this.findNavController()
            .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(username))
    }

}
