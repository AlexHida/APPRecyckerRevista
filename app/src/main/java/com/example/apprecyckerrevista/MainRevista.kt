package com.example.apprecyckerrevista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apprecyckerrevista.Adapter.RevistaAdapter
import com.example.apprecyckerrevista.Models.Revista
import com.example.apprecyckerrevista.databinding.ActivityMainBinding
import com.example.apprecyckerrevista.databinding.ActivityMainRevistaBinding
import java.lang.Exception

class MainRevista : AppCompatActivity() {

    lateinit var binding: ActivityMainRevistaBinding
    private var adapter: RevistaAdapter = RevistaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainRevistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var lstRevistas = ArrayList<Revista>()
        val cola = Volley.newRequestQueue(this)
        val intent = intent.extras
        var url = "https://revistas.uteq.edu.ec/ws/journals.php"

        val solicitud = JsonArrayRequest(Request.Method.GET, url, null,
        { response ->
            for (i in 0 until response.length()){
                var item = response.getJSONObject(i)
                lstRevistas.add(Revista(item))
            }
            binding.recyclerViewvRevistas.setHasFixedSize(true)
            binding.recyclerViewvRevistas.layoutManager = LinearLayoutManager(this)
            adapter.RevistaAdapter(lstRevistas, this)
            binding.recyclerViewvRevistas.adapter = adapter

        }, {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        })
        cola.add(solicitud)
    }
}