package com.shu.mynews.ui.home

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import com.shu.mynews.R
import com.shu.mynews.databinding.ChooseCategoriesBinding


class ChooseCategoriesViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var _binding: ChooseCategoriesBinding? = null
    private val binding get() = _binding!!

    //ставим слушатель
    private var listener: CustomViewClickListener? = null

    private val cat = resources.getStringArray(R.array.category)

    private val listButton = mutableListOf<Button>()
    private var lastButton: Int = 0

    init {

        val root = inflate(context, R.layout.choose_categories, this)
        _binding = ChooseCategoriesBinding.bind(root)
        setText()

        listButton()

        //реализуем нажатие кнопок
        listButton.forEachIndexed { index, button ->
            button.setOnClickListener {
                button.alpha = 0.6F
                //отправляем текст выбора категории
                listener?.onClicked(button.text.toString())
                listButton[lastButton].alpha = 1F
                lastButton = index
            }
        }

    }

    //устанавливаем во фрагменте binding.customGroup.setCustomViewClickListener(this)
    fun setCustomViewClickListener(listener: CustomViewClickListener) {
        this.listener = listener
    }

    private fun setText() {

        binding.rover1.text = cat[0]
        binding.rover2.text = cat[1]
        binding.rover3.text = cat[2]
        binding.rover4.text = cat[3]
        binding.rover5.text = cat[4]
        binding.rover6.text = cat[5]
        binding.rover7.text = cat[6]
        binding.rover8.text = cat[7]
        binding.rover9.text = cat[8]
        binding.rover10.text = cat[9]
        binding.rover11.text = cat[10]
        binding.rover12.text = cat[11]
        binding.rover13.text = cat[12]
    }

    //Создаём список кнопок
    private fun listButton() {
        listButton.add(binding.rover1)
        listButton.add(binding.rover2)
        listButton.add(binding.rover3)
        listButton.add(binding.rover4)
        listButton.add(binding.rover5)
        listButton.add(binding.rover6)
        listButton.add(binding.rover7)
        listButton.add(binding.rover8)
        listButton.add(binding.rover9)
        listButton.add(binding.rover10)
        listButton.add(binding.rover11)
        listButton.add(binding.rover12)
        listButton.add(binding.rover13)

    }

    interface CustomViewClickListener {
        fun onClicked(text: String)
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

}


/*
создаём интерфейс слушателя в CustomView

 */