package com.github.runly.circularprogressview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class ProgressView extends View {
  public static final int MODE_DETERMINATE = 0;
  public static final int MODE_INDETERMINATE = 1;
  private CircularProgressDrawable circularProgressDrawable;
  private boolean isStart = false;
  private boolean isAutoStart = true;

  public ProgressView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  protected void init(Context context, AttributeSet attrs) {
    applyStyle(context, attrs);
  }

  /**
   * set progress's style
   */
  protected void applyStyle(Context context, AttributeSet attrs) {
    circularProgressDrawable = new CircularProgressDrawable.Builder(context, attrs, 0, R.style.CircularProgress)
        .build();
    ViewUtil.setBackground(this, circularProgressDrawable);
  }

  public CircularProgressDrawable getCircularProgressDrawable() {
    return circularProgressDrawable;
  }

  public void setAutoStart(boolean autoStart) {
    isAutoStart = autoStart;
  }

  /**
   * set the stroke size with px
   */
  public void setStrokeSizePx(int px) {
    getCircularProgressDrawable().setStrokeSize(px);
  }

  /**
   * set the stroke size with dp
   */
  public void setStrokeSizeDp(Context context, float dp) {
    int px = dipToPixels(context, dp);
    getCircularProgressDrawable().setStrokeSize(px);
  }

  /**
   * set the colors with int[]
   */
  public void setStrokeColors(int[] strokeColors) {
    getCircularProgressDrawable().setStrokeColors(strokeColors);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (isAutoStart) {
      start();
    }
  }

  @Override
  protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
    super.onVisibilityChanged(changedView, visibility);
    if (visibility == GONE || visibility == INVISIBLE && isStart) {
      stop();
    } else {
      if (isAutoStart) {
        start();
      }
    }

  }

  @Override
  protected void onDetachedFromWindow() {
    if (isStart && getVisibility() == View.VISIBLE) {
      stop();
    }
    super.onDetachedFromWindow();
  }

  /**
   * Start showing progress.
   */
  public void start() {
    if (getCircularProgressDrawable() != null) {
      getCircularProgressDrawable().start();
      isStart = true;
    }
  }

  /**
   * Stop showing progress.
   */
  public void stop() {
    if (getCircularProgressDrawable() != null && isStart) {
      getCircularProgressDrawable().stop();
      isStart = false;
    }
  }

  /**
   * convert dip tp px
   */
  private int dipToPixels(Context context, float dipValue) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics) + 0.5f);
  }
}
