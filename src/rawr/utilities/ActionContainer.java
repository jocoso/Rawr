package rawr.utilities;

import java.util.HashMap;
import java.util.Map;

import rawr.internal.Action;
import rawr.utilities.tools.Tools;

public class ActionContainer {
	private Map<String, Action> container;
	
	public ActionContainer() {
		container = new HashMap<String, Action>();
	}
	
	public ActionContainer(String actionName, Action action) {
		container = new HashMap<String, Action>();
		addAction(actionName, action);
	}
	
	public ActionContainer(String[] actionNames, Action[] actions) {
		container = new HashMap<String, Action>();
		addActions(actionNames, actions);
	}
	
	public void addAction(String actionName, Action action) {
		container.put(Tools.sanitize(actionName), action);
	}
	
	public void addActions(String[] actionsNames, Action[] actions) throws IndexOutOfBoundsException {
		if(actionsNames.length != actions.length) throw new IndexOutOfBoundsException();
		for(int i = 0; i < actionsNames.length; i++) {
			addAction(actionsNames[i], actions[i]);
		}
	}
	
	public Action getAction(String actionName)  {
		return container.get(actionName);
	}
	
	public boolean containsKey(String actionName) {
		return container.containsKey(actionName);
	}

}
