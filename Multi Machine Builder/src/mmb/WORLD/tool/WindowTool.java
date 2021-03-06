/**
 * 
 */
package mmb.WORLD.tool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.Icon;

import mmb.BEANS.Titled;
import mmb.WORLD.gui.window.WorldFrame;
import mmb.WORLD.gui.window.WorldWindow;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 * The {@code Tool} interface describes a world tool, which applies its code to the world frame.
 * The tool supports {@link MouseListener}, {@link MouseMotionListener}, {@link MouseWheelListener} and {@link KeyListener},
 * which are delegated from the world frame to the tool. Each registered tool is created once per created world window.
 * 
 * Events:
 * windowClosed
 * selected
 * deselected
 */
public abstract class WindowTool implements
MouseListener,
MouseMotionListener,
MouseWheelListener,
KeyListener,
Identifiable<String>,
Titled{
	@Override
	public final String id() {
		return id;
	}
	/**
	 * @return the icon of the tool
	 */
	public abstract Icon getIcon();
	/**
	 * Tool ID
	 */
	public final String id;
	protected WindowTool(String s) {
		this.id = s;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//to be implemented by its user
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//to be implemented by its user
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//to be implemented by its user
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//to be implemented by its user
	}
	/**
	 * Invoked when window is closed
	 * @param window the window, which is closed
	 * @param frame the associated world frame
	 */
	public void windowClosed(WorldWindow window, WorldFrame frame) {
		//to be implemented by its user
	}
	/**
	 * Invoked when tool is selected
	 */
	public void selected() {
		//to be implemented by its user
	}
	/**
	 * Invoked when tool is deselected
	 */
	public void deselected() {
		//to be implemented by its user
	}

	protected WorldWindow window;
	protected WorldFrame frame;
	public void setWindow(WorldWindow window) {
		this.window = window;
		if(window != null) frame = window.getWorldFrame();
	}
}
