package rlVizLib;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * rlVizCore is a new class as part of the professionalism refactoring, leading to the first official release of RL-Viz.
 *<p>
 *This class will provide an interface to the outside world to check things like what version of rlVizLib a particular class 
 *was compiled against (according to the MANIFEST of the jar it was packaged with).  This way, in the future, environments 
 *and agents will be able to tell if they are compatible with the version of RLVizLib that somebody is using.
 *<p>
 *Basically, we're trying to write code so thing don't crash in ugly ways.
 *
 * @author btanner
 * @since 1.1
 *
 */
public final class rlVizCore {

/**
 * Get the version of rlVizLib as set in the Manifest file.
 * @return String representation of current rlVizLib version.
 * @since 1.1
 */
	public static String getVersion(){
		return rlVizCore.class.getPackage().getImplementationVersion();
	}

/**
 * getRLVizLinkVersionOfClass will try and lookup what Jar a particular class was loaded from, and then 
 * check for it's RLVizLib-Link-Version tag in the manifest. Basically, at runtime you are using version X, and you 
 * want to find out what version Y this code was compiled against.  If the versions are different, this class might expect things
 * that aren't really there (yet or anymore).
 * <p>
 * This will hopefully make it easy to check
 * whether the versions of different components work together
 * 
 * @param theClass Class file that you want to find out the RLVizLib compile version of.
 * @return The value of the RLVizLib-Link-Version attribute.
 */
public static String getRLVizLinkVersionOfClass(Class<?> theClass){
	String rlVizLinkVersion="unknown (before 1.1?)";
	URL codeBase = theClass.getProtectionDomain().getCodeSource().getLocation();
	if(codeBase.getPath().endsWith(".jar")) {	
		String jarFileName=codeBase.getFile();

	try {
		JarFile theJarFile=new JarFile(new File(jarFileName));
		Manifest theManifest  = theJarFile.getManifest();
	      
	      Attributes as = theManifest.getMainAttributes();
	      String manifestRLVizLinkVersion = as.getValue("RLVizLib-Link-Version");
	      if(manifestRLVizLinkVersion!=null)rlVizLinkVersion=manifestRLVizLinkVersion;
	} catch (IOException e) {
		System.err.println("Exception when trying to look up values from the manifest of Jar which created class: "+theClass);
		e.printStackTrace();
	}
}//If statement
	return rlVizLinkVersion;
}//getRLVizLinkVersionOfClass

}//class


