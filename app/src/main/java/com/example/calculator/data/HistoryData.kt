package com.example.calculator.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HistoryList : ArrayList<String>(), Parcelable
