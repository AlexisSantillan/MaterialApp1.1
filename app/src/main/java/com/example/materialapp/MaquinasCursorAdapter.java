package com.example.materialapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;



public class MaquinasCursorAdapter extends CursorAdapter {

    public MaquinasCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_maquina, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {


        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        final ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);


        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.NOMBRE));
        @SuppressLint("Range") String avatarUri = cursor.getString(cursor.getColumnIndex(MaquinaContract.MaquinaEntry.MESES));


    }
}
