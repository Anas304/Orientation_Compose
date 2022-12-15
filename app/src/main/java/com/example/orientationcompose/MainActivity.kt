 package com.example.orientationcompose

import android.content.res.Configuration
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.orientationcompose.ui.theme.OrientationComposeTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            OrientationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val configuration = LocalConfiguration.current

                    when(configuration.orientation){
                        Configuration.ORIENTATION_LANDSCAPE ->{
                            VideoViewLandscap(videoUri = "https://spont.nl/wp-content/uploads/2022/12/intro-landscape.mp4")
                        }
                        else ->{
                            VideoViewPortrait(videoUri = "https://spont.nl/wp-content/uploads/2022/12/intro-potraid.mp4")
                        }
                    }

                }
            }
        }
    }
}

 @Composable
 fun VideoViewPortrait(videoUri: String) {
     val context = LocalContext.current

     val exoPlayer = ExoPlayer.Builder(LocalContext.current)
         .build()
         .also { exoPlayer ->
             val mediaItem = MediaItem.Builder()
                 .setUri(videoUri)
                 .build()
             exoPlayer.setMediaItem(mediaItem)
             exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
             exoPlayer.playWhenReady =true
             exoPlayer.prepare()
         }

     DisposableEffect(
         AndroidView(factory = {
             StyledPlayerView(context).apply {
                 player = exoPlayer
                 layoutParams = FrameLayout.LayoutParams(
                     ViewGroup.LayoutParams.MATCH_PARENT,
                     ViewGroup.LayoutParams.MATCH_PARENT
                 )

                 useController = false
                 resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
             }
         }
         )
     ) {
         onDispose { exoPlayer.release() }
     }
 }

 @Composable
 fun VideoViewLandscap(videoUri: String) {
     val context = LocalContext.current

     val exoPlayer = ExoPlayer.Builder(LocalContext.current)
         .build()
         .also { exoPlayer ->
             val mediaItem = MediaItem.Builder()
                 .setUri(videoUri)
                 .build()
             exoPlayer.setMediaItem(mediaItem)
             exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
             exoPlayer.playWhenReady =true
             exoPlayer.prepare()
         }

     DisposableEffect(
         AndroidView(factory = {
             StyledPlayerView(context).apply {
                 player = exoPlayer
                 layoutParams = FrameLayout.LayoutParams(
                     ViewGroup.LayoutParams.MATCH_PARENT,
                     ViewGroup.LayoutParams.MATCH_PARENT
                 )

                 useController = false
                 resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
             }
         }
         )
     ) {
         onDispose { exoPlayer.release() }
     }
 }