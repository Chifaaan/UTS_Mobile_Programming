package com.uts_semangat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.uts_semangat.Data.NewsItem

class BeritaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_berita, container, false)
        (activity as MainActivity).showBottomNavigationView()

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = "Berita"
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_add_data -> {
                    startActivity(Intent(requireContext(), TambahData::class.java))
                    true
                }
                R.id.action_view_alumni -> {
                    startActivity(Intent(requireContext(), Alumni::class.java))
                    true
                }
                R.id.action_logout -> {
                    (activity as MainActivity).hideBottomNavigationView()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        val newsListView: ListView = view.findViewById(R.id.newsListView)

        val newsItems = listOf(
            NewsItem(R.drawable.news1, "Judul Berita 1", "Deskripsi berita 1"),
            NewsItem(R.drawable.news1, "Judul Berita 2", "Deskripsi berita 2"),
            NewsItem(R.drawable.news1, "Judul Berita 3", "Deskripsi berita 3"),
            NewsItem(R.drawable.news1, "Judul Berita 4", "Deskripsi berita 4"),
            NewsItem(R.drawable.news1, "Judul Berita 5", "Deskripsi berita 5"),
            NewsItem(R.drawable.news1, "Judul Berita 6", "Deskripsi berita 6"),
            NewsItem(R.drawable.news1, "Judul Berita 7", "Deskripsi berita 7"),
            NewsItem(R.drawable.news1, "Judul Berita 8", "Deskripsi berita 8"),
            NewsItem(R.drawable.news1, "Judul Berita 9", "Deskripsi berita 9"),
            NewsItem(R.drawable.news1, "Judul Berita 10", "Deskripsi berita 10")
        )

        val adapter = NewsAdapter(requireContext(), newsItems)
        newsListView.adapter = adapter

        return view
    }
}