package com.example.apprecyckerrevista.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecyckerrevista.MainArticulo
import com.example.apprecyckerrevista.Models.Volumen
import com.example.apprecyckerrevista.databinding.ItemVolumenBinding
import com.google.android.material.snackbar.Snackbar

class VolumenAdapter : RecyclerView.Adapter<VolumenAdapter.ViewHolder>() {

    var volumenList: ArrayList<Volumen> = ArrayList()
    lateinit var context: Context

    @SuppressLint("NotConstructor")
    fun VolumenAdapter(listVolumes: ArrayList<Volumen>, mContext: Context){
        this.volumenList = listVolumes
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVolumenBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
        )
    }

    class ViewHolder(val binding: ItemVolumenBinding  ) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemVolume = volumenList.get(position)
        holder.binding.txtVolumenDescription.text = "Vol. " + itemVolume.volume + " Num. " + itemVolume.number
        holder.binding.txtDoi.text = "Doi: " + itemVolume.doi
        holder.binding.txtFechaPublicado.text = "Publicado: " + itemVolume.datePublished
        Glide.with(context).load(itemVolume.cover).into(holder.binding.imgPortadaVol)

        holder.binding.volumenDatos.setOnClickListener {
            Snackbar.make(it,"Volumen seleccionado: "+ itemVolume.title,
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val act = Intent(context.applicationContext, MainArticulo::class.java)
            act.putExtra("volumen", volumenList[position])
            act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(act)
        }
    }

    override fun getItemCount(): Int {
        return volumenList.size
    }
}