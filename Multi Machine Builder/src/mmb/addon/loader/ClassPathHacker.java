package mmb.addon.loader;

import java.io.IOException;
import java.io.File;
import java.net.URLClassLoader;
import java.nio.file.Path;

import mmb.debug.Debugger;

import java.net.URL;
import java.lang.reflect.Method;

public class ClassPathHacker {
  private static Debugger debug = new Debugger("CLASSPATH");
  private static final Class[] parameters = new Class[]{URL.class};

  static public void addFile(String s) throws IOException {
    File f = new File(s);
    addFile(f);
  }//end method

  static public void addFile(File f) throws IOException {
    addURL(f.toURL());
  }//end method
  
  static public void addFile(Path p) {
	  try {
		addURL(p.toFile().toURL());
	} catch (IOException e) {
		debug.pstm(e, "Couldn't load" + p.toString());
		e.printStackTrace();
	}
  }


  static public void addURL(URL u) throws IOException { //returns loaded class

    URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    Class sysclass = URLClassLoader.class;
    try {
      Method method = sysclass.getDeclaredMethod("addURL", parameters);
      method.setAccessible(true);
      method.invoke(sysloader, new Object[]{u});
    } catch (Throwable t) {
    	debug.pstm(t, "Failed to read online resource" + u.toString());
    }//end try catch

   }//end method

}//end class