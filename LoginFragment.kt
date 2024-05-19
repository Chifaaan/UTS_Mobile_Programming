package com.uts_semangat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private val correctEmail = "123"
    private val correctPassword = "123"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        (activity as MainActivity).hideBottomNavigationView()

        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        loginButton = view.findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email == correctEmail && password == correctPassword) {
                saveUserData()
                navigateToHome()
            } else {
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun saveUserData() {
        val sharedPreferences = requireActivity().getSharedPreferences("userPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", correctEmail)
        editor.putString("nim", "2207411056")
        editor.putString("nama", "Muhammad Nur Irfan")
        editor.putString("kelas", "TI - 4B")
        editor.apply()
    }

    private fun navigateToHome() {
        (activity as MainActivity).showBottomNavigationView()
        val homeFragment = HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .commit()
    }
}