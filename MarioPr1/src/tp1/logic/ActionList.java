package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
	private List<Action> actionList;
	
	public ActionList() {
		actionList = new ArrayList<>();
	}
	
	public void add(Action act) {
		this.actionList.add(act);
	}
}
