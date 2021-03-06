/**
 * 
 */
package mmb.WORLD.blocks.machine;

import java.awt.Component;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mmb.BEANS.BlockActivateListener;
import mmb.BEANS.Titled;
import mmb.WORLD.Side;
import mmb.WORLD.block.SkeletalBlockEntityData;
import mmb.WORLD.blocks.machine.SideConfig.SideBoolean;
import mmb.WORLD.electric.Battery;
import mmb.WORLD.electric.Electricity;
import mmb.WORLD.gui.window.WorldWindow;
import mmb.WORLD.inventory.io.InventoryReader;
import mmb.WORLD.inventory.io.InventoryWriter;
import mmb.WORLD.inventory.storage.SimpleInventory;
import mmb.WORLD.worlds.MapProxy;
import mmb.WORLD.worlds.world.BlockMap;
import mmb.WORLD.worlds.world.World;

/**
 * @author oskar
 * A block which implements electricity and item I/O capabilities. It has helper indicators.
 */
public abstract class SkeletalBlockMachine extends SkeletalBlockEntityData implements BlockActivateListener, Titled{
	
	//Electrical components
	protected final Battery inElec = new Battery();
	public final SideConfig cfgInElec = new SideConfig();
	protected final Battery outElec  = new Battery();
	public final SideConfig cfgOutElec = new SideConfig();
	
	//Item components
	protected final SimpleInventory inItems = new SimpleInventory();
	public final SideConfig cfgInItems = new SideConfig();
	protected final SimpleInventory outItems = new SimpleInventory();
	public final SideConfig cfgOutItems = new SideConfig();
	
	//Setting flags
	/** This constant contains active components. All flags begin with 'SETTING_FLAG_' */
	public final int flags;
	public static final int SETTING_FLAG_ELEC_INPUT = 1;
	public static final int SETTING_FLAG_ELEC_OUTPUT = 2;
	public static final int SETTING_FLAG_ITEM_INPUT = 4;
	public static final int SETTING_FLAG_ITEM_OUTPUT = 8;
	
	private final Consumer<SideBoolean> SHOVE_ELECTRICITY = s -> {
		if(s.value){
			Electricity elec = owner.getAtSide(s.side, x, y).getElectricalConnection(s.side.negate());
			outElec.extractTo(elec);
		}
	};
	@Override
	public final void onTick(MapProxy map) {
		cfgOutElec.forEach(SHOVE_ELECTRICITY);
		onTick0(map);
	}
	/**
	 * This method should be overridden. Runs on every tick
	 * @param map  
	 */
	protected void onTick0(MapProxy map) {
		//to be overridden
	}

	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner2
	 * @param flags
	 */
	protected SkeletalBlockMachine(int x, int y, @Nonnull BlockMap owner2, int flags) {
		super(x, y, owner2);
		this.flags = flags;
	}

	@Override
	public InventoryReader getOutput(Side s) {
		return cfgOutItems.get(s) ? outItems.createReader() : InventoryReader.NONE;
	}

	@Override
	public InventoryWriter getInput(Side s) {
		return cfgInItems.get(s) ? inItems.createWriter() : InventoryWriter.NONE;
	}

	@Override
	public Electricity getElectricalConnection(Side s) {
		if(cfgInElec.get(s)) return inElec;
		if(cfgOutElec.get(s)) return Electricity.NONE;
		return null;
	}

	@Override
	public final void load(@Nullable JsonNode data) {
		if(data == null) return;
		load1((ObjectNode)data);
		if((flags & SETTING_FLAG_ELEC_INPUT) != 0) {
			inElec.load(data.get("inElec"));
			cfgInElec.load(data.get("inElecCfg"));
		}
		if((flags & SETTING_FLAG_ELEC_OUTPUT) != 0) {
			outElec.load(data.get("outElec"));
			cfgOutElec.load(data.get("outElecCfg"));
		}
		if((flags & SETTING_FLAG_ITEM_INPUT) != 0) {
			inItems.load(data.get("inItem"));
			cfgInItems.load(data.get("inItemCfg"));
		}
		if((flags & SETTING_FLAG_ITEM_OUTPUT) != 0) {
			outItems.load(data.get("outItem"));
			cfgOutItems.load(data.get("outItemCfg"));
		}
	}

	@Override
	protected final void save0(ObjectNode node) {
		save1(node);
		if((flags & SETTING_FLAG_ELEC_INPUT) != 0) {
			node.set("inElec", inElec.save());
			node.set("inElecCfg", cfgInElec.save());
		}
		if((flags & SETTING_FLAG_ELEC_OUTPUT) != 0) {
			node.set("outElec", outElec.save());
			node.set("outElecCfg", cfgOutElec.save());
		}
		if((flags & SETTING_FLAG_ITEM_INPUT) != 0) {
			node.set("inItem", inItems.save());
			node.set("inItemCfg", cfgInItems.save());
		}
		if((flags & SETTING_FLAG_ITEM_OUTPUT) != 0) {
			node.set("outItem", outItems.save());
			node.set("outItemCfg", cfgOutItems.save());
		}
	}
	
	protected void save1(ObjectNode node) {}
	protected void load1(ObjectNode node) {}
	/**
	 * Represents title of this machine
	 * @return the title of this machine
	 */
	@Override
	public String title() {
		return type().title();
	}
	Component gui;
	@Override
	public void click(int blockX, int blockY, World map, WorldWindow window) {
		if(window == null) return;
		if(gui != null) return;
		gui = new MachineGUI(this, window);
		window.openAndShowWindow(gui, title());
	}
	
	/**
	 * Override to create custom GUI. If set to null, GUI is not created.
	 * @return the custom GUI
	 */
	@SuppressWarnings("static-method") // the machine should override it
	protected Component createGUI() {
		return null;
	}
}
