package com.example.apprecyckerrevista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apprecyckerrevista.Adapter.VolumenAdapter
import com.example.apprecyckerrevista.Models.Revista
import com.example.apprecyckerrevista.Models.Volumen
import com.example.apprecyckerrevista.databinding.ActivityMainVolumenBinding

class MainVolumen : AppCompatActivity() {

    lateinit var binding: ActivityMainVolumenBinding
    private var adapter: VolumenAdapter = VolumenAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainVolumenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lstVolumenes = ArrayList<Volumen>()
        val infoRevista = intent.getSerializableExtra("revista") as Revista

        val cola = Volley.newRequestQueue(this)
        var url= " https://revistas.uteq.edu.ec/ws/issues.php"

        val solicitud  = JsonArrayRequest(Request.Method.GET,url + "?j_id=" + infoRevista.idRevista,null,
            { response ->
                for (i in 0 until response.length()){
                    var volumen = response.getJSONObject(i)
                    lstVolumenes.add(Volumen(volumen))
                }
                binding.recyclerviewVolumen.setHasFixedSize(true)
                binding.recyclerviewVolumen.layoutManager = LinearLayoutManager(this)
                adapter.VolumenAdapter(lstVolumenes, this)
                binding.recyclerviewVolumen.adapter = adapter
                binding.lblNombreRev.text = infoRevista.nombreRevista
            }, {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            })
            cola.add(solicitud)
    }
}