package com.cryovac.calc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spanned;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.widget.ScrollView;
import android.widget.TextView;

public class DialogBox{
	Context context;
	TextView textView;
	ScrollView scrollView;
	Spanned spanned;

	public DialogBox(Context context){
		this.context = context;
	}
	
	public void showDialog(String string){
		this.spanned = SpannedString.valueOf(string);
		builtDialog();
	}
	
	
	public void showDialog(Spanned spanned){
		this.spanned = spanned;
		builtDialog();
	}
	
	private void builtDialog(){
		this.scrollView = (ScrollView)(LayoutInflater.from(context).inflate(R.layout.dialog_box, null));
		this.textView = (TextView)this.scrollView.findViewById(R.id.dialog_box_text_view);
		this.textView.setText(this.spanned);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder
		.setView(this.scrollView)
		.setCancelable(true)
		.setNeutralButton("Ok",
		  new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 dialog.dismiss();
			}
		  });
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

}
