package com.example.warpspeedassessment.utils

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

object ExtensionFunctions {
    fun Fragment.showSnackBar(@StringRes stringMessageId: Int) {
        Snackbar.make(requireView().rootView, getString(stringMessageId), Snackbar.LENGTH_SHORT)
            .show()
    }
}