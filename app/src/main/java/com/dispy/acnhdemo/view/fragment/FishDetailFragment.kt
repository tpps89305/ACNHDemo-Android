package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentFishDetailBinding
import com.dispy.acnhdemo.view.adapter.TitleContentAdapter
import com.dispy.acnhdemo.viewmodel.FishDetailViewModel

class FishDetailFragment : BaseFragment() {

    private val args: FishDetailFragmentArgs by navArgs()
    private val viewModel: FishDetailViewModel by viewModels()
    private val detailAdapter = TitleContentAdapter(ArrayList())
    private lateinit var binding: FragmentFishDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFishDetailBinding.inflate(layoutInflater)
        initActionBar(args.fish.name.nameTWzh)

        binding.fish = args.fish
        binding.lifecycleOwner = this

        with(binding.listFishDetail) {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }

        with(viewModel) {
            parseData(args.fish)
            getFishInfo().observe(viewLifecycleOwner, { data ->
                detailAdapter.swapItems(data)
            })
        }

        return binding.root
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