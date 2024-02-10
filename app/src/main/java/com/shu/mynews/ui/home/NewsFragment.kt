package com.shu.mynews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shu.indianews.main.NewsListAdapter
import com.shu.mynews.App
import com.shu.mynews.databinding.FragmentNewsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val TAG = "MainFragment"

class NewsFragment : Fragment(), ChooseCategoriesViewGroup.CustomViewClickListener {


    private val viewModel: NewsViewModel by viewModels {
        App.appComponentUser.newsViewModelFactory()
    }

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var newsAdapter: NewsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        /*val destinationsInBackStack =
            findNavController().backQueue.joinToString("\n") { dest ->
                dest.destination.displayName
            }
        Log.d("BackStack", "----------------------------------\n$destinationsInBackStack")*/
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.d(TAG, "onViewCreated: ")
        //подключение слушателя из CustomView

        newsAdapter?.setOnItemClickListener {
            /*   val new = DetailNews(
                   author = it.author,
                   image = it.urlToImage,
                   title = it.title,
                   description = it.title,
                   url = it.url,
                   content = it.content
               )

               findNavController().navigate(
                   MainFragmentDirections.mainToSecond(new)
               )*/
        }

        binding.fab.text = viewModel.getPage().toString()
        binding.fab.setOnClickListener {
            viewModel.changePage()
            binding.fab.text = viewModel.getPage().toString()
        }

        binding.customGroup.setCustomViewClickListener(this)


        binding.recyclerView.adapter = newsAdapter

        binding.swipeRefresh.setOnRefreshListener {
            // viewModel.refresh()
        }

        viewModel.news.onEach {
            //newsAdapter.setData(it)
            //newsAdapter.submitList(it)
            if (it.isNotEmpty()) {
                newsAdapter?.differ?.submitList(it)
            } else {
                viewModel.resetPage()
                binding.fab.text = viewModel.getPage().toString()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        viewModel.isLoading.onEach {
            binding.swipeRefresh.isRefreshing = it
            binding.customGroup.visibility = if (it) View.GONE else View.VISIBLE
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        newsAdapter = null
        Log.d(TAG, "onDestroyView: ")
    }


    override fun onClicked(text: String) {
// обновляем категорию.
        viewModel.changeCategory(text)
        viewModel.refresh()

    }

    private fun initAdapter() {
        newsAdapter = NewsListAdapter()
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}