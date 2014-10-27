package io.endeios.massiccioPlayer.business

import io.endeios.massiccioPlayer.model.messages.*

import java.util.Observable;
public class FlightMessage extends Observable
{
	public startTime(){
		setChanged()
		notifyObserver(new StartTimer())
    	}

	public addPenalty(){
		setChanged()
		notifyObserver(new AddPenalty())
	}
	
	public addPoint(){
		setChanged()
		notifyObserver(new AddPoint())
	}
}
