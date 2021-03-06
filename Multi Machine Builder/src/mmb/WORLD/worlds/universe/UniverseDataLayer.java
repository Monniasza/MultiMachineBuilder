/**
 * 
 */
package mmb.WORLD.worlds.universe;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.BEANS.Loader;
import mmb.BEANS.Saver;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 *
 */
public interface UniverseDataLayer extends Loader<JsonNode>, Saver<JsonNode>, Identifiable<String> {
	/**
	 * This method is run after the data layer is loaded
	 */
	public void afterWorldLoaded(Universe d);
}
