package com.mapdaobackground.mapdaobg;
import android.app.Application;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by yu on 2014/11/17.
 */
public class MapdaoBGApplication  extends Application
    {
        @Override
        public void onCreate()
        {
            super.onCreate();
            AVOSCloud.useAVCloudCN();
            AVOSCloud.initialize(this,"v9bsux02cqhhfemirp99vdxrrlsu8p51z1em4n5ajkdt8ssu","n83onfpmwjpzw2u64kx005q4567968xtpoeu98wqw3pzuln3");
            //AVOSCloud.initialize();
        }
    }
