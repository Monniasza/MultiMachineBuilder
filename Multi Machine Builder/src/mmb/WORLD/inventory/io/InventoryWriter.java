/**
 * 
 */
package mmb.WORLD.inventory.io;

import mmb.WORLD.inventory.ItemStack;
import mmb.WORLD.items.ItemEntry;
import mmb.debug.Debugger;

/**
 * @author oskar
 * Pushes items into inventory
 */
public interface InventoryWriter {
	/**
	 * Pushes given item entry to the given inventory
	 * @param ent itemto insert
	 * @param amount number of items
	 * @return number of items inserted into inventory
	 */
	public int write(ItemEntry ent, int amount);
	/**
	 * @param stack item stack to insert
	 * @return number of items inserted into inventory
	 */
	public default int write(ItemStack stack) {
		return write(stack.item, stack.amount);
	}
	/**
	 * @param ent item to insert
	 * @return number of items inserted into inventory, here 0 or 1
	 */
	public default int write(ItemEntry ent) {
		return write(ent, 1);
	}
	
	/**
	 * Represents an interface which does not allow input
	 */
	public static final InventoryWriter NONE = new InventoryWriter() {
		@Override
		public int write(ItemEntry ent, int amount) {
			return 0;
		}

		@Override
		public int toBeWritten(ItemEntry currentItem, int i) {
			return 0;
		}	
	};

	/**
	 * @param ent item to be inserted
	 * @param amount number of items to insert
	 * @return number of items which will be written
	 */
	public int toBeWritten(ItemEntry ent, int amount);
	
	/**
	 * @author oskar
	 * Writes items to inventory,
	 * preferring the first inventory.
	 * If it fails, tries writing second inventory.
	 * If both fail, rejects some or all items
	 */
	public static class Priority implements InventoryWriter{
		private final InventoryWriter first, other;
		private static final Debugger debug = new Debugger("INVIN-PRIORITY");
		/**
		 * Creates a priority writer
		 * @param here preferred inventory
		 * @param other secondary inventory
		 */
		public Priority(InventoryWriter here, InventoryWriter other) {
			this.first = here;
			this.other = other;
		}

		@Override
		public int write(ItemEntry ent, int amount) {
			int writeFirst = first.write(ent, amount);
			if(writeFirst == amount) {
				debug.printl("Main accepted all "+amount);
				return amount; //all accepted
			}
			debug.printl("Main accepted some or none "+writeFirst);
			int next = amount - writeFirst;
			int writeSecond = other.write(ent, next);
			debug.printl("Second accepted "+writeSecond);
			return writeFirst+writeSecond;
		}

		@Override
		public int toBeWritten(ItemEntry ent, int amount) {
			int writeFirst = first.toBeWritten(ent, amount);
			if(writeFirst == amount) return amount; //all accepted
			int next = amount - writeFirst;
			int writeSecond = other.toBeWritten(ent, next);
			return writeFirst+writeSecond;
		}
		
	}
	/**
	 * @author oskar
	 * Writes items to inventories
	 */
	public static class Splitter implements InventoryWriter{
		private final InventoryWriter[] writers;
		/**
		 * Creates a splitter with given writers;
		 * @param writers list of targeted writers
		 */
		public Splitter(InventoryWriter... writers) {
			this.writers = writers;
		}
		private int pos = 0;
		@Override
		public int write(ItemEntry ent, int amount) {
			next();
			int remaining = amount;
			int transferred = 0;
			for(int i = 0; i < writers.length; i++) {
				InventoryWriter writer = writers[pos];
				int now = writer.write(ent, remaining);
				remaining -= now;
				transferred += now;
				if(remaining == 0) return transferred;
				next();
			}
			return transferred;
		}

		@Override
		public int toBeWritten(ItemEntry ent, int amount) {
			int oldpos = pos;
			next();
			int remaining = amount;
			int transferred = 0;
			for(int i = 0; i < writers.length; i++) {
				InventoryWriter writer = writers[pos];
				int now = writer.toBeWritten(ent, remaining);
				remaining -= now;
				transferred += now;
				if(remaining == 0) {
					pos = oldpos;
					return transferred;
				}
				next();
			}
			pos = oldpos;
			return transferred;
		}
		
		private int next() {
			pos++;
			if(pos >= writers.length) pos = 0;
			return pos;
		}
		
	}
}
