// ======================
// MyStack.java
// ======================
public class MyStack<T> {

    private T[] elements;
    private int top;
    private int capacity;

    // Constructor
    @SuppressWarnings("unchecked")
    public MyStack(int capacity) {
        this.capacity = capacity;
        this.elements = (T[]) new Object[capacity];
        this.top = -1;
    }

    // Push element to top
    public void push(T value) {
        if(isFull()){
            System.out.println("Stack overflow");
            return;
        }
        top++;
        elements[top] = value;
    }

    // Pop element from top
    public T pop() {
        if(isEmpty()){
            System.out.println("Stack underflow");
            return null;
        }
        int idx = top ;
        top--;

        return elements[idx];
    }

    // Peek top element
    public T peek() {
        if(isEmpty()){
            System.out.println("Stack underflow");
            return null;
        }
        return elements[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        if(top == -1){
            return true;
        }else {
            return false;
        }
    }

    // Check if stack is full
    public boolean isFull() {
        if(top == elements.length-1){
            return true;
        }else{
            return false;
        }
    }

    // Return current size
    public int size() {
            return top + 1;
    }

    // Print all elements
    public void printStack() {
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("top: ");
        for(int i = top ; i >= 0 ; i--){
            System.out.println(elements[i]);
        }
    }

    // Stack as String
    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");

        for(int i = top; i >= 0; i--){
            result.append(elements[i]);
            if(i>0){
                result.append(", ");
            }
        }

        result.append("]");

        return result.toString();
    }
}

