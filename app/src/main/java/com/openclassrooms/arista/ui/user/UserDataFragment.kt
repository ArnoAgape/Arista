package com.openclassrooms.arista.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.openclassrooms.arista.databinding.FragmentUserDataBinding
import com.openclassrooms.arista.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private lateinit var binding: FragmentUserDataBinding
    private val viewModel: UserDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUser()
    }

    private fun observeUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userFlow.collect { user: User? ->
                        user?.let {
                            binding.etName.setText(it.nickname)
                            binding.etEmail.setText(it.email)
                            binding.etAge.setText(it.age.toString())
                            binding.etWeight.setText(it.weight.toString())
                            binding.etHeight.setText(it.height.toString())
                        }
                    }
                }
                launch {
                    viewModel.errorFlow.collect { errorMessage ->
                        errorMessage?.let {
                            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}
