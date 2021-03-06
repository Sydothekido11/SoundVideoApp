package ctec.soundvideoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import android.net.Uri;

public class VideoActivity extends AppCompatActivity
{

    private Button homeButton;
    private VideoView myPlayer;
    private MediaController myVideoController;
    private Uri videoLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        myPlayer = (VideoView) findViewById(R.id.videoView);
        homeButton = (Button) findViewById(R.id.returnToMedia);

        videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bigbang );
        myVideoController = new MediaController(this);

        setupMedia();
        setupListeners();
    }

    private void setupMedia()
    {
        myPlayer.setMediaController(myVideoController);
        myPlayer.setVideoURI(videoLocation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListeners()
    {
        homeButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View buttonView)
          {

              Intent returnIntent = new Intent();
              setResult(RESULT_OK, returnIntent);
              finish();

          }
        });
    }
}
