package io.endeios.massiccioPlayer.business

import io.endeios.massiccioPlayer.model.messages.*

import java.util.Observable;
public class FlightMessager extends Observable
{
	public startTime(){
		setChanged()
		notifyObservers(new StartTimer())
    	}

	public addPenalty(){
		setChanged()
		notifyObservers(new AddPenalty())
	}
	
	public addPoint(){
		setChanged()
		notifyObservers(new AddPoint())
	}
}
