package noritakakagei.study.testing;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// object(Task) state
enum State {
    PENDING, RUNNING, COMPLETED
}

// test target class
class Task {
    private State state = State.PENDING;

    public State getState() {
        return state;
    }

    public void start() {
        state = State.RUNNING;
    }

    public void finish() {
        state = State.COMPLETED;
    }
}

// test class
class ObjStateTimelineTest {
    @Test
    void executeTaskPassThroughScenario() {
        /* JUnit Style */
        // after task object is created
        Task task = new Task();
        Assertions.assertEquals(State.PENDING, task.getState());

        // after task is running
        task.start();
        Assertions.assertEquals(State.RUNNING, task.getState());

        // after task finished
        task.finish();
        Assertions.assertEquals(State.COMPLETED, task.getState());

        /* AssertJ Style */
        // after task object is created
        task = new Task();
        assertThat(task.getState()).isEqualTo(State.PENDING);

        // after task is running
        task.start();
        assertThat(task.getState()).isEqualTo(State.RUNNING);

        // after task finished
        task.finish();
        assertThat(task.getState()).isEqualTo(State.COMPLETED);
    }
}