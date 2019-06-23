
package com.nsc9012.sportsplayer.features.login

import com.nsc9012.sportsplayer.UnitTest
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

    private val authenticator = Authenticator()

    @Test fun `returns default value`() {
        authenticator.userLoggedIn() shouldBe true
    }
}
