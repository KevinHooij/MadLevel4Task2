package com.example.madlevel4task2

import com.example.madlevel4task2.Play.Outcome
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlayFragment : Fragment() {

    private lateinit var playRepository: PlayRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    lateinit var playerAttack: Play.Attack
    lateinit var enemyAttack: Play.Attack



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playRepository = PlayRepository(requireContext())
        initViews()
    }

    fun initViews(){
        btnScissor.setOnClickListener{playScissors()}
        btnPaper.setOnClickListener{playPaper()}
        btnRock.setOnClickListener{playRock()}

        btnHistory.setOnClickListener{findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)}
    }

    fun playScissors(){
        ivPlayer.setImageResource(R.drawable.scissors)

        playerAttack = Play.Attack.SCISSORS
        enemyPlays()
    }
    fun playRock(){
        ivPlayer.setImageResource(R.drawable.rock)

        playerAttack = Play.Attack.ROCK
        enemyPlays()
    }
    fun playPaper(){
        ivPlayer.setImageResource(R.drawable.paper)

        playerAttack = Play.Attack.PAPER
        enemyPlays()
    }


    fun enemyPlays(){
        val random = (0..2).random()

        if (random == 0){
            ivComputer.setImageResource(R.drawable.rock)
            enemyAttack = Play.Attack.ROCK
        }
        if (random == 1){
            ivComputer.setImageResource(R.drawable.paper)
            enemyAttack = Play.Attack.PAPER
        }
        if (random == 2){
            ivComputer.setImageResource(R.drawable.scissors)
            enemyAttack = Play.Attack.SCISSORS
        }
        decideWinner()
        showOutcome()
        outcomeToDB()
    }

    fun decideWinner(): Outcome {
        if (playerAttack == Play.Attack.ROCK && enemyAttack == Play.Attack.SCISSORS){
            return Outcome.WIN

        }
        if (playerAttack == Play.Attack.ROCK && enemyAttack == Play.Attack.PAPER){
            return Outcome.LOSE
        }

        if (playerAttack == Play.Attack.PAPER && enemyAttack == Play.Attack.ROCK){
            return Outcome.WIN
        }
        if (playerAttack == Play.Attack.PAPER && enemyAttack == Play.Attack.SCISSORS){
            return Outcome.LOSE
        }

        if (playerAttack == Play.Attack.SCISSORS && enemyAttack == Play.Attack.PAPER){
            return Outcome.WIN
        }
        if (playerAttack == Play.Attack.SCISSORS && enemyAttack == Play.Attack.ROCK){
            return Outcome.LOSE
        }

        if (playerAttack == enemyAttack){
            return Outcome.DRAW
        }
        return Outcome.DRAW
    }

    fun showOutcome(){
        if (decideWinner() == Outcome.WIN){
            tvWinner.text = getString(R.string.win_txt)
        }
        if (decideWinner() == Outcome.LOSE){
            tvWinner.text = getString(R.string.lose_txt)
        }
        if (decideWinner() == Outcome.DRAW){
            tvWinner.text = getString(R.string.draw_txt)
        }
    }

    fun outcomeToDB(){
        mainScope.launch{
            withContext(Dispatchers.IO){
                playRepository.insertPlay(Play(null,playerAttack,enemyAttack,decideWinner(), Date()))
            }
        }
    }


}