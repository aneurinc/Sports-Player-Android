package com.nsc9012.sportsplayer.core.functional

import com.nsc9012.sportsplayer.UnitTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class EitherTest : UnitTest() {

    @Test fun `Either Right should return correct type`() {
        val result = Either.Right("Lionel Messi")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.either({},
                { right ->
                    right shouldBeInstanceOf String::class.java
                    right shouldEqualTo "Lionel Messi"
                })
    }

    @Test fun `Either Left should return correct type`() {
        val result = Either.Left("Lionel Messi")

        result shouldBeInstanceOf Either::class.java
        result.isLeft shouldBe true
        result.isRight shouldBe false
        result.either(
                { left ->
                    left shouldBeInstanceOf String::class.java
                    left shouldEqualTo "Lionel Messi"
                }, {})
    }
}