package com.example.bilaleluneis.musiclibrarylist;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * @author Bilal El Uneis
 * @since Jan 5, 2015
 */

/**
 * simple example on how to access Music library on Android and
 * display the result in ListView
 * Thanks to Derek Banas youtube vid that allowed me to learn
 * how to create simple List View
 * https://www.youtube.com/watch?v=rhj4_KBD6BQ&index=5&list=PLGLfVvz_LVvSPjWpLPFEfOCbezi6vATIh
 * also thanks to http://code.tutsplus.com/tutorials/create-a-music-player-on-android-project-setup--mobile-22764
 * I was able to learn how to get the Music Library Content from Android Device
 * finally thanks to https://github.com/alexdantas/kure-music-player
 * I learned how to filter the result to get only Music List!
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView musicListView = (ListView) findViewById(R.id.libraryListView);
        musicListView.setAdapter(getMusicLibraryList());

    }

    /**
     *
     * @return ListAdapter containing the list of songs in
     * user's Android Music Library
     */
    private ListAdapter getMusicLibraryList(){

        String[] from = {MediaStore.MediaColumns.TITLE};
        int[] to = {android.R.id.text1};
        ContentResolver contentResolver = getContentResolver();
        Uri musicLibUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        /*thanks to https://github.com/alexdantas/kure-music-player*/
        final String musicsOnly = MediaStore.Audio.Media.IS_MUSIC + "=1";
        Cursor cursor = contentResolver.query(musicLibUri, null, musicsOnly, null, MediaStore.Audio.Media.TITLE);
        ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        return adapter;

    }
}