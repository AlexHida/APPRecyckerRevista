package com.example.apprecyckerrevista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apprecyckerrevista.Adapter.ArticuloAdapter
import com.example.apprecyckerrevista.Models.Articulo
import com.example.apprecyckerrevista.Models.Revista
import com.example.apprecyckerrevista.Models.Volumen
import com.example.apprecyckerrevista.databinding.ActivityMainArticuloBinding

class MainArticulo : AppCompatActivity() {

    lateinit var binding: ActivityMainArticuloBinding
    private var adapter : ArticuloAdapter = ArticuloAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainArticuloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lstArticulos = ArrayList<Articulo>()
        val infoArticulos = intent.getSerializableExtra("volumen") as Volumen

        val cola = Volley.newRequestQueue(this)
        var url= "https://revistas.uteq.edu.ec/ws/pubs.php"

        val solicitud = JsonArrayRequest(
            Request.Method.GET, url + "?i_id=" + infoArticulos.issueid, null,
            { response ->
                for (i in 0 until response.length()){
                    var articulo = response.getJSONObject(i)
                    lstArticulos.add(Articulo(articulo))
                }
                binding.recyclerViewArticulos.setHasFixedSize(true)
                binding.recyclerViewArticulos.layoutManager = LinearLayoutManager(this)
                adapter.ArticuloAdapter(lstArticulos, this)
                binding.recyclerViewArticulos.adapter = adapter
                binding.lblNombreArt.text = "Vol. " + infoArticulos.volume +" Num. " + infoArticulos.number + " Titulo: " + infoArticulos.title
            },{
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            })
        cola.add(solicitud)
    }
}