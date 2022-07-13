package com.example.apprecyckerrevista.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecyckerrevista.MainVolumen
import com.example.apprecyckerrevista.Models.Revista
import com.example.apprecyckerrevista.databinding.ItemRevistaBinding
import com.google.android.material.snackbar.Snackbar

@SuppressLint("NotConstructor")
class RevistaAdapter : RecyclerView.Adapter<RevistaAdapter.ViewHolder>() {

    var revistaList: ArrayList<Revista> = ArrayList()
    lateinit var context: Context

    fun RevistaAdapter(listRevista: ArrayList<Revista>, mContext: Context){
        this.revistaList = listRevista
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRevistaBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        )
    }

    class ViewHolder(val binding: ItemRevistaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRevista = revistaList.get(position)
        holder.binding.txtNameRev.text = itemRevista.nombreRevista
        holder.binding.txtAbreviRev.text = itemRevista.abbrevRevista
        Glide.with(context).load(itemRevista.portadaRevista).into(holder.binding.imgPortadaRev)

        holder.binding.revistaDatos.setOnClickListener {
            Snackbar.make(it,"Revista seleccionada: "+ itemRevista.nombreRevista,
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val act = Intent(context.applicationContext, MainVolumen::class.java)
            act.putExtra("revista", revistaList[position])
            act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(act)
        }
    }
    override fun getItemCount(): Int {
        return revistaList.size
    }
}