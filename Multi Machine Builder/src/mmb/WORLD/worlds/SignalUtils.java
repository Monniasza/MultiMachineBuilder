/**
 * 
 */
package mmb.WORLD.worlds;

import javax.annotation.Nullable;

import mmb.WORLD.Side;
import mmb.WORLD.block.BlockEntry;
import mmb.WORLD.worlds.world.BlockArrayProvider;

/**
 * @author oskar
 *
 */
public class SignalUtils {
	/**
	 * Return 4-bit 0000UDLR vector of incoming boolean signals
	 * @param x X coordinate of block
	 * @param y Y coordinate of block
	 * @param map block map, from which to get signals
	 * @return vectorized incoming signals
	 */
	public static byte incomingSignals(int x, int y, BlockArrayProvider map) {
		byte result = 0;
		if(provideSignal(map.get(x+1, y), Side.L)) result += 1;
		if(provideSignal(map.get(x-1, y), Side.R)) result += 2;
		if(provideSignal(map.get(x, y+1), Side.U)) result += 4;
		if(provideSignal(map.get(x, y-1), Side.D)) result += 8;
		return result;
	}
	/**
	 * Count incoming signals. Used in WireWorld
	 * @param x X coordinate of block
	 * @param y Y coordinate of block
	 * @param map block map, from which to get signals
	 * @return number of 'true' signals
	 */
	public static int allIncomingSignals(int x, int y, BlockArrayProvider map) {
		int result = 0;
		if(provideSignal(map.get(x+1, y), Side.L)) result++;
		if(provideSignal(map.get(x+1, y+1), Side.UL)) result++;
		if(provideSignal(map.get(x, y+1), Side.U)) result++;
		if(provideSignal(map.get(x-1, y+1), Side.UR)) result++;
		if(provideSignal(map.get(x-1, y), Side.R)) result++;
		if(provideSignal(map.get(x-1, y-1), Side.DR)) result++;
		if(provideSignal(map.get(x, y-1), Side.D)) result++;
		if(provideSignal(map.get(x+1, y-1), Side.DL)) result++;
		return result;
	}
	/**
	 * @param x X coordiante
	 * @param y Y coordinate
	 * @param map the block map
	 * @return
	 */
	public static boolean hasIncomingSignal(int x, int y, BlockArrayProvider map) {
		if(provideSignal(map.get(x+1, y), Side.L)) return true;
		if(provideSignal(map.get(x-1, y), Side.R)) return true;
		if(provideSignal(map.get(x, y+1), Side.U)) return true;
		if(provideSignal(map.get(x, y-1), Side.D)) return true;
		return false;
	}
	/**
	 * Check if given block emits signal at given side
	 * @param ent block to check
	 * @param s side
	 * @return does block provide signal?
	 */
	public static boolean provideSignal(@Nullable BlockEntry ent, Side s) {
		if(ent == null) return false;
		return ent.provideSignal(s);
	}
}
