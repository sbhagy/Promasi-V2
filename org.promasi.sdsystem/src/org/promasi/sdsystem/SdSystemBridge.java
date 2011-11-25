/**
 * 
 */
package org.promasi.sdsystem;

import org.promasi.sdsystem.sdobject.InputSdObject;
import org.promasi.sdsystem.sdobject.OutputSdObject;
import org.promasi.utilities.exceptions.NullArgumentException;
import org.promasi.utilities.serialization.SerializationException;


/**
 * @author m1cRo
 *
 */
public class SdSystemBridge 
{
	/**
	 * 
	 */
	protected OutputSdObject _outputSdObject;
	
	/**
	 * 
	 */
	protected InputSdObject _inputSdObject;
	
	/**
	 * 
	 * @param outputSdObjectId
	 * @param outputSdSystem
	 * @param inputSdObjectId
	 * @param inputSdSystem
	 * @throws NullArgumentException
	 * @throws IllegalArgumentException
	 */
	public SdSystemBridge(String outputSdObjectId, SdSystem outputSdSystem, String inputSdObjectId, SdSystem inputSdSystem)throws SdSystemException{
		if(outputSdObjectId==null){
			throw new SdSystemException("Wrong argument outputSdObjectId==null");
		}
		
		if(inputSdObjectId==null){
			throw new SdSystemException("Wrong argument inputSdObjectId==null");
		}
		
		if(outputSdSystem==null){
			throw new SdSystemException("Wrong argument outputSdSystem==null");
		}
		
		if(inputSdSystem==null){
			throw new SdSystemException("Wrong argument inputSdSystem==null");
		}
		
		if(!inputSdSystem._sdObjects.containsKey(inputSdObjectId) || !(inputSdSystem._sdObjects.get(inputSdObjectId) instanceof InputSdObject) ){
			throw new IllegalArgumentException("Wrong arguments input named '"+inputSdObjectId+"' does not exist in inputSdSystem");
		}
		
		if(!outputSdSystem._sdObjects.containsKey(outputSdObjectId) || !(outputSdSystem._sdObjects.get(outputSdObjectId) instanceof OutputSdObject) ){
			throw new IllegalArgumentException("Wrong arguments input named '" + inputSdObjectId+ "' does not exist in outputSdSystem");
		}
		
		_inputSdObject=(InputSdObject) inputSdSystem._sdObjects.get(inputSdObjectId);
		_outputSdObject=(OutputSdObject) outputSdSystem._sdObjects.get(outputSdObjectId);
	}
	
	/**
	 * 
	 * @param inputSdObject
	 * @param outputSdObject
	 */
	protected SdSystemBridge(InputSdObject inputSdObject, OutputSdObject outputSdObject)throws SdSystemException{
		if(inputSdObject==null){
			throw new SdSystemException("Wrong argument inputSdObject==null");
		}
		
		if(outputSdObject==null){
			throw new SdSystemException("Wrong argument outputSdObject==null");
		}
		
		_inputSdObject=inputSdObject;
		_outputSdObject=outputSdObject;
	}
	
	/**
	 * 
	 * @return
	 * @throws SerializationException
	 */
	public SerializableSdSystemBridge getSerializableSdSystemBridge()throws SerializationException{
		try {
			return new SerializableSdSystemBridge(_inputSdObject.getMemento(), _outputSdObject.getMemento());
		} catch (NullArgumentException e) {
			throw new SerializationException("Serialization failed because "  +  e.getMessage() );
		}
	}
	
	/**
	 * 
	 */
	public boolean executeStep(){
		return _inputSdObject.setValue( _outputSdObject.getValue() );
	}
}
