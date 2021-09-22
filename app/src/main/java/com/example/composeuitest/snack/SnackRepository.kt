package com.example.composeuitest.snack

import com.example.composeuitest.base.Result

interface SnackRepository {

    suspend fun getSnacks():Result<List<SnackCollection>>
}