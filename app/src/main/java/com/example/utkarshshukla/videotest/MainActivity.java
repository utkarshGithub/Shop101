package com.example.utkarshshukla.videotest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    public static ArrayList< Integer > checkList;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        recyclerView = ( RecyclerView ) findViewById ( R.id.my_recycler_view );
        checkNewDate ( );

        final ArrayList< Integer > mylist = new ArrayList< Integer > ( );
        int i = 0;
        while ( i < 5 ) {
            mylist.add ( i );
            i++;
        }
        checkList = new ArrayList< Integer > ( );
        manager = new LinearLayoutManager ( this );
        manager.setOrientation ( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager ( manager );
        final MyAdapter myAdater = new MyAdapter ( mylist, this, recyclerView );
        recyclerView.setAdapter ( myAdater );
        FloatingActionButton fab = ( FloatingActionButton ) findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                // Click action
                mylist.add ( mylist.size ( ) );
                myAdater.notifyDataSetChanged ( );
                Toast.makeText ( getApplicationContext ( ), "NEW ITEM ADDED", Toast.LENGTH_LONG ).show ( );
            }
        } );

    }

    private void checkNewDate ( ) {
        SharedPreferences sharedPref = getPreferences ( Context.MODE_PRIVATE );
        String storedDate = sharedPref.getString ( "DATE", "NO LATEST DATE" );
        Date today = new Date ( );

        if ( !String.valueOf ( today ).equals ( storedDate ) ) {
            AlertDialog.Builder builder = new AlertDialog.Builder ( this );
            LayoutInflater inflater = getLayoutInflater ( );
            builder.setView ( inflater.inflate ( R.layout.dialog_signin, null ) ).create ( ).show ( );

        }
        storeDate ( );
    }

    private void storeDate ( ) {
        SharedPreferences sharedPref = getSharedPreferences ( "Shop101", MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit ( );
        editor.putString ( "DATE", String.valueOf ( new Date ( ) ) );
        editor.commit ( );
    }

    @Override
    public void onBackPressed ( ) {
        super.onBackPressed ( );
        Toast.makeText ( getApplicationContext ( ), "Size of List " + checkList.size ( ), Toast.LENGTH_LONG ).show ( );
    }

    @Override
    protected void onUserLeaveHint ( ) {
        super.onUserLeaveHint ( );
        Toast.makeText ( getApplicationContext ( ), "Size of List " + checkList.size ( ), Toast.LENGTH_LONG ).show ( );
    }


    @Override
    protected void onDestroy ( ) {
        super.onDestroy ( );
        checkList = null;
    }
}
