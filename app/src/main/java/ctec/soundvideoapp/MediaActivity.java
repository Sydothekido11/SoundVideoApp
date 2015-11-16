package ctec.soundvideoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import android.media.MediaPlayer;

public class MediaActivity extends AppCompatActivity implements Runnable
{

    private Button swapButton;
    private Button playButton;
    private Button stopButton;
    private Button pauseButton;
    private SeekBar soundSeekBar;
    private MediaPlayer soundPlayer;
    private Thread soundThread;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        swapButton = (Button) findViewById (R.id.changeScreenButton);
        playButton = (Button) findViewById (R.id.playButton);
        stopButton = (Button) findViewById (R.id.stopButton);
        pauseButton = (Button) findViewById (R.id.pauseButton);
        soundSeekBar = (SeekBar) findViewById (R.id.soundSeekBar);

        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lotr);

        setupListeners();

        soundThread = new Thread(this);
        soundThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media, menu);
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

        swapButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View buttonView)
            {
                Intent changeScreen = new Intent(buttonView.getContext(), VideoActivity.class);
                startActivityForResult(changeScreen, 0);
            }

        });

        playButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                soundPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                soundPlayer.pause();
            }

        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View currentView)
            {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.lotr );
            }

        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
           public void onStopTrackingTouch(SeekBar seekBar)
           {
           }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    soundPlayer.seekTo(progress);
                }
            }

        });

    }

    public void run()
    {
        int currentPosition = 0;
        int soundTotal = soundPlayer.getDuration();
        soundSeekBar.setMax(soundTotal);

        while (soundPlayer != null && currentPosition < soundTotal)
        {
            try
            {
                Thread.sleep(300);
                currentPosition = soundPlayer.getCurrentPosition();
            }
            catch(InterruptedException soundException)
            {
                return;
            }
            soundSeekBar.setProgress(currentPosition);
        }
    }
}
