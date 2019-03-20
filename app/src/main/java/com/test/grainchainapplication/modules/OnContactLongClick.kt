package com.test.grainchainapplication.modules

import com.test.grainchainapplication.models.Contact

/**
 * Created by Luis Vargas on 3/20/19.
 */

interface OnContactLongClick{
    fun onLongClick(contact: Contact)
}