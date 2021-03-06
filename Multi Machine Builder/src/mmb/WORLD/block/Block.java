/**
 * 
 */
package mmb.WORLD.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import mmb.WORLD.gui.window.WorldWindow;
import mmb.WORLD.texture.BlockDrawer;
import mmb.WORLD.worlds.world.BlockMap;
import mmb.WORLD.worlds.world.World;

/**
 * @author oskar
 *
 */
public class Block extends BlockBase implements BlockEntry{
	@Override
	public String toString() {
		return "Block " + title + "(" + id + ")";
	}
	@Override
	public @Nonnull JsonNode save() {
		return new TextNode(id);
	}
	@Override
	public BlockType type() {
		return this;
	}
	@Override
	public void place(int x, int y, World map) {
		map.place(this, x, y);
	}

	@Override
	public BlockEntry create(int x, int y, BlockMap map) {
		return this;
	}

	//Placement GUI
	@Override
	public void openGUI(WorldWindow window) {
		//no GUI available
	}
	@Override
	public void closeGUI(WorldWindow window) {
		//no GUI available
	}
	
	//Placer preview
	@Override
	public void preview(Graphics g, Point renderStartPos, BlockMap map, Point targetLocation) {
		drawer.draw(this, renderStartPos, g);
	}

	//Not a BlockEntity
	@Override
	public boolean isBlockEntity() {
		return false;
	}
	@Override
	public BlockEntity asBlockEntity() {
		throw new IllegalStateException("Not a BlockEntity");
	}
	@Override
	public BlockEntityType asBlockEntityType() {
		throw new IllegalStateException("Not a BlockEntity");
	}
	@Override
	public BlockEntityType nasBlockEntityType() {
		return null;
	}
	@Override
	public BlockEntity nasBlockEntity() {
		return null;
	}

	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture path to texture, starting from `/textures`
	 * @return this
	 */
	@Override
	public Block texture(String texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	public Block texture(BufferedImage texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture color
	 * @return this
	 */
	@Override
	public Block texture(Color texture) {
		setTexture(BlockDrawer.ofColor(texture));
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	public Block texture(BlockDrawer texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets title.This is a convenience chainable method
	 * @param title title
	 * @return this
	 */
	@Override
	public Block title(String title) {
		setTitle(title);
		return this;
	}
	/**
	 * Sets description.This is a convenience chainable method
	 * @param description description
	 * @return this
	 */
	@Override
	public Block describe(String description) {
		setDescription(description);
		return this;
	}
	/**
	 * Sets dropped item.This is a convenience chainable method
	 * @param drop drop
	 * @return this
	 */
	@Override
	public Block drop(Drop drop) {
		setDrop(drop);
		return this;
	}
	/**
	 * Registers this block. This is a convenience chainable method
	 * @param id block id
	 * @return this
	 */
	@Override
	public Block finish(String id) {
		register(id);
		return this;
	}
	/**
	 * Sets title.This is a convenience chainable method
	 * @param block title
	 * @return this
	 */
	@Override
	public Block leaveBehind(BlockType block) {
		setLeaveBehind(block);
		return this;
	}
	/**
	 * Sets volume. This is a convenience chainable method
	 * @param volume volume
	 * @return this
	 */
	@Override
	@Nonnull public Block volumed(double volume) {
		this.volume = volume;
		return this;
	}
}
