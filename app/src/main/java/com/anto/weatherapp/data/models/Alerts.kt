package com.anto.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class Alerts(
    @SerializedName("alert")
    var alert: ArrayList<Alert> = arrayListOf()

)
