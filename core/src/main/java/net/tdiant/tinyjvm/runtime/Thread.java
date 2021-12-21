package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.exception.StackOverflowError;

public class Thread {

    public int top;
    public Frame[] frames;

    public Thread(int size) {
        this.frames = new Frame[size];
    }

    public void push(Frame frame) {

        if (top >= this.frames.length)
            throw new StackOverflowError();

        frames[top++] = frame;
    }

    public Frame pop() {
        if (top < 1) {
            throw new StackOverflowError();
        }
        Frame frame = frames[--top];
        frames[top] = null;
        return frame;
    }

    public int top() {
        return this.top;
    }

    public Frame now() {
        return this.frames[top - 1];
    }

    public Frame caller() {
        return this.frames[top - 2];
    }

    public boolean empty() {
        return top <= 0;
    }
}
