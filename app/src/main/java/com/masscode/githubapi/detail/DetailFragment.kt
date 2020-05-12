package com.masscode.githubapi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masscode.githubapi.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // accept args
        val argsUsername = DetailFragmentArgs.fromBundle(requireArguments()).username
        val vmFactory = DetailViewModelFactory(argsUsername)

        binding.viewModel = ViewModelProvider(this, vmFactory).get(DetailViewModel::class.java)

        return binding.root
    }

}
