package com.milliways.convidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.milliways.convidados.constants.DataBaseConstants
import com.milliways.convidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guestModel: GuestModel): Long

    @Update
    fun update(guestModel: GuestModel): Int

    @Delete
    fun delete(guestModel: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun getById(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>

}