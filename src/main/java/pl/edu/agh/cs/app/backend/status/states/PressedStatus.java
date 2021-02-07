package pl.edu.agh.cs.app.backend.status.states;

public enum PressedStatus {
    NOTPRESSED,
    SUCCESS,
    FAILURE;

    public boolean isSuccess() {
        return this.equals(SUCCESS);
    }

    public boolean isFailure() {
        return this.equals(FAILURE);
    }

    public boolean notPressed() {
        return this.equals(NOTPRESSED);
    }
}
