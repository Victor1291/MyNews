package com.shu.mynews.ui.habits

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.green.habits.home.view.RecommendationsItemDecorator
import com.green.habits.home.view.StartSnapHelper
import com.shu.mynews.R
import com.shu.mynews.databinding.RecommendationsCellLayoutBinding
import com.shu.mynews.ui.core.Populatable

class RecommendationsCell
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), Populatable<RecommendationsHolder> {


    private var _binding: RecommendationsCellLayoutBinding? = null
    private val binding get() = _binding!!

    private val adapter = RecommendationsAdapter()

    init {
        val root = inflate(context, R.layout.recommendations_cell_layout, this)
        _binding = RecommendationsCellLayoutBinding.bind(root)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val startSnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding.recommendationsRecyclerView)
        binding.recommendationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recommendationsRecyclerView.adapter = adapter
        binding.recommendationsRecyclerView.addItemDecoration(RecommendationsItemDecorator())
    }

    override fun populate(model: RecommendationsHolder) {
        adapter.items.addAll(model.recommendations)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }
}

data class RecommendationsHolder(
    val recommendations: List<RecommendationModel>
) : MainListItemType {

    override fun getItemType() = R.layout.recommendations_cell_layout
}

data class RecommendationModel(
    val id: Long,
    val title: String,
    val subtitle: String,
    val imageBackgroundUrl: String,
    val imageForegroundUrl: String
)