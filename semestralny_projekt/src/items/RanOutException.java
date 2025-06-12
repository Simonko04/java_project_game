package items;

/**
 * custom exception ak došlo consumable
 */
public class RanOutException extends Exception {
    public RanOutException(String message) {
      super(message);
    }
}
