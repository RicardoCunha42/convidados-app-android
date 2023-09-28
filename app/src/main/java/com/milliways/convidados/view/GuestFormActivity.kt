package com.milliways.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.milliways.convidados.R
import com.milliways.convidados.constants.DataBaseConstants
import com.milliways.convidados.databinding.ActivityGuestFormBinding
import com.milliways.convidados.model.GuestModel
import com.milliways.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()

        loadData()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val guest = GuestModel(this.guestId, name, presence)

            viewModel.save(guest)

            //TODO temp
            finish()
        }
    }

    private fun observe() {
        this.viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        this.viewModel.saveGuest.observe(this) {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            this.guestId = bundle.getInt(DataBaseConstants.GUEST.GUESTID)
            viewModel.getGuest(this.guestId)
        }
    }
}