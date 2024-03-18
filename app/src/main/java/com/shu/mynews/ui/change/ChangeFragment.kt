package com.shu.mynews.ui.change

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shu.mynews.App
import com.shu.mynews.databinding.FragmentChangeBinding

class ChangeFragment : Fragment() {

    private var _binding: FragmentChangeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChangeViewModel by viewModels {
        App.appComponentUser.changeViewModelFactory()
    }

    private val args: ChangeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChangeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = args.message
        binding.textviewSecond.setText(message.message)

        binding.buttonSecond.setOnClickListener {
            val newText = binding.textviewSecond.text.toString()
            val newMessage = message.copy(message = newText)
            viewModel.updateMessage(newMessage)
            findNavController().popBackStack()
            // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}