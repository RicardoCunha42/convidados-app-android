package com.milliways.convidados.viewmodel.holder

import android.app.AlertDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.milliways.convidados.databinding.RowGuestBinding
import com.milliways.convidados.model.GuestModel
import com.milliways.convidados.viewmodel.listener.OnGuestListener

class GuestViewHolder(
    private val bind: RowGuestBinding,
    private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza de que deseja remover?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}