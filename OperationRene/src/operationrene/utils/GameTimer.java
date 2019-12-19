package operationrene.utils;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    
    private static GameTimer instance = null;
    private int time;
    private Timer timer;
    
    private GameTimer(){}
    
    public static synchronized void deleteInstance(){
        
        instance.stopTimer();
        instance = null;
        
    }
    
    public static synchronized GameTimer getIstance(){
        
        if(instance == null){
            
            instance = new GameTimer();
            
        }
        
        return instance;
        
    }
    
    public synchronized void startTimer(int startTime){
        
        this.time = startTime;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(time<=0){
                    stopTimer();
                }else{
                    time--;
                }
            }

        }, 0, 1000);
        
    }
    
    public synchronized void stopTimer(){
        
        this.timer.cancel();
        
    }
    
    public synchronized void increaseTime(int increase){
        
        this.time += increase;
        
    }

    public synchronized int getTime() {
        return time;
    }
    
    public synchronized void setTime(int time) {
        this.time = time;
    }
    
}
