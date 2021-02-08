package ng.com.jedun.jdvalidatorexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.misterjedu.jdformvalidator.*
import ng.com.jedun.jdvalidatorexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerButton.setOnClickListener {
            if (validateFields()) {
                Toast.makeText(this, "Yes, validated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No, Not validated", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateFields(): Boolean {
        val fields: MutableList<JDataClass> = mutableListOf(
            JDataClass(
                editText = binding.emailEt,
                editTextInputLayout = null,
                errorMessage = JDErrorConstants.INVALID_EMAIL_ERROR,
                validator = { it.jdValidateEmail(it.text.toString()) }
            ),
            JDataClass(
                editText = binding.passwordEt,
                editTextInputLayout = null,
                errorMessage = JDErrorConstants.INVALID_PASSWORD_ERROR,
                validator = { it.jdValidatePassword(it.text.toString()) }
            ))

        val validator = JDFormValidator.Builder()
            .addFieldsToValidate(fields)
            .viewsToEnable(mutableListOf(binding.registerButton))
            .build()

        return validator.areAllFieldsValidated
    }
}