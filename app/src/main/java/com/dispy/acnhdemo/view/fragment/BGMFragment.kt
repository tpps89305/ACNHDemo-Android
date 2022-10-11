package com.dispy.acnhdemo.view.fragment

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentBgmBinding
import com.dispy.acnhdemo.viewmodel.BGMViewModel

class BGMFragment : BaseFragment() {

    private lateinit var binding: FragmentBgmBinding
    private var mediaPlayer: MediaPlayer? = MediaPlayer()
    private lateinit var viewModel: BGMViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBgmBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(BGMViewModel::class.java)
        initActionBar("BGM")
        initMusicPlayer()
        binding.buttonPlay.isEnabled = false
        viewModel.getBGMValue().observe(viewLifecycleOwner) { data ->
            binding.buttonPlay.isEnabled = true
        }

        val arrayHours = resources.getStringArray(R.array.array_hours)
        with(binding.pickerHour) {
            minValue = 0
            maxValue = arrayHours.size - 1
            displayedValues = arrayHours
        }

        val arrayWeather = resources.getStringArray(R.array.array_weathers)
        with(binding.pickerWeather) {
            minValue = 0
            maxValue = arrayWeather.size - 1
            displayedValues = arrayWeather
        }

        binding.buttonPlay.setOnClickListener {
            with(mediaPlayer!!) {
                if (isPlaying) {
                    stop()
                    reset()
                    binding.buttonPlay.text = resources.getString(R.string.play)
                } else {
                    val hour = arrayHours[binding.pickerHour.value]
                    val weather = arrayWeather[binding.pickerWeather.value]
                    setDataSource(viewModel.getMusicURL(hour, weather))

                    prepareAsync()
                }
            }
        }

        with(mediaPlayer!!) {
            setOnPreparedListener {
                start()
                binding.buttonPlay.text = resources.getString(R.string.stop)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun initMusicPlayer() {
        mediaPlayer?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.root.findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}