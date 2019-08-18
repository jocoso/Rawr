package rawr.components;

public interface Transitional {
	public boolean allowsTransition(Character player);
	public String transitionSucceed();
	public String transitionFailed();
}
