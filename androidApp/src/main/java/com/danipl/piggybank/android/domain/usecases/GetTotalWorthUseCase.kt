package com.danipl.piggybank.android.domain.usecases

import com.danipl.piggybank.android.domain.Asset
import javax.inject.Inject

class GetTotalWorthUseCase @Inject constructor() {

    operator fun invoke(assets: List<Asset>): String {
        var acc = 0.00
        assets.forEach {
            acc += it.amount.toDouble()
        }
        return acc.toString()
    }
}
