/**
 * 
 */
package org.promasi.multiplayer.client.clientstate;



import java.net.ProtocolException;

import org.apache.commons.lang.NullArgumentException;
import org.promasi.multiplayer.AbstractClientState;
import org.promasi.multiplayer.ProMaSiClient;
import org.promasi.multiplayer.client.clientstate.GameMasterClientState;
import org.promasi.network.protocol.client.request.RequestBuilder;
import org.promasi.network.protocol.client.request.RetreiveGameListRequest;
import org.promasi.network.protocol.client.response.ChooseGameMasterStateResponse;
import org.promasi.network.protocol.client.response.InternalErrorResponse;
import org.promasi.network.protocol.client.response.JoinGameResponse;
import org.promasi.network.protocol.client.response.RetreiveGameListResponse;
import org.promasi.network.protocol.client.response.WrongProtocolResponse;
import org.promasi.shell.playmodes.multiplayerscoremode.MultiPlayerScorePlayMode;

/**
 * @author m1cRo
 *
 */
public class JoinGameClientState extends AbstractClientState{

	/**
	 * 
	 */
	private MultiPlayerScorePlayMode _playMode;
	
	
	/**
	 * 
	 * @param playMode
	 * @throws NullArgumentException
	 */
	public JoinGameClientState(MultiPlayerScorePlayMode playMode)throws NullArgumentException
	{
		if( playMode==null )
		{
			throw new NullArgumentException("Wrong argument playMode=null");
		}
		
		_playMode=playMode;
	}
	
	
	/**
	 * 
	 * @param client
	 * @return
	 */
	public boolean sendRetreiveGameListRequest(ProMaSiClient client)
	{
		if(client==null)
		{
			return false;
		}
		
		return client.sendMessage(new RetreiveGameListRequest().toProtocolString());
	}
	
	/* (non-Javadoc)
	 * @see org.promasi.server.clientstate.IClientState#onReceive(org.promasi.server.core.ProMaSiClient, java.lang.String)
	 */
	@Override
	public void onReceive(ProMaSiClient client, String recData)throws NullArgumentException {
		if(recData==null)
		{
			throw new NullArgumentException("Wrong argument recData==null");
		}
		
		if(client==null)
		{
			throw new NullArgumentException("Wrong argument client==null");
		}
		
		try
		{
			Object object=RequestBuilder.buildRequest(recData);
			if(object instanceof RetreiveGameListResponse)
			{
				RetreiveGameListResponse response=(RetreiveGameListResponse)object;
				_playMode.updateGameList(response.getGames());
			}
			else if(object instanceof JoinGameResponse)
			{
				JoinGameResponse response=(JoinGameResponse)object;
				changeClientState( client, new WaitingGameClientState( _playMode, response.getGameStory()) );
			}
			else if(object instanceof ChooseGameMasterStateResponse)
			{
				changeClientState(client, new GameMasterClientState(_playMode, client));
			}
			else
			{
				client.sendMessage(new WrongProtocolResponse().toProtocolString());
				client.disconnect();
			}
		}
		catch(ProtocolException e)
		{
			client.sendMessage(new WrongProtocolResponse().toProtocolString());
			client.disconnect();
		}
		catch(NullArgumentException e)
		{
			client.sendMessage(new InternalErrorResponse().toProtocolString());
			client.disconnect();
		}
		catch(IllegalArgumentException e)
		{
			client.sendMessage(new InternalErrorResponse().toProtocolString());
			client.disconnect();
		}
	}
}
