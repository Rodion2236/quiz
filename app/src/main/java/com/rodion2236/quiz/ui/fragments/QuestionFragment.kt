package com.rodion2236.quiz.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rodion2236.quiz.R
import com.rodion2236.quiz.data.question.Level
import com.rodion2236.quiz.data.question.Question
import com.rodion2236.quiz.data.question.QuestionList
import com.rodion2236.quiz.databinding.FragmentQuestionBinding
import com.rodion2236.quiz.db.DbProfile
import com.rodion2236.quiz.profile.CurrentProfile
import com.rodion2236.quiz.profile.Profile
import java.util.Locale

class QuestionFragment: Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private lateinit var gameLevel: Level
    private val questionList = QuestionList()
    private var profile: Profile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()
        val level = args.getString("level")
        val name = args.getString("name")

        gameLevel = when (level) {
            "easy" -> Level.EASY
            "medium" -> Level.MEDIUM
            "hard" -> Level.HARD
            else -> Level.EASY
        }
        profile = DbProfile.getInstance(requireContext())?.appDB?.profileDao()?.getUserByName(name)

        play()
        startTimer()
    }

    private fun play() {
        try {
            val question = questionList.getQuestion(gameLevel)

            binding.questionTv.text = question.toString()

            binding.button1.text = question.wrongAnswerOne
            binding.button2.text = question.wrongAnswerTwo
            binding.button3.text = question.wrongAnswerThree
            binding.button4.text = question.wrongAnswerFour

            binding.scoreQuestion.text = profile?.score.toString()

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.button1 -> checkAnswer(question, binding.button1)
                    R.id.button2 -> checkAnswer(question, binding.button2)
                    R.id.button3 -> checkAnswer(question, binding.button3)
                    R.id.button4 -> checkAnswer(question, binding.button4)
                }
            }

        } catch (e: IllegalArgumentException) {
            countDownTimer?.cancel()
            val action = GameFragmentDirections.actionGameFragmentToHomeFragment("Вы ответили на все вопросы!")
            findNavController().navigate(action)
            e.printStackTrace()
        }
    }

    private fun checkAnswer(question: Question, button: Button) {
        binding.button1.isClickable = false
        binding.button2.isClickable = false
        binding.button3.isClickable = false
        binding.button4.isClickable = false

        if (questionList.checkCorrectAnswer(question, button.text.toString())) {
            Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
            addScore(gameLevel)
        } else {
            Toast.makeText(context, "Неправильно!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer() {
        when (gameLevel) {
            Level.EASY -> timeLeftInMillis = 60000
            Level.MEDIUM -> timeLeftInMillis = 60000
            Level.HARD -> timeLeftInMillis = 60000
        }

        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                val action = GameFragmentDirections.actionGameFragmentToHomeFragment("Время истекло")
                findNavController().navigate(action)
            }
        }.start()
    }

    private fun addScore(level: Level) {
        val score = binding.scoreQuestion.text.toString().toInt()

        when (level) {
            Level.EASY -> profile?.score = score + 1
            Level.MEDIUM -> profile?.score = score + 2
            Level.HARD -> profile?.score = score + 3
        }

        DbProfile.getInstance(requireContext())?.appDB?.profileDao()?.update(profile)
        CurrentProfile.instance?.currentProfile = profile
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        binding.timerTv.text = timeLeftFormatted
    }
}