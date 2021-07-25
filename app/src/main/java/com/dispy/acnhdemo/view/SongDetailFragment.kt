package com.dispy.acnhdemo.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentSongDetailBinding
import com.dispy.acnhdemo.viewmodel.SongDetailViewModel

class SongDetailFragment : Fragment() {

    private val args: SongDetailFragmentArgs by navArgs()
    private val viewModel = SongDetailViewModel()
    private var mediaPlayer : MediaPlayer? = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSongDetailBinding.inflate(layoutInflater)

        // Important
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        (activity as AppCompatActivity).supportActionBar?.title  = args.song.name.nameTWzh
        viewModel.getData(args.song)

        initMusicPlayer()
        binding.buttonPlay.setOnClickListener {
            with(mediaPlayer!!) {
                if (isPlaying) {
                    stop()
                    binding.buttonPlay.text = resources.getString(R.string.play)
                } else {
                    prepare()
                    start()
                    binding.buttonPlay.text = resources.getString(R.string.stop)
                }
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
            setDataSource(args.song.musicURI)
        }
    }

}