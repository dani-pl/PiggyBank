package com.danipl.piggybank.android.util

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.danipl.piggybank.android.theme.PiggyBankTheme

@Composable
fun PreviewTheme(
    content: @Composable () -> Unit,
) {
    PiggyBankTheme {
        Surface {
            content()
        }
    }
}
