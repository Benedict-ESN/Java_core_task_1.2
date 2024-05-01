public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener doneListener = System.out::println;
        OnTaskErrorListener errorListener = System.err::println;

        Worker worker = new Worker(doneListener, errorListener);
        worker.start();
    }
}

@FunctionalInterface
interface OnTaskDoneListener {
    void onDone(String result);
}

@FunctionalInterface
interface OnTaskErrorListener {
    void onError(String error);
}

class Worker {
    private final OnTaskDoneListener doneCallback;
    private final OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener doneCallback, OnTaskErrorListener errorCallback) {
        this.doneCallback = doneCallback;
        this.errorCallback = errorCallback;
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            if (i == 33) {
                errorCallback.onError("Task " + i + " failed");
            } else {
                doneCallback.onDone("Task " + i + " is done");
            }
        }
    }
}