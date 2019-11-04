package com.maku.guessitreplica.ui.ui

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.maku.guessitreplica.R
import com.maku.guessitreplica.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        //construct a refence for the view model
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.hViewModel = homeViewModel

        // UI controllers are where you'll set up the observation relationship
        homeViewModel.score.observe(this, Observer { newScore ->

            binding.scoreText.text = newScore.toString()

        })

        homeViewModel.word.observe(this, Observer { newWord ->

            binding.wordText.text = newWord.toString()

        })

        homeViewModel.currentTime.observe(this, Observer { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)

        })

        homeViewModel.eventGameFinish.observe(this, Observer { hasFinished ->

            if (hasFinished) {
                val currentScore = homeViewModel.score.value ?: 0
                val action = HomeFragmentDirections.actionNavHomeToScoreFragment(currentScore)
                findNavController(this).navigate(action)
                homeViewModel.onGameFinishComplete()
            }

        })

        return binding.root
    }

    /**
     * Called when the game is finished
     */
    fun gameFinished() {
//        val action = GameFragmentDirections.actionGameToScore(homeViewModel.score)
//        findNavController(this).navigate(action)
        Toast.makeText(this.activity, "game has finished", Toast.LENGTH_SHORT).show()
    }



}