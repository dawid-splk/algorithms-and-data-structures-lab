package lab4.Zad3;

import java.util.EmptyStackException;

public class SinkingStack<E> {

    private class Element{
        private E value;
        private Element next;

        public Element(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    Element head = null;
    private Element tail = head;
    private int size;
    private int nextFreeIndex = 0;

    public SinkingStack(int limit) {
        this.size = limit;
    }

    public Element getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        if(index == 0)
            return head;
        Element actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem=actElem.getNext();
        }
        if (actElem==null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    public E get(int index) {
        Element actElem=getElement(index);
        return actElem.getValue(); }

    public int size() {
        return nextFreeIndex;
    }

    public boolean push(E e) {
        Element newElem = new Element(e);
        if(head==null){
            head=newElem;
            newElem.setNext(null);
            tail = head;
            nextFreeIndex++;
            return true;
        }
        if(tail == head){
            tail = newElem;
            newElem.setNext(null);
            head.setNext(newElem);
        } else {
            tail.setNext(newElem);
            tail = newElem;
            tail.setNext(null);
        }
        if(nextFreeIndex == size) {
            head = head.getNext();
        } else {
            nextFreeIndex++;
        }
        return true;
    }

    public E pop() {
        if(head == null){
            System.out.println("Stos jest pusty!");
            throw new EmptyStackException();
        }
        Element curElem = head;
        if(curElem.getNext() == null) {
            E retValue = head.getValue();
            head = null;
            nextFreeIndex--;
            return retValue;
        }
        while(!curElem.getNext().equals(tail)){
            curElem = curElem.getNext();
        }
        E retValue = curElem.getNext().getValue();
        curElem.setNext(null);
        tail = curElem;
        nextFreeIndex--;

        return retValue;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public E getTailValue(){
        return tail.getValue();
    }

    public int getNextFreeIndex() {
        return nextFreeIndex;
    }
}
