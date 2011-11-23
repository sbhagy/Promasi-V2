/**
 * 
 */
package org.promasi.game.company;

import java.util.Map;

import org.promasi.game.GameException;
import org.promasi.game.project.ProjectTask;
import org.promasi.utilities.exceptions.NullArgumentException;
import org.promasi.utilities.serialization.SerializationException;

/**
 * @author m1cRo
 *
 */
public class EmployeeTask
{
	/**
	 * 
	 */
	protected ProjectTask _projectTask;
	
	/**
	 * 
	 */
	protected int _firstStep;
	
	/**
	 * 
	 */
	protected int _lastStep;
	
	/**
	 * 
	 * @param projectTask
	 * @param startDate
	 * @param endDate
	 * @throws NullArgumentException
	 * @throws IllegalArgumentException
	 */
	public EmployeeTask(ProjectTask projectTask,final int firstStep, final int lastStep)throws GameException
	{
		if(projectTask==null)
		{
			throw new GameException("Wrong argument projectTask==null");
		}
		
		if(firstStep<0)
		{
			throw new GameException("Wrong argument startStep < 0");
		}
		
		if(lastStep<=firstStep)
		{
			throw new GameException("Wrong argument endDate<=startStep");
		}
		
		_firstStep=firstStep;
		_lastStep=lastStep;
		_projectTask=projectTask;
	}
	
	/**
	 * 
	 * @return
	 * @throws SerializationException
	 */
	public SerializableEmployeeTask getSerializableEmployeeTask()throws SerializationException{
		return new SerializableEmployeeTask(this);
	}
	
	/**
	 * 
	 * @param systemClock
	 * @param employeeProperties
	 * @return
	 * @throws NullArgumentException
	 */
	public boolean executeTask(Map<String, Double> employeeSkills, double currentStep)throws NullArgumentException
	{
		if(currentStep<_firstStep || currentStep>_lastStep){
			return false;
		}
		
		return _projectTask.applyEmployeeSkills(employeeSkills);
	}
	
	/**
	 * 
	 * @param task
	 * @return
	 */
	public boolean conflictsWithTask(final EmployeeTask task){
		if(_firstStep>task._firstStep && _firstStep<task._lastStep){
			return true;
		}
		
		if(_lastStep>task._firstStep && _lastStep<task._lastStep){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param currentStep
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean isValid(int currentStep)throws IllegalArgumentException{
		if(currentStep<0){
			throw new IllegalArgumentException("Wrong argument currentStep<0");
		}
		
		if(currentStep>_lastStep){
			return false;
		}
		
		return _projectTask.isValidTask();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFirstStep(){
		return _firstStep;
	}

}
