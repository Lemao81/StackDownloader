package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.stackdownloader.ui.search.SearchViewModel
import org.joda.time.DateTime
import java.util.*

class SetPeriodUseCase {
    fun today(viewModel: SearchViewModel) {
        viewModel.fromDate.set(Date())
        viewModel.toDate.set(Date())
    }

    fun lastWeek(viewModel: SearchViewModel) {
        viewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
        viewModel.toDate.set(Date())
    }

    fun lastMonth(viewModel: SearchViewModel) {
        viewModel.fromDate.set(DateTime().minusMonths(1).toDate())
        viewModel.toDate.set(Date())
    }
}