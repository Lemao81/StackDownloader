package com.jueggs.stackdownloader.ui.search.usecase

import com.jueggs.stackdownloader.ui.search.viewmodel.SearchCriteriaViewModel
import org.joda.time.DateTime
import java.util.*

class SetPeriodUseCase {
    fun today(viewModel: SearchCriteriaViewModel) {
        viewModel.fromDate.set(Date())
        viewModel.toDate.set(Date())
    }

    fun lastWeek(viewModel: SearchCriteriaViewModel) {
        viewModel.fromDate.set(DateTime().minusWeeks(1).toDate())
        viewModel.toDate.set(Date())
    }

    fun lastMonth(viewModel: SearchCriteriaViewModel) {
        viewModel.fromDate.set(DateTime().minusMonths(1).toDate())
        viewModel.toDate.set(Date())
    }
}