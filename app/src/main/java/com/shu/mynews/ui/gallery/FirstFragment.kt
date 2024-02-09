package com.shu.mynews.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.shu.mynews.App
import com.shu.mynews.databinding.FragmentFirstBinding
import com.shu.mynews.ui.adapter.PhotoAdapter
import com.shu.mynews.ui.mainFragment.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FirstViewModel by viewModels {
       App.appComponentUser.firstViewModelFactory()
    }

    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()


        photoAdapter.setOnItemClickListener {
            val bundle = bundleOf("photochka" to it)
            /* findNavController().navigate(
                 R.id.action_FirstFragment_to_SecondFragment,
                 bundle
             )*/
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allPhoto
                    .collect { photoList ->
                        photoAdapter.differ.submitList(photoList)
                    }
            }
        }

        /* binding.fab.setOnClickListener { view ->
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/

    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter()
        binding.recycler.apply {
            adapter = photoAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}