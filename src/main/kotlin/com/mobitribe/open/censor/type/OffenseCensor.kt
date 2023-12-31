package com.mobitribe.open.censor.type

class OffenseCensor: Censor() {

    var offensiveWords = arrayListOf(
        "Brobnb",
        "Booking",
        "Instagram",
        "Vrbo",
        "Facebook",
        "Airbnb",
        "Kingdombnb",
        "Gmail",
        "Hotmail",
        "Yahoo",
        "Gmx",
        "Outlook",
        "Telegram",
        "Social",
        "AOL",
        "cloud",
        "cash",
        "sex",
        "fuck",
        "Google",
        "Evolve",
        "$",
        "USD",
        "@",
        "€"
    ) // Add your list of offensive words here
    var offensivePattern: String? = null
    override fun getCensoredText(text: String): String {
        val filteredText = offensivePattern?.let {
            Regex(it).replace(text, placeHolder)
        } ?:run {
            Regex(
                pattern = "\\b(?:${offensiveWords.joinToString("|") { Regex.escape(it) }})\\b",
                option = RegexOption.IGNORE_CASE
            ).replace(text,placeHolder)
        }
        return filteredText
    }

    override fun changePattern(pattern: String): Censor {
        this.offensivePattern = pattern
        return this
    }

    fun changeOffensiveWordsList(offensiveWords: ArrayList<String>): Censor {
        this.offensiveWords = offensiveWords
        return this
    }
}