package com.curiel_ruelas.republicserviceschallenge.ui.routes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.databinding.FragmentRoutesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutesFragment : Fragment() {

    companion object {
        private const val DRIVER_ID = "driver id"
        fun newInstance(driverId: String): RoutesFragment {
            val args = Bundle()
            args.putString(DRIVER_ID, driverId)
            val fragment = RoutesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentRoutesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoutesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRoutes(arguments?.getString(DRIVER_ID) ?: "")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbRoutes.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        binding.rvRoutes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.routes.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.pbRoutes.visibility = View.VISIBLE
                is Resource.Error -> {
                    binding.pbRoutes.visibility = View.GONE
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.pbRoutes.visibility = View.GONE
                    it.data?.let { routes ->
                        binding.rvRoutes.adapter = RoutesAdapter(routes)
                    }
                }
            }
        }
        viewModel.driver.observe(viewLifecycleOwner) {
            binding.tbRoutes.title = it.name
        }
    }

}