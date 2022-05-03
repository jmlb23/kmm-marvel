package com.github.jmlb23.mvvm

import kotlin.test.Test
import kotlin.test.assertTrue

class IosTest {
    @Test
    fun testModule(){
        initKoin()
        IosComponent().provideCharacterListView.let {
            it.nextPage()
            it.getElement {
                assertTrue(it.isNotEmpty())
            }
        }
    }
}