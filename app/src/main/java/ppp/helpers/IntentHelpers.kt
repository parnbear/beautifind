package ppp.helpers

import android.app.Activity

fun Activity.enforceInputs(vararg inputs: String) {
    val intent = this.intent
    inputs.forEach {
        if (!intent.hasExtra(it)) throw Exception("MUST SEND $it")
    }
}