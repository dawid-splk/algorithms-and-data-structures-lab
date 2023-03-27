package lab2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;

public class OneWayLinkedList<E> extends AbstractList<E> implements Serializable {

    private class Element implements Serializable{
        private E value;
        private Element next;

        Element(E data){
            this.value=data;}

        public E getValue() {
            return value;}

        public void setValue(E value) {
            this.value = value;}

        public Element getNext() {
            return next;}

        public void setNext(Element next) {
            this.next = next;}
    }

    Element head = null;

    public OneWayLinkedList(){};

    public boolean isEmpty(){
        return head==null;
    }

    @Override
    public void clear() {
        head = null;
    }

    private Element getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        Element actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem=actElem.getNext();
        }
        if (actElem==null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    @Override
    public int size() {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;}


    @Override
    public boolean add(E e) {
        Element newElem=new Element(e);
        if(head==null){
            head=newElem;
            return true;
        }
        Element tail=head;
        while(tail.getNext()!=null)
            tail=tail.getNext();
        tail.setNext(newElem);
        return true;
    }

    @Override
    public void add(int index, E data) {
        if(index<0) throw new IndexOutOfBoundsException();
        Element newElem=new Element(data);
        if(index==0)
        {
            newElem.setNext(head);
            head=newElem;
            return;
        }
        Element actElem=getElement(index-1);
        newElem.setNext(actElem.getNext());
        actElem.setNext(newElem);
        return;
    }

    @Override
    public int indexOf(Object data) {
        E e = (E) data;
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            if(actElem.getValue().equals(e))
                return pos;
            pos++;
            actElem=actElem.getNext();
        }
        return -1;}

    @Override
    public boolean contains(Object data) {
        return indexOf(data)>=0;}

    @Override
    public E get(int index) {
        Element actElem=getElement(index);
        return actElem.getValue(); }

    @Override
    public E set(int index, E data) {
        Element actElem=getElement(index);
        E elemData=actElem.getValue();
        actElem.setValue(data);
        return elemData;
    }
    @Override
    public E remove(int index) {
        if(index<0 || head==null) throw new IndexOutOfBoundsException();
        if(index==0){
            E retValue=head.getValue();
            head=head.getNext();
            return retValue;}
        Element actElem=getElement(index-1);
        if(actElem.getNext()==null)
            throw new IndexOutOfBoundsException();
        E retValue=actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        return retValue;}

    @Override
    public boolean remove(Object value) {
        if(head==null)
            return false;
        if(head.getValue().equals((E) value)){
            head=head.getNext();
            return true;}
        Element actElem=head;
        while(actElem.getNext()!=null && !actElem.getNext().getValue().equals((E) value))
            actElem=actElem.getNext();
        if(actElem.getNext()==null)
            return false;
        actElem.setNext(actElem.getNext().getNext());
        return true;}

    //~~~~~~~~~~~~~~~~~~~~~~metody z zadania 1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void removeStudentsUsingKey(E key){

        Element currElem = head;

        if(head == null) {
            System.out.println("\nLista jest pusta. ");
            return;
        }

        if(head.getValue().equals(key)) {
            while(currElem.getValue().equals(key) && currElem.getNext() != null) {
                currElem = currElem.getNext();      //kilka pasujacych elementow na poczatku
            }
            head = currElem.getNext() == null ? null : currElem;
        }

        while(currElem.getNext() != null){
            if(currElem.getNext().getValue().equals(key)) {
                if(currElem.getNext().getNext() != null)
                    currElem.setNext(currElem.getNext().getNext());
                else
                    currElem.setNext(null);
            } else {
                currElem = currElem.getNext();
            }
        }
    }

    public void viewList(){
        System.out.println("\nContent of the list:");
        LinkedListIterator iter = new LinkedListIterator(this);
        while(iter.hasNext()){
            E value = iter.next();
            System.out.println(value.toString());
        }
    }

    public void serialize(String fileName){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));

            oos.writeObject(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedListIterator iterator() {
        return new LinkedListIterator(this);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~iterator z zadania 1~~~~~~~~~~~~~~~~~~~~~~~~~~

    class LinkedListIterator implements Iterator<E>{

        OneWayLinkedList<E>.Element currElem;
        OneWayLinkedList<E> list;

        public LinkedListIterator(OneWayLinkedList<E> list) {
            this.list = list;
            this.currElem = list.head;
        }

        @Override
        public boolean hasNext() {
            return currElem != null;
        }

        @Override
        public E next() {
            E value = currElem.getValue();
            currElem = currElem.getNext();
            return value;
        }


    }




}

