package com.example.navdemo1

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.navdemo1.databinding.FragmentEmailBinding
import com.example.navdemo1.databinding.FragmentHomeBinding

class EmailFragment : Fragment() {
    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailBinding.inflate(inflater,container,false)
        binding.btnSubmit.setOnClickListener() {
            if (!TextUtils.isEmpty(binding.etEmail.text.toString())) {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
                    val userName = requireArguments().getString("user_name")
                    val bundle = bundleOf(
                        "user_name" to userName,
                        "user_email" to binding.etEmail.text.toString()
                    )
                    it.findNavController()
                        .navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
                } else {
                    Toast.makeText(activity, "Make sure to provide a correct email address", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(activity, "Please provide an email", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

}