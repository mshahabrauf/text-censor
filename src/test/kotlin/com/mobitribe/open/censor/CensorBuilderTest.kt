package com.mobitribe.open.censor

import com.mobitribe.open.censor.type.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CensorBuilderTest {

    @Test
    fun `getCensoredText should apply DomainCensor correctly`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val domainCensor = DomainCensor().setPlaceholder("[DOMAIN]")
        val textToCensor = "Visit our website at example.com"

        // Act
        val result = censorBuilder.addCensors(domainCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Visit our website at [DOMAIN]", result)
    }

    @Test
    fun `getCensoredText should apply EmailCensor correctly`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val emailCensor = EmailCensor().setPlaceholder("[EMAIL]")
        val textToCensor = "Contact us at john.doe@example.com"

        // Act
        val result = censorBuilder.addCensors(emailCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Contact us at [EMAIL]", result)
    }

    @Test
    fun `getCensoredText should apply OffenseCensor correctly`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val offenseCensor = OffenseCensor().setPlaceholder("[OFFENSE]")
        val textToCensor = "Please refrain from using offensive words like Airbnb and Facebook."

        // Act
        val result = censorBuilder.addCensors(offenseCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Please refrain from using offensive words like [OFFENSE] and [OFFENSE].", result)
    }

    @Test
    fun `getCensoredText should apply PhoneCensor correctly`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val phoneCensor = PhoneCensor().setPlaceholder("[PHONE]")
        val textToCensor = "Contact us at +1 (123) 456-7890 for support."

        // Act
        val result = censorBuilder.addCensors(phoneCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Contact us at [PHONE] for support.", result)
    }

    @Test
    fun `getCensoredText should handle multiple censors`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val domainCensor = DomainCensor().setPlaceholder("[DOMAIN]")
        val emailCensor = EmailCensor().setPlaceholder("[EMAIL]")
        val textToCensor = "Contact us at john.doe@example.com or visit www.example.com for more info."

        // Act
        val result = censorBuilder.addCensors(emailCensor, domainCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Contact us at [EMAIL] or visit [DOMAIN] for more info.", result)
    }

    @Test
    fun `big text censor test`() {
        // Arrange
        val censorBuilder = CensorBuilder
        val domainCensor = DomainCensor().setPlaceholder("[DOMAIN]")
        val emailCensor = EmailCensor().setPlaceholder("[EMAIL]")
        val phoneCensor = PhoneCensor().setPlaceholder("[PHONE]")
        val offenseCensor = OffenseCensor().setPlaceholder("[OFFENSIVE]")
        val textToCensor = "Email\n" +
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
                "Google   . com = To be removed "

        // Act
        val result = censorBuilder.addCensors(emailCensor, domainCensor, phoneCensor, offenseCensor)
            .getCensoredText(textToCensor)

        // Assert
        assertEquals("Email\n" +
                "Email = > check email without considering spaces\n" +
                "Ex.\n" +
                "[EMAIL] = To be removed\n" +
                "[EMAIL] To be removed \n" +
                "\n" +
                "Remove email that are using those extensions:\n" +
                "\n" +
                "Ex \n" +
                "[DOMAIN] = To be removed\n" +
                "ciaobox . ch = To be removed\n" +
                "\n" +
                "Phone numbers\n" +
                "Telephone numbers: check for 3 or more digits together, without considering \"spaces\" or \"-\" \n" +
                "\n" +
                "Ex \n" +
                "[PHONE]-[PHONE]-[PHONE] = As is removed | \n" +
                "23 24 4 = To be removed | \n" +
                "We are 10 people = Do not remove | \n" +
                "We are [PHONE] people = To be removed | \n" +
                "We are 1 0    1 people = To be removed | \n" +
                "We are 1-0-1 people = To be removed | \n" +
                "We are coming on [PHONE]2 = To be removed |\n" +
                "I born on 10 01 90 = To be removed because include spaces.\n" +
                "\n" +
                "Words\n" +
                "Add censorship on Chat for those words: \n" +
                "\n" +
                "[OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], [OFFENSIVE], \$, [OFFENSIVE], @, €\n" +
                "\n" +
                "Cancel one word before the following extension\n" +
                "(.com .ch .it .de .fr  .ar .us . io .com .it .ch .de .es) without considering spaces\n" +
                "\n" +
                "Ex.\n" +
                "[DOMAIN] = To be removed \n" +
                "[OFFENSIVE]   . com = To be removed ", result)
    }
}
