package com.mobitribe.open.censor

import com.mobitribe.open.censor.type.*

fun main(args: Array<String>) {
    println("Hello Censor!")
    val censoredText = CensorBuilder.addCensors(
        EmailCensor().setPlaceholder(args.get(0)),
        DomainCensor().setPlaceholder(args.get(0)),
        PhoneCensor().setPlaceholder(args.get(0)),
        OffenseCensor().setPlaceholder(args.get(0))
    ).getCensoredText(args.get(1))

    println(censoredText)
}