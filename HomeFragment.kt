package com.uts_semangat

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as MainActivity).showBottomNavigationView()

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
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

        return view
    }
}