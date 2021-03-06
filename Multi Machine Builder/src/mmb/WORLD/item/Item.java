/**
 * 
 */
package mmb.WORLD.item;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.WORLD.block.Drop;
import mmb.WORLD.inventory.io.InventoryWriter;
import mmb.WORLD.items.ItemBase;
import mmb.WORLD.items.ItemEntry;
import mmb.WORLD.texture.BlockDrawer;
import mmb.WORLD.worlds.world.BlockMap;
import mmb.debug.Debugger;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 *
 */
public class Item extends ItemBase implements ItemEntry {
	private static final Debugger debug = new Debugger("ITEMS");
	/**
	 * Get the hash code, which is always the same as ID's hash code.
	 * @return item's hash code.
	 * @see java.lang.String#hashCode()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean equals(@Nullable Object other) {
		if(this == other) return true;
		if(other == null) return false;
		if(!(other instanceof Identifiable)) return false;
		Object id1 = ((Identifiable<?>)other).id();
		if(id == id1) return true;
		if(id1 == null) return false;
		return id1.equals(id);
	}

	@Override
	public ItemEntry create() {
		return this;
	}
	@Override
	public ItemEntry itemClone() {
		return this;
	}
	@Override
	public ItemType type() {
		return this;
	}
	@Override
	public boolean exists() {
		return true;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Icon getIcon() {
		return drawer.toIcon();
	}
	@Override
	public ItemEntry load(JsonNode node) {
		debug.printl("Attempting to load a non-data item");
		return this;
	}
	@Override
	public boolean drop(InventoryWriter inv, BlockMap map, int x, int y) {
		return Drop.tryDrop(this, inv, map, x, y);
	}

	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture path to texture, starting from `/textures`
	 * @return this
	 */
	@Override
	public Item texture(String texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	public Item texture(BufferedImage texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture color
	 * @return this
	 */
	@Override
	public Item texture(Color texture) {
		setTexture(BlockDrawer.ofColor(texture));
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	public Item texture(BlockDrawer texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets title.This is a convenience chainable method
	 * @param title title
	 * @return this
	 */
	@Override
	public Item title(String title) {
		setTitle(title);
		return this;
	}
	/**
	 * Sets description.This is a convenience chainable method
	 * @param description description
	 * @return this
	 */
	@Override
	public Item describe(String description) {
		setDescription(description);
		return this;
	}
	/**
	 * Registers this item. This is a convenience chainable method
	 * @param id block id
	 * @return this
	 */
	@Override
	public Item finish(String id) {
		register(id);
		return this;
	}
	/**
	 * Sets volume. This is a convenience chainable method
	 * @param volume volume
	 * @return this
	 */
	@Override
	@Nonnull public Item volumed(double volume) {
		this.volume = volume;
		return this;
	}
}
