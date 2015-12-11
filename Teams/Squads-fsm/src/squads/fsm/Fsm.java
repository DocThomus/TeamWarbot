package squads.fsm;

import java.util.ArrayList;

public class Fsm
{
	public ArrayList<State> stack;
	
	public Fsm()
	{
		stack = new ArrayList<State>();
	}
	
	public void push(State s)
	{
		stack.add(s);
	}
	
	public void pop()
	{
		stack.remove(stack.size()-1);
	}
	
	public String execute()
	{
		stack.get(stack.size()-1).reflexe();
		return stack.get(stack.size()-1).execute();
	}
	
	public void reflexe()
	{
		stack.get(stack.size()-1).reflexe();
	}
	
	public State getCurrentState()
	{
		return stack.get(stack.size()-1);
	}
}
