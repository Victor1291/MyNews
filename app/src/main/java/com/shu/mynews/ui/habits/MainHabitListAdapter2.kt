package com.shu.mynews.ui.habits

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shu.mynews.databinding.HabitCellBinding
import com.squareup.picasso.Picasso

class MainHabitListAdapter2 : RecyclerView.Adapter<MainHabitsViewHolder>() {

    //  var items: MutableList<MainListItemType> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHabitsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = HabitCellBinding.inflate(inflater, parent, false)

        return MainHabitsViewHolder(binding)

    }

    private val callback = object : DiffUtil.ItemCallback<MainHabitModel>() {
        override fun areItemsTheSame(oldItem: MainHabitModel, newItem: MainHabitModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainHabitModel, newItem: MainHabitModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)


    override fun onBindViewHolder(holder: MainHabitsViewHolder, position: Int) {
        val item = differ.currentList[position]
        Log.d("maadapter", " new title  ${item.title}")
        with(holder.binding) {
            titleView.text = item.title
            repeatsIndicatorView.setDotsNumber(4)
            Picasso.get().load(item.icon).into(iconView)
            repeatsIndicatorView.setOnClickListener { repeatsIndicatorView.updateCounter() }
        }


        // holder.binding.cell.populate(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

}


class MainHabitsViewHolder(val binding: HabitCellBinding) : RecyclerView.ViewHolder(binding.root)

/*class MdainHabitsViewHolder(
    private val item: MainHabitCell
) : RecyclerView.ViewHolder(item) { //, AnimateViewHolder

    *//*  override fun animateAddImpl(
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
      }*//*

    fun populate(model: MainListItemType) {
        item.populate(model as MainHabitModel)
    }
}*/


