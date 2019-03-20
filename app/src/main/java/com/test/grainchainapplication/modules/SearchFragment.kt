package com.test.grainchainapplication.modules

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.grainchainapplication.R
import com.test.grainchainapplication.cache.PreferencesCacheRepository
import com.test.grainchainapplication.databinding.FragmentSearchBinding
import com.test.grainchainapplication.models.Contact
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject

/**
 * Created by Luis Vargas on 3/19/19.
 */

class SearchFragment : Fragment(), OnContactLongClick {

    @Inject
    lateinit var preferenceDataStore: PreferencesCacheRepository

    private lateinit var binding: FragmentSearchBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUI()
    }

    override fun onResume() {
        super.onResume()
        setUpUI()
    }

    private fun setUpUI() {

        binding.fragmentSearchUsername.text = preferenceDataStore.getUsername()
        val mIngredientArrayList = ArrayList<Contact>()
        for (i in 0 until (context as TabActivity).listContacts.size){
            mIngredientArrayList.add((context as TabActivity).listContacts[i])
        }
        val viewAdapter = SearchAdapter(context!!, mIngredientArrayList, listener = this)
        val manager = LinearLayoutManager(context)
        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = viewAdapter
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Call back the Adapter with current character to Filter
                viewAdapter.filter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onLongClick(contact: Contact) {
        removeContact("Do you want to remove contact?", contact)?.show()

    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private fun removeContact(message: String, contact: Contact): AlertDialog? {
        val alertDialog = context?.let { AlertDialog.Builder(it).create() }
        alertDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog?.setMessage(message)
        alertDialog?.setButton(AlertDialog.BUTTON_NEGATIVE, "NO") { _, _ ->
            alertDialog.dismiss()
        }
        alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE, "YES") { _, _ ->
            // Dismiss dialog
            (context as TabActivity).listContacts.remove(contact)
            setUpUI()
            alertDialog.dismiss()

        }
        return alertDialog
    }
}