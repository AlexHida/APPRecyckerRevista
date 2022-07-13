package com.example.apprecyckerrevista.Models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Revista (a: JSONObject) : Serializable {
    var idRevista : String
    var nombreRevista : String
    var abbrevRevista : String
    var portadaRevista : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Volumen> {
            val revis : ArrayList<Volumen> =  ArrayList<Volumen>()
            for(i in 0 until datos.length()) {
                revis.add(Volumen(datos.getJSONObject(i)))
            }
            return revis
        }
    }

    init {
        idRevista = a.getString("journal_id").toString()
        nombreRevista = a.getString("name").toString()
        abbrevRevista = a.getString("abbreviation").toString()
        portadaRevista = a.getString("portada").toString()
    }
}