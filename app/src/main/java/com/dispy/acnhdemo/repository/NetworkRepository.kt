package com.dispy.acnhdemo.repository

import android.util.Log
import com.dispy.acnhdemo.model.ACNHService
import com.dispy.acnhdemo.model.bean.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dispy-Yang on 2022/10/12.
 *
 * @author yangchaofu
 * @since 2022/10/12
 */
class NetworkRepository {
    val acnhService: ACNHService by lazy {
        Retrofit.Builder()
            .baseUrl("https://acnhapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ACNHService::class.java)
    }

    val gson by lazy {
        Gson()
    }

    interface ResponseListener<T> {
        fun onResponse(response: T)
    }

    fun fetchSongs(listener: ResponseListener<List<Song>>) {
        val call: Call<SongsMap> = acnhService.getSongs()
        call.enqueue(object : Callback<SongsMap> {
            override fun onResponse(call: Call<SongsMap>, response: Response<SongsMap>) {
                val data = response.body()!!
                val listSongs = ArrayList<Song>()
                for (eachKey in data.keys) {
                    listSongs.add(data[eachKey]!!)
                }
                listener.onResponse(listSongs)
                Log.i("NetworkRepository", "Got ${listSongs.size} songs")
            }

            override fun onFailure(call: Call<SongsMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get songs", t)
            }

        })
    }

    fun fetchFishes(listener: ResponseListener<List<Fish>>) {
        val call: Call<FishesMap> = acnhService.getFishes()
        call.enqueue(object : Callback<FishesMap> {
            override fun onResponse(call: Call<FishesMap>, response: Response<FishesMap>) {
                val data = response.body()!!
                val listFish = ArrayList<Fish>()
                for (eachKey in data.keys) {
                    listFish.add(data[eachKey]!!)
                }
                listener.onResponse((listFish))
                Log.i("NetworkRepository", "Got ${listFish.size} fishes")
            }

            override fun onFailure(call: Call<FishesMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get fishes", t)
            }

        })
    }

    fun fetchSeaCreatures(listener: ResponseListener<List<SeaCreature>>) {
        val call: Call<SeaCreaturesMap> = acnhService.getSeaCreatures()
        call.enqueue(object : Callback<SeaCreaturesMap> {
            override fun onResponse(call: Call<SeaCreaturesMap>, response: Response<SeaCreaturesMap>) {
                val data = response.body()!!
                val listSeaCreature = ArrayList<SeaCreature>()
                for (eachKey in data.keys) {
                    listSeaCreature.add(data[eachKey]!!)
                }
                listener.onResponse(listSeaCreature)
                Log.i("NetworkRepository", "Got ${listSeaCreature.size} sea creatures")
            }

            override fun onFailure(call: Call<SeaCreaturesMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get sea creatures", t)
            }

        })
    }

    fun fetchBugs(listener: ResponseListener<List<Bug>>) {
        val call: Call<BugsMap> = acnhService.getBugs()
        call.enqueue(object : Callback<BugsMap> {
            override fun onResponse(call: Call<BugsMap>, response: Response<BugsMap>) {
                val data = response.body()!!
                val listBugs = ArrayList<Bug>()
                for (eachKey in data.keys) {
                  listBugs.add(data[eachKey]!!)
                }
                listener.onResponse(listBugs)
                Log.i("NetworkRepository", "Got ${listBugs.size} bugs")
            }

            override fun onFailure(call: Call<BugsMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get bugs", t)
            }

        })
    }
}