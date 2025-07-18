package luaj;

public class OrphanedThread extends Error {
    public OrphanedThread() {
        super("orphaned thread");
    }
}
