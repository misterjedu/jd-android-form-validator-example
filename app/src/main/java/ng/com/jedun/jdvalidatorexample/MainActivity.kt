package ng.com.jedun.jdvalidatorexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.misterjedu.jdformvalidator.*
import ng.com.jedun.jdvalidatorexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateFields()

        binding.registerButton.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                startActivity(this)
            }
        }

    }


    private fun validateFields() {
        val fields: MutableList<JDataClass> = mutableListOf(
            JDataClass(
                editText = binding.emailEt,
                editTextInputLayout = binding.emailTil,
                errorMessage = JDErrorConstants.INVALID_EMAIL_ERROR,
                validator = { it.jdValidateEmail(it.text.toString()) }
            ),
            JDataClass(
                editText = binding.passwordEt,
                editTextInputLayout = binding.passwordTil,
                errorMessage = JDErrorConstants.INVALID_PASSWORD_ERROR,
                validator = { it.jdValidatePassword(it.text.toString()) }
            ),
            JDataClass(
                editText = binding.retypePasswordEt,
                editTextInputLayout = binding.retypePasswordTil,
                errorMessage = JDErrorConstants.PASSWORD_DOES_NOT_MATCH,
                validator = {
                    it.validateConfirmPassword(
                        binding.passwordEt,
                        binding.retypePasswordEt
                    )
                }
            )
        )

        JDFormValidator.Builder()
            .addFieldsToValidate(fields)
            .removeErrorIcon(true)
            .viewsToEnable(mutableListOf(binding.registerButton))
            .watchWhileTyping(true)
            .build()
    }
}