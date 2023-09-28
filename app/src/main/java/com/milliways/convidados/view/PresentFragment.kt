package com.milliways.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.milliways.convidados.constants.DataBaseConstants
import com.milliways.convidados.databinding.FragmentPresentBinding
import com.milliways.convidados.viewmodel.GuestsViewModel
import com.milliways.convidados.viewmodel.adapter.GuestsAdapter
import com.milliways.convidados.viewmodel.listener.OnGuestListener

class PresentFragment : Fragment() {
    private val adapter = GuestsAdapter()
    private lateinit var viewModel: GuestsViewModel
    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        binding.recyclerPresent.layoutManager = LinearLayoutManager(context)

        binding.recyclerPresent.adapter = adapter
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.GUESTID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresents()
            }

        }
        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}