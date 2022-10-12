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

    fun fetchFossils(listener: ResponseListener<List<Fossil>>) {
        val call: Call<FossilsMap> = acnhService.getFossils()
        call.enqueue(object : Callback<FossilsMap> {
            override fun onResponse(call: Call<FossilsMap>, response: Response<FossilsMap>) {
                val data = response.body()!!
                val listFossils = ArrayList<Fossil>()
                for (eachKey in data.keys) {
                    listFossils.add(data[eachKey]!!)
                }
                listener.onResponse(listFossils)
                Log.i("NetworkRepository", "Got ${listFossils.size} fossils")
            }

            override fun onFailure(call: Call<FossilsMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get fossils", t)
            }

        })
    }

    fun fetchArt(listener: ResponseListener<List<Art>>) {
        val call: Call<ArtMap> = acnhService.getArt()
        call.enqueue(object : Callback<ArtMap> {
            override fun onResponse(call: Call<ArtMap>, response: Response<ArtMap>) {
                val data = response.body()!!
                val listArt = ArrayList<Art>()
                for (eachKey in data.keys) {
                    listArt.add(data[eachKey]!!)
                }
                listener.onResponse(listArt)
                Log.i("NetworkRepository", "Got ${listArt.size} art")
            }

            override fun onFailure(call: Call<ArtMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get art", t)
            }

        })
    }

    fun fetchBGMs(listener: ResponseListener<List<BGM>>) {
        val call: Call<BGMsMap> = acnhService.getBGM()
        call.enqueue(object : Callback<BGMsMap> {
            override fun onResponse(call: Call<BGMsMap>, response: Response<BGMsMap>) {
                val data = response.body()!!
                val listBGM = ArrayList<BGM>()
                for (eachKey in data.keys) {
                    listBGM.add(data[eachKey]!!)
                }
                listener.onResponse(listBGM)
                Log.i("NetworkRepository", "Got ${listBGM.size} BGMs")
            }

            override fun onFailure(call: Call<BGMsMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get BGMs", t)
            }

        })
    }

    fun fetchHousewares(listener: ResponseListener<List<Houseware>>) {
        val call: Call<HousewaresMap> = acnhService.getHouseware()
        call.enqueue(object : Callback<HousewaresMap> {
            override fun onResponse(call: Call<HousewaresMap>, response: Response<HousewaresMap>) {
                val data = response.body()!!
                val listHousewares = ArrayList<Houseware>()
                for (eachKey in data.keys) {
                    listHousewares.add(data[eachKey]!![0])
                }
                listener.onResponse(listHousewares)
                Log.i("NetworkRepository", "Got ${listHousewares.size} housewares")
            }

            override fun onFailure(call: Call<HousewaresMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get housewares", t)
            }

        })
    }

    fun fetchWallmounteds(listener: ResponseListener<List<Wallmounted>>) {
        val call: Call<WallmountedsMap> = acnhService.getWallmounted()
        call.enqueue(object : Callback<WallmountedsMap> {
            override fun onResponse(call: Call<WallmountedsMap>, response: Response<WallmountedsMap>) {
                val data = response.body()!!
                val listWallmounted = ArrayList<Wallmounted>()
                for (eachKey in data.keys) {
                    listWallmounted.add(data[eachKey]!![0])
                }
                listener.onResponse(listWallmounted)
                Log.i("NetworkRepository", "Got ${listWallmounted.size} wallmounted")
            }

            override fun onFailure(call: Call<WallmountedsMap>, t: Throwable) {
                Log.w("NetworkRepository", "Cannot get wallmounted", t)
            }

        })
    }
}