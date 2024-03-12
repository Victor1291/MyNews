package com.shu.mynews.ui.visitor.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shu.mynews.App
import com.shu.mynews.databinding.FragmentVisitorBinding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.BaseListAdapter
import com.shu.mynews.ui.visitor.adapter.ItemTypes
import com.shu.mynews.ui.visitor.adapter.ViewHoldersManager
import com.shu.mynews.ui.visitor.adapter.ViewHoldersManagerImpl
import com.shu.mynews.ui.visitor.viewHolders.CardViewHolder
import com.shu.mynews.ui.visitor.viewHolders.HeaderViewHolder
import com.shu.mynews.ui.visitor.viewHolders.OneLine2ViewHolder
import com.shu.mynews.ui.visitor.viewHolders.TwoStringsViewHolder
import kotlinx.coroutines.launch
import com.shu.mynews.ui.visitor.model.HasStringId
import com.shu.mynews.ui.visitor.model.RecyclerHeader
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


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

    lateinit var viewHoldersManager: ViewHoldersManager

    private val items = mutableListOf<HasStringId>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitorBinding.inflate(inflater, container, false)
        viewHoldersManager =  ViewHoldersManagerImpl().apply {
            registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
            registerViewHolder(ItemTypes.ONE_LINE_STRINGS, OneLine2ViewHolder())
            registerViewHolder(ItemTypes.TWO_STRINGS, TwoStringsViewHolder())
            registerViewHolder(ItemTypes.CARD, CardViewHolder())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       binding.shuffleButton.setOnClickListener { shuffle() }


        recycler = binding.recycller
        val itemsAdapter = BaseListAdapter(AdapterClickListenerById {}, viewHoldersManager)
        binding.recycller.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation))
            adapter = itemsAdapter
        }
        viewModel.news.onEach {
                itemsAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

       // itemsAdapter.submitList(items)
      //  populateRecycler()
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