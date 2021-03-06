/**
 * 
 */
package mmb.WORLD.inventory.io;

import mmb.BEANS.Positioned;
import mmb.WORLD.items.ItemEntry;
import mmb.WORLD.worlds.world.BlockMap;

/**
 * @author oskar
 *
 */
public class Dropper implements InventoryWriter, Positioned {
	private final BlockMap map;
	private int x, y;
	public Dropper(BlockMap map) {
		this.map = map;
	}

	@Override
	public int write(ItemEntry ent, int amount) {
		for(int i = 0; i < amount; i++) {
			map.dropItem(ent, x, y);
		}
		return amount; //always accepts
	}

	@Override
	public int toBeWritten(ItemEntry currentItem, int amt) {
		return amt; //always accepts
	}

	@Override
	public int posX() {
		return x;
	}

	@Override
	public int posY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
