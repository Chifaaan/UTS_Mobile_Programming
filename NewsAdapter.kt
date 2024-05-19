package com.uts_semangat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.uts_semangat.Data.NewsItem

class NewsAdapter(private val context: Context, private val dataSource: List<NewsItem>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.news_item, parent, false)

        val newsImageView = view.findViewById<ImageView>(R.id.newsImageView)
        val newsTitleTextView = view.findViewById<TextView>(R.id.newsTitleTextView)
        val newsDescriptionTextView = view.findViewById<TextView>(R.id.newsDescriptionTextView)

        val newsItem = getItem(position) as NewsItem

        newsImageView.setImageResource(newsItem.imageResId)
        newsTitleTextView.text = newsItem.title
        newsDescriptionTextView.text = newsItem.description

        return view
    }
}