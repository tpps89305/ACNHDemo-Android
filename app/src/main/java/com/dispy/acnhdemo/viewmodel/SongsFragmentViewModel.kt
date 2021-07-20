package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Song
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongsFragmentViewModel: ViewModelBase() {

    private val songs: MutableLiveData<List<Song>> = MutableLiveData()

    fun getSongs(): LiveData<List<Song>> {
        if (songs.value != null)
            return songs
        val call: Call<ResponseBody> = acnhService.getSongs()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listSongs = ArrayList<Song>()
                val jsonSongs = JSONObject(data)
                for (eachKey in jsonSongs.keys()) {
                    val eachObject = jsonSongs.optJSONObject(eachKey)!!
                    listSongs.add(gson.fromJson(eachObject.toString(), object : TypeToken<Song>() {}.type))
                }
                songs.value = listSongs
                Log.i("Songs", "Get songs success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Songs", "Error when get villagers")
                Log.w("Songs", t.message!!)
            }

        })
        return songs
    }

}
