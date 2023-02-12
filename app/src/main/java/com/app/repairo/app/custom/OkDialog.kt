package com.app.repairo.app.custom

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.app.repairo.app.R


 object OkDialog {

    fun show(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.alert_dialog, null)
        builder.setView(view)
        builder.setCancelable(true)
        val alert = builder.create()
        val textView = view.findViewById<TextView>(R.id.alertTitle) as TextView
        textView.text = message
        view.findViewById<Button>(R.id.alertOk).setOnClickListener { alert.dismiss() }
        alert.show()
    }

    public interface OkDialogListener {
        fun okDialogClick()
    }

}

