package com.hoshino.hoshinoscene.tools.animations;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//为 Generation 相关准备的动画类
public class AnimatedGen {
    public static final double ANIMATION_TIME = 500;
    public static final int TRANSPARENT = 0;
    public static final int OPAQUE = 1;

    public static void warningAnimation(String text, ArrayList<Label> targetLabels) {
        //遍历ArrayList，逐一添加动画
        for(Label l : targetLabels) {
            //淡出：
            FadeTransition animationOut = new FadeTransition(Duration.millis(ANIMATION_TIME), l);
            animationOut.setFromValue(OPAQUE);//从不透明
            animationOut.setToValue(TRANSPARENT);//到透明
            animationOut.setAutoReverse(true);
            //淡入：
            FadeTransition animationIn = new FadeTransition(Duration.millis(ANIMATION_TIME), l);
            animationIn.setFromValue(TRANSPARENT);//从透明
            animationIn.setToValue(OPAQUE);//到不透明
            animationIn.setAutoReverse(true);
            //播放：
            //检查按钮触发的时候控件不透明度是否为零，防止出现动画乱抽
            if(l.getOpacity()==0) {
                l.setText(text);//设置文本
                animationIn.play();//播放进入
                //设置延时
                Timer t = new Timer();
                //延时任务
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        animationOut.play();//播放退出
                    }
                };
                t.schedule(tt, 3000);//3s后执行
            }
        }
    }
}
