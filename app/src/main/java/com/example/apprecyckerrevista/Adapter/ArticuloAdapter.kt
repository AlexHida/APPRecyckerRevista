package com.example.apprecyckerrevista.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecyckerrevista.Models.Articulo
import com.example.apprecyckerrevista.databinding.ItemArticuloBinding
import com.google.android.material.snackbar.Snackbar

class ArticuloAdapter : RecyclerView.Adapter<ArticuloAdapter.ViewHolder>() {

    var articulosList: ArrayList<Articulo> = ArrayList()
    lateinit var context: Context

    @SuppressLint("NotConstructor")
    fun ArticuloAdapter(listArticles: ArrayList<Articulo>, mContext: Context){
        this.articulosList = listArticles
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticuloBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class ViewHolder(val binding: ItemArticuloBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemArticulo = articulosList.get(position)
        holder.binding.txtNameArt.text = itemArticulo.art_title
        holder.binding.txtDoiArt.text = itemArticulo.art_doi
        holder.binding.txtDateArt.text = itemArticulo.art_datePublished

        holder.binding.articuloDatos.setOnClickListener {
            Snackbar.make(it,"Articulo seleccionada: "+ itemArticulo.art_title,
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }
    override fun getItemCount(): Int {
        return articulosList.size
    }
}