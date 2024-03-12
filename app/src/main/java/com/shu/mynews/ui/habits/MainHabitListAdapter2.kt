package com.shu.mynews.ui.habits

import android.animation.Animator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shu.mynews.databinding.HabitCellBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder

class MainHabitListAdapter2 : RecyclerView.Adapter<MainHabitsViewHolder>() {

    //  var items: MutableList<MainListItemType> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHabitsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = HabitCellBinding.inflate(inflater, parent, false)

        return MainHabitsViewHolder(binding)

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


    override fun onBindViewHolder(holder: MainHabitsViewHolder, position: Int) {
        val item = differ.currentList[position]

        with(holder.binding) {
            with((item as MainHabitModel)) {
                titleView.text = item.title
                repeatsIndicatorView.setDotsNumber(4)
                Picasso.get().load(item.icon).into(iconView)
                repeatsIndicatorView.setOnClickListener { repeatsIndicatorView.updateCounter() }
            }
        }


        // holder.binding.cell.populate(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

}


class MainHabitsViewHolder(val binding: HabitCellBinding) : RecyclerView.ViewHolder(binding.root),
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



