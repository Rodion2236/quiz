package com.rodion2236.quiz.data.question

class QuestionList {
    private var questions: MutableList<Question> = mutableListOf(
        Question(Level.EASY,
            "В каком году была основана ФИФА?",
            "1904",
            "1912",
            "1924",
            "1936",
            "1904"),

        Question(Level.EASY,
            "Кто является рекордсменом по количеству забитых мячей в истории Лиги Чемпионов УЕФА?",
            "Lionel Messi",
            "Cristiano Ronaldo",
            "Raul Gonzalez",
            "Filippo Inzaghi",
            "Cristiano Ronaldo"),

        Question(Level.EASY,
            "Какая команда является рекордсменом по количеству выигранных Лиг Чемпионов УЕФА?",
            "Real Madrid",
            "Barcelona",
            "Bayern Munich",
            "AC Milan",
            "Real Madrid"),

        Question(Level.EASY,
            "Какой клуб является самым титулованным в английской Премьер-лиге?",
            "Manchester United",
            "Liverpool",
            "Arsenal",
            "Chelsea",
            "Manchester United"),

        Question(Level.EASY,
            "Кто является рекордсменом по количеству забитых мячей за национальную сборную?",
            "Lionel Messi",
            "Cristiano Ronaldo",
            "Pele",
            "Miroslav Klose",
            "Miroslav Klose"),

        Question(Level.MEDIUM,
            "Какой год был первым, когда состоялся Чемпионат мира по футболу?",
            "1926",
            "1930",
            "1934",
            "1938",
            "1930"),

        Question(Level.MEDIUM,
            "В каком году были основаны Олимпийские игры в Древней Греции?",
            "776 год до н.э.",
            "500 год до н.э.",
            "200 год до н.э.",
            "50 год до н.э.",
            "776 год до н.э."),

        Question(Level.MEDIUM,
            "В каком году была основана Национальная хоккейная лига (NHL)?",
            "1902",
            "1917",
            "1925",
            "1933",
            "1917"),

        Question(Level.MEDIUM,
            "Какое животное является символом Олимпийских игр?",
            "Медведь",
            "Тигр",
            "Сокол",
            "Рысь",
            "Сокол"),

        Question(Level.MEDIUM,
            "Какой год стал первым для проведения Современных олимпийских игр?",
            "1866",
            "1896",
            "1908",
            "1924",
            "1896"),

        Question(Level.HARD,
            "Кто является автором серии книг «Гарри Поттер»?",
            "Доминик Торетто",
            "Джоан Роулинг",
            "Джеймс Паттерсон",
            "Дэн Браун",
            "Джоан Роулинг"),

        Question(Level.HARD,
            "В какой школе учится Гарри Поттер?",
            "Хогвартс",
            "Илверморни",
            "Беверли",
            "Для дебилов",
            "Хогвартс"),

        Question(Level.HARD,
            "Кого играет актер Руперт Гринт?",
            "Гарри Поттер",
            "Рон Уизли",
            "Сириус Блэк",
            "Альбус Дамблдор",
            "Рон Уизли"),

        Question(Level.HARD,
            "Как зовут бомжа с моего подвала?",
            "Вася",
            "Игорь",
            "Борис",
            "Михалыч",
            "Михалыч"),

        Question(Level.HARD,
            "У меня закончились вопросы?",
            "Да",
            "Нет",
            "Лысого ответ",
            "Ночной бабочки аргумент",
            "Да"),
    )

    fun getQuestion(level: Level): Question {
        val filteredQuestions = questions.filter { it.level == level && it.state == State.NOT_SHOW }
        if (filteredQuestions.isNotEmpty()) {
            val question = filteredQuestions[(filteredQuestions.indices).random()]
            question.state == State.SHOW
            questions.add(question)
            return question
        } else {
            throw IllegalArgumentException("")
        }
    }

    fun checkCorrectAnswer(question: Question, answer: String): Boolean {
        return question.answer == answer
    }
}