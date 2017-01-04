package fr.inria.sedrennes.myplugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import fr.inria.sedrennes.myplugin.api.MyExtensionServiceInterface;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.sedrennes.myplugin"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		
		IConfigurationElement[] confElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("fr.inria.sedrennes.mypluginmy.extension");
		for (int i = 0; i < confElements.length; i++) {
			// do something with each contributors
			String myattributeString = confElements[i].getAttribute("my_attribute_string");
			final MyExtensionServiceInterface myattributeClass = 
					(MyExtensionServiceInterface) confElements[i].createExecutableExtension("my_attribute_class");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void warn(String msg, Throwable e) {
		Activator.getDefault().getLog().log(new Status(Status.WARNING, PLUGIN_ID, Status.OK, msg, e));
	}

	public static void error(String msg, Throwable e) {
		Activator.getDefault().getLog().log(new Status(Status.ERROR, PLUGIN_ID, Status.OK, msg, e));
	}
}
