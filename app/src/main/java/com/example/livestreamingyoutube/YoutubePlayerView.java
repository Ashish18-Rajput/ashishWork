package com.example.livestreamingyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.lang.reflect.InvocationTargetException;

public class YoutubePlayerView extends AppCompatActivity {
    private YTubePlayerView yTubePlayerView;
    String url = "";
    private String videoId;
    WebView webView;
    ProgressBar progressBar;
//    https://www.youtube.com/watch?v=BddP6PYo2gs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player_view);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webView = findViewById(R.id.youtube_player_view);
        progressBar = findViewById(R.id.progress);
        this.url = getIntent().getStringExtra("link1");
        initUI(url);
    }

    private void initUI(String url) {
        this.webView = (WebView) findViewById(R.id.youtube_player_view);
        this.videoId = url;

        //  this.videoId ="jko3YbU8Mhk";
        //   this.videoId ="TTJW9T1Tki8";
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPlayer();
    }

    private void initPlayer() {
        yTubePlayerView = new YTubePlayerView(this);
        yTubePlayerView.setInstanseOfActivity(this);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebViewClient(new PlayerWebViewClient());
        webView.loadUrl("https://www.youtube.com/embed/" + "rw7bxTqkYYs");

//        https://www.youtube.com/watch?v=7xNcMwyHQ6c
     //   tgbNymZ7vqY

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (yTubePlayerView != null) {
            if (getResources().getConfiguration().orientation == 2) {
                yTubePlayerView.goFullScreenVideo();
            } else if (getResources().getConfiguration().orientation == 1) {
                yTubePlayerView.hideFullScreen();
            }
        }
    }

    private class PlayerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // This line right here is what you're missing.
            // Use the url provided in the method.  It will match the member URL!
//            view.loadUrl("https://www.youtube.com/embed/" + videoId);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.loadUrl("javascript:(function() { document.getElementsByClassName('ytp-play-button ytp-button')[0].click(); })()");
            yTubePlayerView.hideSomeSectionOfBlog(webView);
            yTubePlayerView.scheduleHideContent(webView);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            webView.getSettings();
            webView.loadData("Please try after some time.", "text/html", "UTF-8");
        }
    }

    @Override
    public void onBackPressed() {
        Log.e("BackPressed", "BackPressed");
        yTubePlayerView.loadUrl("about:blank");
        yTubePlayerView.clearHistory();
        yTubePlayerView.stopLoading();
        yTubePlayerView.clearCache(true);
        //removeView(wv);
        yTubePlayerView.clearView();
        yTubePlayerView.freeMemory();
        yTubePlayerView.destroy();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(yTubePlayerView, (Object[]) null);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (yTubePlayerView != null) {
            yTubePlayerView.loadUrl("about:blank");
            yTubePlayerView.clearHistory();
            yTubePlayerView.stopLoading();
            yTubePlayerView.clearCache(true);
            //removeView(wv);
            yTubePlayerView.clearView();
            yTubePlayerView.freeMemory();
            yTubePlayerView.destroy();
            try {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(yTubePlayerView, (Object[]) null);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

  /*  @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            yTubePlayerView.loadUrl("about:blank");
            yTubePlayerView.clearHistory();
            yTubePlayerView.stopLoading();
            yTubePlayerView.clearCache(true);
            //removeView(wv);
            yTubePlayerView.clearView();
            yTubePlayerView.freeMemory();
            yTubePlayerView.destroy();
            try {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(yTubePlayerView, (Object[]) null);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}