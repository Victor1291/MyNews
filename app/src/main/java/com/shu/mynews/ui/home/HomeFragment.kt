package com.shu.mynews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.shu.mynews.ui.habits.MainHabitListAdapter
import com.shu.mynews.ui.habits.MainHabitsItemDecorator
import com.shu.mynews.databinding.FragmentHomeBinding
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {

        fun newInstance() = HomeFragment()
    }

    private val habitsListAdapter = MainHabitListAdapter()
    // private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //горожу такую конструкцию
     /*   HomeComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)*/
        // viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weekBar.setSelectedDay(viewModel.getDayOfWeek() - 1)
        initRecycler()


        viewModel.todayHabits.onEach {

            Log.d("homeFragment", " size List ${it.size} $it")
            habitsListAdapter.items.addAll(it)
            habitsListAdapter.notifyDataSetChanged()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.getHabits(1)
        binding.fab.setOnClickListener{
            //todo сделать переход
           // viewModel.openCreateHabitScreen(requireContext())
        }
    }

    private fun initRecycler() {
        binding.habitsListRecycler.addItemDecoration(MainHabitsItemDecorator())
        binding.habitsListRecycler.adapter = habitsListAdapter
        binding.habitsListRecycler.itemAnimator = SlideInLeftAnimator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}