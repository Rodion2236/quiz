package com.rodion2236.quiz.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodion2236.quiz.R
import com.rodion2236.quiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var timer: CountDownTimer

    private val questionsAndAnswers = mapOf(
        "В каком году была основана компания Google?" to listOf("1998", "2000", "2002", "2004"),
        "Какой язык программирования создал Ян Валент?" to listOf("Python", "Java", "C++", "PHP"),
        "Как называется операционная система, разработанная компанией Apple?" to listOf("iOS", "Windows", "Linux", "Android"),
        "Какие из этих языков программируют на платформе .NET?" to listOf("C#", "Java", "Python", "Ruby"),
        "Какая из этих баз данных является наиболее популярной?" to listOf("MySQL", "Oracle", "SQL Server", "PostgreSQL")
    )

    private var questionIndex = 0
    private var correctAnswers = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding
            .inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val answerButton1 = binding.button1
        val answerButton2 = binding.button2
        val answerButton3 = binding.button3
        val answerButton4 = binding.button4

        val answerButtons = arrayOf(answerButton1, answerButton2, answerButton3, answerButton4)
        answerButtons.forEach { button ->
            button.setOnClickListener {
                checkAnswer(button.text.toString())
                loadNextQuestion()
            }
        }

        loadNextQuestion()

        timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(timeM: Long) {
                binding.timerTv.text = timeM.toString()
            }

            override fun onFinish() {
                showResults()
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadNextQuestion() {
        val currentQuestion = questionsAndAnswers.entries.elementAt(questionIndex)
        questionIndex++

        val answerButtons = listOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4
        )

        binding.questionTv.text = currentQuestion.key
        currentQuestion.value.shuffled().forEachIndexed { index, answer ->
            answerButtons[index].text = answer
        }
    }

    private fun checkAnswer(answer: String) {
        val correctAnswer = questionsAndAnswers.entries.elementAt(questionIndex - 1).value.first()
        if (answer == correctAnswer) {
            correctAnswers++
        }
    }

    private fun showResults() {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, ResultFragment())?.commit()

        timer.cancel()
    }
}
