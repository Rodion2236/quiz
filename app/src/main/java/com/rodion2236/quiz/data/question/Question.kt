package com.rodion2236.quiz.data.question

data class Question(
    var level: Level,
    var quest: String,
    var wrongAnswerOne: String,
    var wrongAnswerTwo: String,
    var wrongAnswerThree: String,
    var wrongAnswerFour: String,
    var answer: String
) {
    var state: State = State.NOT_SHOW
}