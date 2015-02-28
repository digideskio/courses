import java.util.Stack;

// The two-stack solution works to ensure that the mail is processed
// in the same order that it arrives in the inbox because the inbox,
// as a stack, pushes the last (most recent) mail first. That mail goes
// into the outbox, one by one, until the inbox is empty, which means
// that the most recent mail is at the bottom of the outbox. The oldest
// mail that was originally on the bottom of the inbox is now on the top
// of the outbox because it was the first in, last out, for the inbox.
// If we refill the outbox with all the contents of the inbox each time
// the outbox is empty, then all the mail is placed into the mailbag in
// the correct order, and the oldest mail will be addressed sooner, not
// stay at the bottom of the stack while new mail continues to accumulate.

// The running times for the enqueue and dequeue operations are O(n),
// which means that the running time for the whole process is O(2n).

public class TwoStackQueue<T> implements Queue<T> {
 
    private Stack<T> inbox = new Stack<T>();
    private Stack<T> outbox = new Stack<T>();
 
    public void enqueue(T x) {
        inbox.push(x);
    }
 
    public T dequeue() {
        if (outbox.empty() && !inbox.empty()) {
            while (!inbox.empty()) {
                outbox.push(inbox.pop());
            }
        }
        else if (outbox.empty() && inbox.empty()) {
            return null;
        }
        return outbox.pop();
    }
 
}
