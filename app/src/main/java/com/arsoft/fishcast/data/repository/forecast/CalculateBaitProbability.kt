package com.arsoft.fishcast.data.repository.forecast

import com.arsoft.fishcast.data.request.forecast.Result

class CalculateBaitProbability {

    companion object {

        fun getFromAll(result: Result): Result {
            for(item in result.list) {
                if (item.wind.deg > 191 && item.wind.deg < 348) {
                    item.baitProbabilityPercentage = 30
                } else {
                    item.baitProbabilityPercentage = 60
                }
            }

            return result
        }

    }
}