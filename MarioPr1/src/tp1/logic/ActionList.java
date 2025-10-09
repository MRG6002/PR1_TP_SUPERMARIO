package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
	public List<Action> actionList;
	
	public ActionList() {
		actionList = new ArrayList<>();
	}
	
	public void add(Action act) {
		this.actionList.add(act);
	}

	public int size() {
		return this.actionList.size();
	}
}
