package com.test.grainchainapplication.modules


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.test.grainchainapplication.R
import com.test.grainchainapplication.databinding.FragmentAddBinding
import com.test.grainchainapplication.models.Contact
import java.io.IOException


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    var imageBitmap: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                GALLERY_REQUEST -> {
                    val selectedImage = data!!.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, selectedImage)
                        binding.fragmentAddPic.setImageBitmap(bitmap)
                        imageBitmap = bitmap

                    } catch (e: IOException) {
                        Log.i("TAG", "Some exception $e")
                    }

                }
            }
    }

    fun clearUI() {
        binding.fragmentAddPic.setImageResource(0)
        binding.fragmentAddPhone.text = Editable.Factory.getInstance().newEditable("")
        binding.fragmentAddName.text = Editable.Factory.getInstance().newEditable("")
        binding.fragmentAddLastName.text = Editable.Factory.getInstance().newEditable("")
        binding.fragmentAddAge.text = Editable.Factory.getInstance().newEditable("")
        binding.fragmentAddPhone.text = Editable.Factory.getInstance().newEditable("")
    }

    private fun setUpUI() {
        binding.fragmentAddButton.setOnClickListener {
            addContact()
        }
        binding.fragmentAddPic.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
    }

    private fun addContact(){
        if (isValidContact()) {
            (context as TabActivity).listContacts.add(Contact(
                imageBitmap!!,
                binding.fragmentAddName.text.toString(),
                binding.fragmentAddLastName.text.toString(),
                binding.fragmentAddAge.text.toString(),
                binding.fragmentAddPhone.text.toString()
            ))
            Log.wtf("bitmap", imageBitmap.toString())
            clearUI()
            Toast.makeText(context, "Contact added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidContact(): Boolean {
        when {
            imageBitmap == null -> {
                showMessage("Must select image from Gallery")?.show()
                return false
            }
            binding.fragmentAddName.text.toString() == "" -> {
                showMessage("Name is required")?.show()
                return false
            }
            binding.fragmentAddLastName.text.toString() == "" -> {
                showMessage("Last name is required")?.show()
                return false
            }
            binding.fragmentAddAge.text.toString() == "" -> {
                showMessage("Age is required")?.show()
                return false
            }
            binding.fragmentAddAge.text.toString().length > 2 -> {
                showMessage("Age must be 2 digits")?.show()
                return false
            }
            binding.fragmentAddPhone.text.toString() == "" -> {
                showMessage("Phone is required")?.show()
                return false
            }
            binding.fragmentAddPhone.text.toString().length != 10 -> {
                showMessage("Phone must have 10 digits")?.show()
                return false
            }
        }
        return true
    }

    private fun showMessage(message: String): AlertDialog? {
        val alertDialog = context?.let { AlertDialog.Builder(it).create() }
        alertDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog?.setMessage(message)
        alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
            // Dismiss dialog
            alertDialog.dismiss()
        }
        return alertDialog
    }

    companion object {
        /**
         * Gallery Request Code
         */
        private val GALLERY_REQUEST = 1

        /**
         * Returns a new instance of this fragment
         */
        fun newInstance(): AddFragment {
            return AddFragment()
        }
    }

}
