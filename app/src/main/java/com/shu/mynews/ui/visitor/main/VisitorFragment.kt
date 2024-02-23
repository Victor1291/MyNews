package com.shu.mynews.ui.visitor.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shu.mynews.App
import com.shu.mynews.R
import com.shu.mynews.databinding.FragmentNewsBinding
import com.shu.mynews.databinding.FragmentVisitorBinding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.BaseListAdapter
import com.shu.mynews.ui.visitor.adapter.ViewHoldersManager
import kotlinx.coroutines.launch
import ru.alexmaryin.recycleronvisitor.data.HasStringId
import ru.alexmaryin.recycleronvisitor.data.ui_models.RecyclerHeader
import javax.inject.Inject

class VisitorFragment : Fragment() {

    companion object {
        fun newInstance() = VisitorFragment()
    }

    private var _binding: FragmentVisitorBinding? = null
    private val binding get() = _binding!!


    private val viewModel: VisitorViewModel by viewModels {
        App.appComponentUser.visitorViewModelFactory()
    }

    private lateinit var recycler: RecyclerView


    @Inject lateinit var viewHoldersManager: ViewHoldersManager

    private val items = mutableListOf<HasStringId>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       binding.shuffleButton.setOnClickListener { shuffle() }

        recycler = binding.recycller
        val itemsAdapter = BaseListAdapter(AdapterClickListenerById {}, viewHoldersManager)
        itemsAdapter.submitList(items)
        binding.recycller.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation))
            adapter = itemsAdapter
        }
        populateRecycler()
    }

    private fun populateRecycler() {
        lifecycleScope.launch {
            viewModel.getItems().flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    items.add(it)
                }
        }
    }

    private fun shuffle() {
        (recycler.adapter as BaseListAdapter).submitList(items.apply {
            removeAll { it is RecyclerHeader }
            shuffle()
        })
        recycler.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}