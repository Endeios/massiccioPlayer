package io.endeios.massiccioPlayer.business

import io.endeios.massiccioPlayer.model.messages.*

import java.util.Observer;
import java.util.Observable;
import groovy.beans.Bindable

@groovy.transform.ToString
class FlightObserver implements Observer{
    StartTimer start;
    @Bindable Date startDate;
	List<AddPoint> points;
	List<AddPenalty> penalties;

    @Bindable Integer pointsCount=0
    @Bindable Integer penaltiesCount=0

    @Bindable Long secRemaining=0;

    private Integer maximumSecs = 35

    private Timer timer;

    private void resetTimer(){
        if(timer!=null){
            timer.cancel()
            timer.purge()
        }
        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                def now = new Date()
                //millis passed from start
                def diff = now.getTime()-startDate.getTime()
                def longDiff = maximumSecs * 1000 - diff
                if(longDiff>0)
                    setSecRemaining(longDiff)
                else 
                    setSecRemaining(0)
                
            }
        },0,1)
    }


	public void update(Observable obs, Object obj){
        switch(obj.class.name){
            case("io.endeios.massiccioPlayer.model.messages.StartTimer"):resetCase:{
                resetAll(obj)
                break;
            }
            case("io.endeios.massiccioPlayer.model.messages.AddPenalty"):addPenaltyCase:{
                if(penalties == null){
                    def fakeStart = new StartTimer()
                    fakeStart.timeStamp = obj.timeStamp
                    resetAll(fakeStart)
                }
                if(secRemaining>0)
                    penalties << obj
                this.setPenaltiesCount(penalties.size())
                break;
            }
            case("io.endeios.massiccioPlayer.model.messages.AddPoint"):addPointCase:{
                if(points == null){
                    def fakeStart = new StartTimer()
                    fakeStart.timeStamp = obj.timeStamp
                    resetAll(fakeStart)
                }

                if(secRemaining>0)
                    points << obj
                this.setPointsCount(points.size())
                break;
            }
            default:errorCase:{
                println "Error!"
                break;
            }
        }
        println "Status: ${start.timeStamp}-> P:${points.size()}, D:${penalties.size()}"
        println "$this"
	}

    public void resetAll(StartTimer obj){
        start = obj;
        points = new ArrayList<AddPoint>()
        penalties = new ArrayList<AddPenalty>()
        this.setPenaltiesCount( 0)
        this.setPointsCount (0)
        this.setStartDate(start.timeStamp)
        this.resetTimer()

        println "Reset: $this"

    }
}
