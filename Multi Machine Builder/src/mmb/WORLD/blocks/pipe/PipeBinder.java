/**
 * 
 */
package mmb.WORLD.blocks.pipe;

import javax.annotation.Nonnull;

import mmb.WORLD.RotatedImageGroup;
import mmb.WORLD.Side;
import mmb.WORLD.block.BlockType;
import mmb.WORLD.gui.Variable;
import mmb.WORLD.inventory.io.InventoryReader;
import mmb.WORLD.inventory.io.InventoryWriter;
import mmb.WORLD.items.ItemEntry;
import mmb.WORLD.worlds.MapProxy;
import mmb.WORLD.worlds.world.BlockMap;

/**
 * @author oskar
 * Represents a binding pipe. The binding side goes to UP
 */
public class PipeBinder extends AbstractBasePipe{
	
	protected final @Nonnull Pusher toCommon, toSide, toMain;
	/**
	 * Creates a binding pipe.
	 * @param x X position
	 * @param y Y position
	 * @param owner2 containing map
	 * @param type block type
	 * @param binding binding side
	 * @param rig texture
	 */
	public PipeBinder(int x, int y, @Nonnull BlockMap owner2, 
			@Nonnull BlockType type, @Nonnull Side binding, @Nonnull RotatedImageGroup rig) {
		super(x, y, owner2, type, 3, rig);
		Variable<ItemEntry> varM = getSlot(0);
		Variable<ItemEntry> varS = getSlot(1);
		Variable<ItemEntry> varC = getSlot(2);
		
		toCommon = new Pusher(varC, Side.U);
		toSide = new Pusher(varS, binding);
		toMain = new Pusher(varM, Side.D);
		
		InventoryReader readC = new ExtractForItemVar(varC);
		InventoryReader readM = new ExtractForItemVar(varM);
		InventoryReader readS = new ExtractForItemVar(varS);
		
		InventoryWriter top = new InventoryWriter.Priority(toMain, toSide);
		
		setIn(toCommon, binding);
		inU = top;
		inD = toCommon;
		
		setOut(readS, binding);
		outD = readM;
		outU = readC;
	}
	@Override
	public void onTick(MapProxy map) {
		toCommon.push();
		toSide.push();
		toMain.push();
	}

}
