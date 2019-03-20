package com.test.grainchainapplication.modules

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.test.grainchainapplication.R
import com.test.grainchainapplication.databinding.ActivityTabBinding
import com.test.grainchainapplication.models.Contact
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TabActivity : AppCompatActivity(), HasSupportFragmentInjector {

    /**
     * The [androidx.viewpager.widget.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * androidx.fragment.app.FragmentStatePagerAdapter.
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    var listContacts = mutableListOf<Contact>()

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var binding: ActivityTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.ter_stegen), "Marc André", "ter Stegen", "25", "111111111"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.semedo), "Nelson", "Semedo", "23", "222222222"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.pique), "Gerard", "Pique", "32", "3333333333"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.rakitic), "Ivan", "Rakitic", "31", "4444444444"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.busquets), "Sergio", "Busquets", "31", "555555555"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.todibo), "Jean-Clair", "Todibo", "31", "6666666666"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.coutinho), "Philippe", "Coutinho", "27", "7777777777"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.arthur), "Arthur", "Melo", "21", "8888888888"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.suarez), "Luis", "Suarez", "32", "9999999999"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.messi), "Lionel", "Messi", "31", "1010101010"))
        listContacts.add(Contact(BitmapFactory.decodeResource(resources, R.drawable.dembele), "Ousmane", "Dembele", "21", "1111111111"))
        // Set up the ViewPager with the sections adapter.
        binding.container.adapter = mSectionsPagerAdapter


        binding.container.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    val fragment = binding.container.adapter!!.instantiateItem(binding.container, position) as SearchFragment
                    fragment.onResume()
                    binding.container.adapter!!.notifyDataSetChanged()
                } else if (position == 1) {
                    val fragment = binding.container.adapter!!.instantiateItem(binding.container, position) as AddFragment
                    fragment.clearUI()
                    binding.container.adapter!!.notifyDataSetChanged()
                }
            }
        })

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return SearchFragment.newInstance()
            } else if (position == 1) {
                return AddFragment.newInstance()
            }
            return Fragment()
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }
    }

    override fun onBackPressed() {
        showMessage("Are you sure you want to exit app?, \n you will have to login again").show()
    }

    private fun showMessage(message: String): AlertDialog {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO") { _, _ ->
            alertDialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES") { _, _ ->
            // Dismiss dialog
            finish()
        }
        return alertDialog
    }
}
