package com.milliways.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.milliways.convidados.constants.DataBaseConstants
import com.milliways.convidados.model.GuestModel

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun insert(guestModel: GuestModel): Boolean {
        return guestDataBase.insert(guestModel) > 0
    }

    fun update(guestModel: GuestModel): Boolean {
        return guestDataBase.update(guestModel) > 0
    }

    fun delete(id: Int) {
        val guest = guestDataBase.getById(id)
        guestDataBase.delete(guest)
    }

    fun getById(id: Int): GuestModel {
        return guestDataBase.getById(id)
    }

    fun getAll(): List<GuestModel> {
       return guestDataBase.getAll()
    }

    fun getPresent(): List<GuestModel> {
        return guestDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return guestDataBase.getAbsent()
    }
}