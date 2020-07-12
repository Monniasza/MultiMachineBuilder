/**
 * 
 */
package mmb.world.parts.wing;

import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

import e3d.d3.Rotation3;
import e3d.d3.Transform3;
import e3d.d3.Vector3;
import mmb.world.Circumstances;
import mmb.world.parts.Part;
import mmb.world.parts.modules.PartModule;

/**
 * @author oskar
 *
 */
public class Wing implements PartModule {
	public double lift;
	/**
	 * 
	 */
	public Wing() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see mmb.world.parts.modules.PartModule#build()
	 */
	@Override
	public void build() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mmb.world.parts.modules.PartModule#flight(mmb.world.Circumstances)
	 */
	@Override
	public void flight(Circumstances cs) {
		Transform3 ori = Transform3.rotation(cs.p.getOrientation());
		Vector3 t = ori.top();
		Vector3 f = ori.fwd();
		Vector3 r = ori.right();
		double ahead = t.dot(cs.wind);
		double cross = r.dot(cs.wind);
		double vertical = f.dot(cs.wind);
	}

	/* (non-Javadoc)
	 * @see mmb.world.parts.modules.PartModule#partCreation(mmb.world.parts.Part)
	 */
	@Override
	public void partCreation(Part p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mmb.world.parts.modules.PartModule#partCrash(mmb.world.Circumstances)
	 */
	@Override
	public void partCrash(Circumstances cs) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mmb.world.parts.modules.PartModule#createGeometry(com.jme3.scene.Geometry, com.jme3.scene.Spatial)
	 */
	@Override
	public void createGeometry(Geometry g, Spatial s) {
		// TODO Auto-generated method stub

	}

}
