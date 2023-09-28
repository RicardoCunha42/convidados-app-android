package com.milliways.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.milliways.convidados.model.GuestModel
import com.milliways.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application)
    private val guestList = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = guestList

    fun getAll() {
        guestList.value = repository.getAll()
    }

    fun getPresents() {
        guestList.value = repository.getPresent()
    }

    fun getAbsents() {
        guestList.value = repository.getAbsent()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}