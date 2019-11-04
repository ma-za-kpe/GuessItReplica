package com.maku.guessitreplica.ui.title

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.maku.guessitreplica.R
import com.maku.guessitreplica.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    private lateinit var binding: TitleFragmentBinding

    companion object {
        fun newInstance() = TitleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.title_fragment, container, false)

        //The complete onClickListener with Navigation using createNavigateOnClickListener
        binding.playGameButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_nav_home))

        return binding.root
    }

}
