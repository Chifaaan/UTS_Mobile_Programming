package com.uts_semangat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var emailTextView: TextView
    private lateinit var nimTextView: TextView
    private lateinit var namaTextView: TextView
    private lateinit var kelasTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        (activity as MainActivity).showBottomNavigationView()

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = "Profile"
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
                    logout()
                    true
                }
                else -> false
            }
        }

        emailTextView = view.findViewById(R.id.emailTextView)
        nimTextView = view.findViewById(R.id.nimTextView)
        namaTextView = view.findViewById(R.id.namaTextView)
        kelasTextView = view.findViewById(R.id.kelasTextView)
        logoutButton = view.findViewById(R.id.logoutButton)

        logoutButton.setOnClickListener {
            logout()
        }

        loadUserData()
        return view
    }

    private fun loadUserData() {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("userPrefs", AppCompatActivity.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val nim = sharedPreferences.getString("nim", "")
        val nama = sharedPreferences.getString("nama", "")
        val kelas = sharedPreferences.getString("kelas", "")

        emailTextView.text = "Email: $email"
        nimTextView.text = "NIM: $nim"
        namaTextView.text = "Nama: $nama"
        kelasTextView.text = "Kelas: $kelas"
    }

    private fun logout() {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("userPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        (activity as MainActivity).hideBottomNavigationView()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }
}
