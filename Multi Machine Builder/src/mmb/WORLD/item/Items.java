/**
 * 
 */
package mmb.WORLD.item;

import mmb.debug.Debugger;
import monniasza.collects.selfset.HashSelfSet;
import monniasza.collects.selfset.SelfSet;

/**
 * @author oskar
 *
 */
public class Items {
	private static Debugger debug = new Debugger("ITEMS");
	public static final SelfSet<String, ItemType> items = new HashSelfSet<>();

	/**
	 * @param typ
	 */
	@SuppressWarnings("null")
	public static void remove(ItemType typ) {
		remove(typ.id());
	}
	/**
	 * @param o
	 */
	public static void remove(String o) {
		debug.printl("Removing "+o);
		items.removeKey(o);
	}
	
	public static void register(ItemType t) {
		items.add(t);
	}

}
