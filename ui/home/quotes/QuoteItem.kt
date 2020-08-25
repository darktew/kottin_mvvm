package com.example.kotlin_mvvm.ui.home.quotes

import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.database.entities.Quotes
import com.example.kotlin_mvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quotes: Quotes
) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quotes)
    }


}