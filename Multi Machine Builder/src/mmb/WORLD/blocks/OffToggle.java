/**
 * 
 */
package mmb.WORLD.blocks;

import mmb.BEANS.BlockActivateListener;
import mmb.WORLD.Side;
import mmb.WORLD.block.Block;
import mmb.WORLD.gui.window.WorldWindow;
import mmb.WORLD.worlds.world.World;

/**
 * @author oskar
 *
 */
public class OffToggle extends Block implements BlockActivateListener {
	@Override
	public boolean provideSignal(Side s) {
		return false;
	}

	@Override
	public void click(int blockX, int blockY, World map, WorldWindow window) {
		map.place(ContentsBlocks.ON, blockX, blockY);
	}

}
