package com.github.runly.example;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.runly.circularprogressview.ProgressView;

public class MainActivity extends AppCompatActivity {
  private ProgressView progress1;
  private ProgressView progress2;
  private ProgressView progress3;
  private ProgressView progress4;
  private ProgressView progress5;
  private Handler handler;
  private Runnable runnable;
  private boolean isStart = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    progress1 = (ProgressView) findViewById(R.id.progress1);
    progress1.setStrokeSizeDp(this, 5);
    progress1.setStrokeColors(new int[] {Color.BLUE});

    progress1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress1.pauseUnpause();
      }
    });

    progress2 = (ProgressView) findViewById(R.id.progress2);
    progress2.setStrokeSizePx(15);
    progress2.setStrokeColors(new int[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});

    progress2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress2.pauseUnpause();
      }
    });

    progress3 = (ProgressView) findViewById(R.id.progress3);
    progress3.setStrokeSizeDp(this, 5);
    progress3.setStrokeColors(new int[] {Color.BLUE});

    progress3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress3.pauseUnpause();
      }
    });

    progress4 = (ProgressView) findViewById(R.id.progress4);
    progress4.setStrokeSizePx(20);
    progress4.setStrokeColors(new int[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});
    progress4.start();
    progress4.setProgress(25);

    progress4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress4.pauseUnpause();
      }
    });

    progress5 = (ProgressView) findViewById(R.id.progress5);
    progress5.setStrokeSizePx(20);
    progress5.setStrokeColors(new int[] {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});
    progress5.start();
    progress5.setProgress(25);

    progress5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progress5.pauseUnpause();
      }
    });

    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        if (isStart) {
          progress1.stop();
          progress2.stop();
          progress3.stop();
          progress4.stop();
          progress5.stop();
          isStart = false;
          handler.postDelayed(this, 3000);
        } else {
          progress1.start();
          progress2.start();
          progress3.start();
          progress4.start();
          progress5.start();
          isStart = true;
          handler.postDelayed(this, 8000);
        }
      }
    };
    handler.postDelayed(runnable, 8000);

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    handler.removeCallbacks(runnable);
  }
}
