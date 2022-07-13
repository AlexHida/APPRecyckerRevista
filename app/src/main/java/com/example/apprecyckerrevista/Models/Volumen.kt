package com.example.apprecyckerrevista.Models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Volumen (a: JSONObject) : Serializable {
    var issueid : String
    var volume : String
    var number : String
    var datePublished : String
    var title : String
    var doi : String
    var cover : String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Volumen> {
            val vlm : ArrayList<Volumen> =  ArrayList<Volumen>()
            for(i in 0 until datos.length()) {
                vlm.add(Volumen(datos.getJSONObject(i)))
            }
            return vlm
        }
    }

    init {
        issueid = a.getString("issue_id").toString()
        volume = a.getString("volume").toString()
        number = a.getString("number").toString()
        datePublished = a.getString("date_published").toString()
        title = a.getString("title").toString()
        doi = a.getString("doi").toString()
        cover = a.getString("cover").toString()
    }
}