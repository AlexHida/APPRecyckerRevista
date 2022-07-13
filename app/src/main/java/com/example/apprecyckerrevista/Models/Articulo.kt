package com.example.apprecyckerrevista.Models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Articulo (a: JSONObject) : Serializable {
    var art_title : String
    var art_doi : String
    var art_datePublished : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Articulo> {
            val articles : ArrayList<Articulo> =  ArrayList<Articulo>()
            for(i in 0 until datos.length()) {
                articles.add(Articulo(datos.getJSONObject(i)))
            }
            return articles
        }
    }
    init {
        art_title = a.getString("title").toString()
        art_doi = a.getString("doi").toString()
        art_datePublished = a.getString("date_published").toString()
    }
}