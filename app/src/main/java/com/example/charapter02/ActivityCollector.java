package com.example.charapter02;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//在活动管理器中，我们通过-一个List 来暂存活动,然后提供了一个addActivity()方法用
//于向List中添加一一个活动,提供了-一个removeActivity()方法用于从List中移除活动,最后提
//供了一个finishAll()方法用于将List中存储的活动全部销毁掉。
public class ActivityCollector {
    public static List<Activity> list = new ArrayList<>() ;

    public static void addActivity(Activity activity){
        list.add(activity);
    }

    public static void removeActivity(Activity activity){
        list.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:list) {
            //isFinishing() 可用来判断Activity是否处于活跃状态（false）还是等待回收状态（true）。
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
