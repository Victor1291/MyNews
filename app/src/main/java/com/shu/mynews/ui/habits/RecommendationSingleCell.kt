package com.shu.mynews.ui.habits

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.shu.mynews.R
import com.shu.mynews.databinding.RecommendationCellLayoutBinding
import com.shu.mynews.ui.core.Populatable

class RecommendationSingleCell @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), Populatable<RecommendationModel> {

    private var _binding: RecommendationCellLayoutBinding? = null
    private val binding get() = _binding!!

    init {

        val root = inflate(context, R.layout.recommendation_cell_layout, this)
        _binding = RecommendationCellLayoutBinding.bind(root)
    }
    override fun populate(model: RecommendationModel) {
        binding.textView.text = model.title
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

}