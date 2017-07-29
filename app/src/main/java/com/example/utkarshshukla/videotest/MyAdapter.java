package com.example.utkarshshukla.videotest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.utkarshshukla.videotest.MainActivity.checkList;

/**
 * Created by Utkarsh Shukla on 29/07/17.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private final ArrayList< Integer > list;
    private final MainActivity mainActivity;
    private final RecyclerView recyclerView;

    public MyAdapter ( ArrayList< Integer > mylist, MainActivity mainActivity, RecyclerView recyclerView ) {
        this.list = mylist;
        this.mainActivity = mainActivity;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.holder0, null );

        return new myholder0 ( view );

    }

    @Override
    public void onBindViewHolder ( RecyclerView.ViewHolder holder, final int position ) {

        final myholder0 my0 = ( myholder0 ) holder;
        my0.checkBox.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {


                Toast.makeText ( mainActivity.getApplicationContext ( ), position + 1 + "  clicked", Toast.LENGTH_SHORT ).show ( );
            }
        } );

        my0.dustbin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                list.remove ( position );
                notifyDataSetChanged ( );
            }
        } );
        my0.view.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition ( position );
                CheckBox a = ( CheckBox ) holder.itemView.findViewById ( R.id.mycheckbox );


                if ( !a.isChecked ( ) )
                    a.setChecked ( true );
                else
                    a.setChecked ( false );
                checkList.add ( position );
                Toast.makeText ( mainActivity.getApplicationContext ( ), position + 1 + "  clicked", Toast.LENGTH_SHORT ).show ( );
            }

        } );


    }

    @Override
    public int getItemCount ( ) {
        return list.size ( );
    }


    class myholder0 extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView contentImage;
        ImageButton dustbin;
        View view;

        public myholder0 ( View itemView ) {
            super ( itemView );
            this.view = itemView;
            checkBox = ( CheckBox ) itemView.findViewById ( R.id.mycheckbox );
            contentImage = ( ImageView ) itemView.findViewById ( R.id.item_image );
            dustbin = ( ImageButton ) itemView.findViewById ( R.id.dust_bin );

        }
    }


}


