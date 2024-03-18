package com.shu.mynews.ui.collections

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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shu.entity.icollection.Collections
import com.shu.mynews.App
import com.shu.mynews.R
import com.shu.mynews.databinding.FragmentProfileBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private var adapter: ListCollectionAdapter? = null

    private val collectionViewModel: ProfileViewModel by viewModels {
        App.appComponentUser.profileViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        /*  (activity as AppCompatActivity).supportActionBar?.apply {
              displayOptions = ActionBar.DISPLAY_SHOW_TITLE
          }*/
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListCollectionAdapter { collection -> onItemClick(collection) }
        binding.recyclerView.adapter = adapter

        collectionViewModel.collectionsAll.onEach {
            adapter?.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.fab.setOnClickListener {
            onAddCollectionOnClick()
        }

    }

    private fun onAddCollectionOnClick() {

        val v =
            LayoutInflater.from(context).inflate(R.layout.add_collection, null)
        val name = v.findViewById<EditText>(R.id.name)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Придумайте название для вашей новой коллекции")
            .setView(v)
            .setPositiveButton("Ok") { dialog, _ ->
                val nameCollection = name.text.toString()
                if (nameCollection != "") {
                    collectionViewModel.addCollection(nameCollection)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun onItemClick(collection: Collections) {
        Log.d("profileFragment", " Click on fragment")
        findNavController().navigate(
            ProfileFragmentDirections.actionProfileFragmentToMessageFragment(
                CollectionInUi(
                    collectionId = collection.collectionId,
                    name = collection.name,
                    total = collection.total,
                    icon = collection.icon,
                    checked = collection.checked,
                )
            )
        )
    }

    override fun onResume() {
        super.onResume()
        //TODO обновлять списки после очистки.
        //collectionViewModel.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }


}