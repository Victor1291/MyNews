package com.shu.mynews.ui.message

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shu.entity.icollection.Collections
import com.shu.entity.icollection.IMessage
import com.shu.mynews.App
import com.shu.mynews.R
import com.shu.mynews.databinding.FragmentMessageBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MessageFragment()
    }

    private val viewModel: MessageViewModel by viewModels {
        App.appComponentUser.messageViewModelFactory()
    }

    private val args: MessageFragmentArgs by navArgs()

    private var adapter: ChildAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collection = args.collection
        viewModel.loadList(collection.collectionId)

        adapter = ChildAdapter { message -> onItemClick(message) }
        binding.recyclerView.adapter = adapter

        viewModel.listMessages.onEach {
            adapter?.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        /* viewLifecycleOwner.lifecycleScope.launch {
             repeatOnLifecycle(Lifecycle.State.STARTED) {
                 viewModel.listMessages
                     .collect { wordList ->
                         adapter?.submitList(wordList)
                     }
             }
         }*/

        binding.fab.setOnClickListener {
            onAddMessageOnClick(collection)
        }

    }

    private fun onAddMessageOnClick(collection: Collections) {
        val v =
            LayoutInflater.from(context).inflate(R.layout.add_message, null)
        val name = v.findViewById<EditText>(R.id.name)
        MaterialAlertDialogBuilder(requireContext())
            .setView(v)
            .setPositiveButton("Ok") { dialog, _ ->
                val message = name.text.toString()
                if (message != "") {
                    //TODO
                    viewModel.createMessage(
                        Message(
                            message = message,
                            collectionId = collection.collectionId
                        )
                    )
                    //viewModel.loadList(collection.collectionId)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun onItemClick(message: IMessage) {
        Log.d("messageFragment", "click on item")
        findNavController().navigate(
            MessageFragmentDirections.actionMessageFragmentToChangeFragment(
                Message(
                    messageId = message.messageId,
                    message = message.message,
                    collectionId = message.collectionId
                )
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

}