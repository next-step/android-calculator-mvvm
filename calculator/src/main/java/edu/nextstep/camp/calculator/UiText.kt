package edu.nextstep.camp.calculator

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicString(val value: String): UiText()

    class StringResource(@StringRes val resId: Int, vararg val args: Any): UiText() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is StringResource) return false
            return this.resId == other.resId && this.args.contentEquals(other.args)
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
