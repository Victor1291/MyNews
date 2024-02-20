package com.shu.mynews.ui.habits

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import com.shu.mynews.R
import com.shu.mynews.databinding.MainHabitCellLayoutBinding
import com.shu.mynews.ui.core.Populatable
import com.squareup.picasso.Picasso

class MainHabitCell
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), Populatable<MainHabitModel> {

    private var _binding: MainHabitCellLayoutBinding? = null
    private val binding get() = _binding!!

    init {
        val root = inflate(context, R.layout.main_habit_cell_layout, this)
        _binding = MainHabitCellLayoutBinding.bind(root)
    }

    override fun populate(model: MainHabitModel) {
        binding.titleView.text = model.title
        binding.repeatsIndicatorView.setDotsNumber(4)
        Picasso.get().load(model.icon).into(binding.iconView)
        setOnClickListener { binding.repeatsIndicatorView.updateCounter() }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }
}

data class MainHabitModel(
    val id: Long,
    val title: String,
    @DrawableRes val icon: Int,
    val totalRepeatsNumber: Int,
    val doneRepeatsNumber: Int,
    val isCompleted: Boolean,
    val days: List<Int> = emptyList()
) : MainListItemType {

    override fun getItemType() = R.layout.main_habit_cell_layout
}