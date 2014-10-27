package io.endeios.massiccioPlayer.business

import io.endeios.massiccioPlayer.model.messages.*

import java.util.Observer;
import java.util.Observable;

class FlightObserver implements Observer{
	StartTimer start;
	List<AddPoint> points;
	List<AddPenalty> penalties;

	public void update(Observable obs, Object obj){
		println "${obj.class.name}"
		/*switch(obj.class.name){
			case "AddPoint
				break;
			}
		}*/
	}
}
