package ng.com.jedun.jdvalidatorexample

import android.widget.EditText

fun EditText.validateConfirmPassword(
    password: EditText,
    confirmPasswordEditText: EditText
): Boolean {

    val confirmPassword = confirmPasswordEditText.text.toString().trim()

    return password.text.toString().trim() == confirmPassword
}