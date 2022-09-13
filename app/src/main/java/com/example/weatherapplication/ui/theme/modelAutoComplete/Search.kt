package com.example.weatherapplication.ui.theme.modelAutoComplete

data class Search(
    val ClientSessionId: Any,
    val Errors: List<Any>,
    val Id: String,
    val IsError: Boolean,
    val MaxAllowedUsage: Any,
    val RequestId: Any,
    val Result: List<String>,
    val StatusCode: Int,
    val UsageStatistics: Any
)