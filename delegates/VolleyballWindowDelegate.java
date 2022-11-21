package ca.ubc.cs304.delegates;

/**
 * This interface uses the delegation design pattern where instead of having
 * the VolleyballWindow class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the
 * controller class (in this case Bank).
 *
 * VolleyballWindow calls the methods that we have listed below but
 * Volleyball is the actual class that will implement the methods.
 */

public interface VolleyballWindowDelegate {
    public void databaseSetup();
    public void volleyballWindowFinished();

//TODO methods for queries!!!

}
