package com.maku.guessitreplica.ui.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class HomeViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The current word live data, private to onlny he view model, which is allowed to change it(internal version)
    private var _eventGameFinish = MutableLiveData<Boolean>()
        //intorduce the LiveData (external version)
    val eventGameFinish : LiveData<Boolean>
        get() = _eventGameFinish

    // The current word live data, private to onlny he view model, which is allowed to change it(internal version)
   private var _word = MutableLiveData<String>()
    //intorduce the LiveData (external version)
    val word : LiveData<String>
    get() = _word

    // The current score live data, private to onlny he view model, which is allowed to change it
    private var _score = MutableLiveData<Int>()
    //intorduce the LiveData
    val score : LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Timber.i("Gameviewmodel created ...")
        resetList()
        nextWord()
        _eventGameFinish.value = false
        _score.value = 0
        _word.value = ""

        //timer
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            // gameFinished() should happen here
//            _eventGameFinish.value = true
            resetList()
        }
        _word.value = wordList.removeAt(0)

    }

    /** Methods for buttons presses add null safety checks, then call the minus and plus functions, respectively. For example:**/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /** Methods to handle the game finished event  **/
    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        Timber.i("Gameviewmodel destroyed ...")
        super.onCleared()
        timer.cancel()
    }
}