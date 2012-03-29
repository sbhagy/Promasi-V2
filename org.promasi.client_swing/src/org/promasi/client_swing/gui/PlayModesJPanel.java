/**
 * 
 */
package org.promasi.client_swing.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.DefaultListModel;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

import org.promasi.client_swing.playmode.IPlayMode;
import org.promasi.client_swing.playmode.multiplayer.MultiPlayerPlayMode;
import org.promasi.client_swing.playmode.singleplayer.SinglePlayerPlayMode;
import org.promasi.utils_swing.GuiException;
import org.promasi.utils_swing.components.RoundedJPanel;
import org.promasi.utils_swing.components.jeditorpane.ExtendedJEditorPane;
import org.promasi.utils_swing.components.jlist.MenuCellRenderer;

/**
 * @author alekstheod
 *
 */
public class PlayModesJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public static final int CONST_PLAYMODES_LIST_WIDTH = 200;
	
	/**
	 * 
	 */
	private JList<IPlayMode> _playModesList;
	
	/**
	 * 
	 */
	private IMainFrame _mainFrame;
	
	/**
	 * 
	 */
	private ExtendedJEditorPane _infoPane;
	
	/**
	 * 
	 */
	public PlayModesJPanel( IMainFrame listener )throws GuiException{
		super();
		try {
			if( listener == null ){
				throw new GuiException("Wrong argument listener == null");
			}
			
			_mainFrame = listener;
			DefaultListModel<IPlayMode> listModel = new DefaultListModel<IPlayMode>();
			listModel.addElement( new SinglePlayerPlayMode() );
			
			try{
				listModel.addElement( new MultiPlayerPlayMode() );
			}catch( Exception e){
				//TODO log.
			}
			
			JSplitPane splitPane = new JSplitPane();
			
			RoundedJPanel playModesPanel = new RoundedJPanel();
			playModesPanel.setLayout(new BorderLayout());
			_playModesList = new JList<IPlayMode>(listModel);
			_playModesList.setBackground(new Color(80, 80, 80, 0));
			playModesPanel.add(_playModesList, BorderLayout.CENTER);
			setLayout( new BorderLayout() );
			add(splitPane, BorderLayout.CENTER);

			splitPane.setLeftComponent(playModesPanel);
			_playModesList.addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					Point p = new Point(arg0.getX(),arg0.getY());
					_playModesList.setSelectedIndex(_playModesList.locationToIndex(p));
					IPlayMode playMode =  _playModesList.getSelectedValue();
					if( playMode != null ){
						_infoPane.setText(playMode.getDescription());
					}
				}
				
				@Override
				public void mouseDragged(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			_playModesList.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {}
				
				@Override
				public void mousePressed(MouseEvent arg0) {}
				
				@Override
				public void mouseExited(MouseEvent arg0) {	
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					IPlayMode playMode = _playModesList.getSelectedValue();
					if( playMode != null ){
						playMode.gotoNextPanel(_mainFrame);
					}
				}
			});
			
			_playModesList.setCellRenderer(new MenuCellRenderer());
			
			EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);
	
			_playModesList.setBorder(edge);
			
			_infoPane = new ExtendedJEditorPane();
			_infoPane.setEditable(false);
			_infoPane.setContentType( "text/html" );
			splitPane.setRightComponent(new JScrollPane(_infoPane));
			splitPane.setDividerLocation( 200 );
		} catch (Exception e) {
			throw new GuiException(e);
		}
	}
}
