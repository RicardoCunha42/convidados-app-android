package com.milliways.convidados.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milliways.convidados.databinding.RowGuestBinding
import com.milliways.convidados.model.GuestModel
import com.milliways.convidados.viewmodel.holder.GuestViewHolder
import com.milliways.convidados.viewmodel.listener.OnGuestListener

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {
    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return GuestViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    fun updatedGuests(guests: List<GuestModel>) {
        this.guestList = guests
        notifyDataSetChanged()
    }

    fun attachListener(listener: OnGuestListener) {
        this.listener = listener
    }
}