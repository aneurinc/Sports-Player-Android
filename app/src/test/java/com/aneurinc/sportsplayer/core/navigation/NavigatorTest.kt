
package com.aneurinc.sportsplayer.core.navigation

import com.aneurinc.sportsplayer.features.athletes.AthletesActivity
import com.aneurinc.sportsplayer.features.login.Authenticator
import com.aneurinc.sportsplayer.features.login.LoginActivity
import com.aneurinc.sportsplayer.AndroidTest
import com.aneurinc.sportsplayer.shouldNavigateTo
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify


class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Mock private lateinit var authenticator: Authenticator

    @Before fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test fun `should forward user to login screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test fun `should forward user to athletes screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo AthletesActivity::class
    }
}
