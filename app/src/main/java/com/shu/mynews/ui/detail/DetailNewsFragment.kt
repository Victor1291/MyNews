package com.shu.mynews.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shu.mynews.R
import com.shu.mynews.databinding.FragmentDetailNewsBinding

private const val TAG = "DetailNewsFragment"

class DetailNewsFragment : Fragment() {


    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!


    // private val glide by lazy { Glide.with(this) } утекает при повороте.

    private val args: DetailNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d(TAG, "onCreateView: ")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val new = args.news

        binding.fab.setOnClickListener {
            val activity = requireActivity()
            val url = new.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            activity.startActivity(intent)
        }

        binding.poster.setOnClickListener {
            findNavController().navigate(
                DetailNewsFragmentDirections.actionDetailNewsFragmentToZoomFragment(new.image ?: "")
            )
        }

        with(binding) {

            title.text = buildString {
                append(new.title)
                append(new.author)
            }
            text.text = new.content
            fab.text = new.url

            Glide
                .with(view)
                .load(new.image)
                /*.override(
                    resources.getDimensionPixelOffset(R.dimen.game_card_wide_width),
                    resources.getDimensionPixelOffset(R.dimen.game_card_wide_height)
                )*/
                .transform(
                    CenterCrop()
                )
                //.skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(poster)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "onDestroyView: ")
    }

}