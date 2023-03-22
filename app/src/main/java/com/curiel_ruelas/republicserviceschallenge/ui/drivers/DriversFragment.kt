package com.curiel_ruelas.republicserviceschallenge.ui.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.curiel_ruelas.republicserviceschallenge.R
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.databinding.FragmentDriversBinding
import com.curiel_ruelas.republicserviceschallenge.ui.routes.RoutesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriversFragment : Fragment(), DriversAdapter.DriverInterface {

    private val viewModel: DriversViewModel by viewModels()

    private var _binding: FragmentDriversBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDrivers.title = activity?.title
        binding.tbDrivers.inflateMenu(R.menu.menu_drivers)
        binding.rvDrivers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDrivers.adapter = DriversAdapter(this)

        viewModel.drivers.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbDrivers.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    binding.pbDrivers.visibility = View.GONE
                    (binding.rvDrivers.adapter as DriversAdapter).info = it.data


                }
                is Resource.Error -> {
                    binding.pbDrivers.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        it.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.tbDrivers.setOnMenuItemClickListener {

            if (viewModel.getfilter()) {
                viewModel.sortDrivers()
            } else
                viewModel.getDrivers()
            return@setOnMenuItemClickListener false
        }
    }

    override fun onDriverSelected(driver: Driver) {
        parentFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
            replace(R.id.container, RoutesFragment.newInstance(driverId = driver.id))
            addToBackStack(null)
            commit()
        }
    }


}