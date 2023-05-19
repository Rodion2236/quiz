package com.rodion2236.quiz.profile

class CurrentProfile private constructor() {
    var currentProfile: Profile? = null

    companion object {
        var instance: CurrentProfile? = null
            get() {
                if (field == null) {
                    field = CurrentProfile()
                }
                return field
            }
            private set
    }
}