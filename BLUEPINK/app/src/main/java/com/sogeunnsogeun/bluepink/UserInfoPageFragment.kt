package com.sogeunnsogeun.bluepink

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_user_info_page.*

/**
 * A simple [Fragment] subclass.
 */
class UserInfoPageFragment : AppCompatDialogFragment() {

    lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        myView = activity!!.layoutInflater.inflate(R.layout.fragment_user_info_page, LinearLayout(activity), false)

        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setNegativeButton("취소", { dialog, which -> dialog.dismiss()})
            .setPositiveButton("적용", { dialog, which ->
                if(user_name_edit.text.isNotEmpty()){
                    UserInfo.set(UserInfo.NAME, user_name_edit.text.toString())
                }
            })
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user_name_edit.hint = UserInfo.get(UserInfo.NAME).toString()

    }
}
