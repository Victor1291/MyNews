package com.shu.mynews.ui.habits

import android.animation.Animator
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shu.mynews.R
import com.shu.mynews.databinding.HabitCellBinding
import com.shu.mynews.databinding.RecommendationsCellBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder

class MainHabitListAdapter3 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val adapter = RecommendationsAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            R.layout.habit_cell -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = HabitCellBinding.inflate(inflater, parent, false)

                return MainHabitsViewHolder(binding)
            }

            R.layout.recommendations_cell -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecommendationsCellBinding.inflate(inflater, parent, false)
                val startSnapHelper = StartSnapHelper()
                startSnapHelper.attachToRecyclerView(binding.recommendationsRecyclerView)
                binding.recommendationsRecyclerView.layoutManager =
                    LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
                binding.recommendationsRecyclerView.adapter = adapter
                binding.recommendationsRecyclerView.addItemDecoration(RecommendationsItemDecorator())
                return RecomendViewHolder(binding)
            }

            else -> {
                Log.d("recicler", "viewType  R.layout.habit_cell ${R.layout.habit_cell}")
                Log.d(
                    "recicler",
                    "viewType R.layout.recommendations_cell  ${R.layout.recommendations_cell}"
                )
                Log.d("recicler", "viewType $viewType")
                throw IllegalArgumentException("Wrong type")
            }
        }


    }

    private val callback = object : DiffUtil.ItemCallback<MainListItemType>() {
        override fun areItemsTheSame(
            oldItem: MainListItemType,
            newItem: MainListItemType
        ): Boolean {
            return oldItem.getItemType() == newItem.getItemType()
        }

        override fun areContentsTheSame(
            oldItem: MainListItemType,
            newItem: MainListItemType
        ): Boolean {
            return oldItem.size() == newItem.size()
        }
    }

    val differ = AsyncListDiffer(this, callback)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when (item.getItemType()) {
            R.layout.habit_cell -> {
                with((holder as MainHabitsViewHolder).binding) {
                    with((item as MainHabitModel)) {
                        titleView.text = item.title
                        repeatsIndicatorView.setDotsNumber(4)
                        Picasso.get().load(item.icon).into(iconView)
                        repeatsIndicatorView.setOnClickListener { repeatsIndicatorView.updateCounter() }
                    }
                }
            }

            R.layout.recommendations_cell -> {
                adapter.items.addAll((item as RecommendationsHolder).recommendations)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size


    class RecomendViewHolder(val binding: RecommendationsCellBinding) :
        RecyclerView.ViewHolder(binding.root)

    class MainHabitsViewHolder(val binding: HabitCellBinding) :
        RecyclerView.ViewHolder(binding.root),
        AnimateViewHolder {
        override fun animateAddImpl(
            holder: RecyclerView.ViewHolder,
            listener: Animator.AnimatorListener
        ) {
            holder.itemView
                .animate()
                .translationX(0F)
                .setDuration(120)
                .setInterpolator(LinearInterpolator())
                .start()
        }

        override fun animateRemoveImpl(
            holder: RecyclerView.ViewHolder,
            listener: Animator.AnimatorListener
        ) {
            holder.itemView
                .animate()
                .translationX(-holder.itemView.rootView.width.toFloat())
                .setDuration(120)
                .setInterpolator(LinearInterpolator())
                .start()
        }

        override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
            holder.itemView.translationX = -holder.itemView.rootView.width.toFloat()
        }

        override fun preAnimateRemoveImpl(holder: RecyclerView.ViewHolder) {
            TODO("Not yet implemented")
        }
    }
}

