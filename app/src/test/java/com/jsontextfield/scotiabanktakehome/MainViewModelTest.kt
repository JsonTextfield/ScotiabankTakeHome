package com.jsontextfield.scotiabanktakehome

import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun testSearchText() {
        val mainViewModel = MainViewModel()
        assertEquals("", mainViewModel.mainState.value.searchText)

        mainViewModel.onSearchTextChanged("hello")
        assertEquals("hello", mainViewModel.mainState.value.searchText)
    }
}