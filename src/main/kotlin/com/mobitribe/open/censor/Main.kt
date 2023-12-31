package com.mobitribe.open.censor

import com.mobitribe.open.censor.type.*

fun main(args: Array<String> = arrayOf("[placeholder]","Email\n" +
        "Email = > check email without considering spaces\n" +
        "Ex.\n" +
        "apallini@gmail.com = To be removed\n" +
        "apallini @ gmail.com To be removed \n" +
        "\n" +
        "Remove email that are using those extensions:\n" +
        "\n" +
        "Ex \n" +
        "www.ciaobox.ch = To be removed\n" +
        "ciaobox . ch = To be removed\n" +
        "\n" +
        "Phone numbers\n" +
        "Telephone numbers: check for 3 or more digits together, without considering \"spaces\" or \"-\" \n" +
        "\n" +
        "Ex \n" +
        "123-122-322 = As is removed | \n" +
        "23 24 4 = To be removed | \n" +
        "We are 10 people = Do not remove | \n" +
        "We are 101 people = To be removed | \n" +
        "We are 1 0    1 people = To be removed | \n" +
        "We are 1-0-1 people = To be removed | \n" +
        "We are coming on 2022 = To be removed |\n" +
        "I born on 10 01 90 = To be removed because include spaces.\n" +
        "\n" +
        "Words\n" +
        "Add censorship on Chat for those words: \n" +
        "\n" +
        "Brobnb, Booking, Instagram, Vrbo, Facebook, Airbnb, Kingdombnb, Gmail, Hotmail, Yahoo, Gmx, Outlook, Telegram, Social, AOL, cloud, cash, sex, Google, Evolve, \$, USD, @, €\n" +
        "\n" +
        "Cancel one word before the following extension\n" +
        "(.com .ch .it .de .fr  .ar .us . io .com .it .ch .de .es) without considering spaces\n" +
        "\n" +
        "Ex.\n" +
        "Google.com = To be removed \n" +
        "Google   . com = To be removed ")) {
    println("Hello Censor!")
    val censoredText = CensorBuilder.addCensors(
        EmailCensor().setPlaceholder(args.get(0)),
        DomainCensor().setPlaceholder(args.get(0)),
        PhoneCensor().setPlaceholder(args.get(0)),
        OffenseCensor().setPlaceholder(args.get(0))
    ).getCensoredText(args.get(1))

    println(censoredText)
}