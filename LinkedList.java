
/**
 * Represents a list of Nodes.
 */
public class LinkedList {

    private Node first; // pointer to the first element of this list
    private Node last;  // pointer to the last element of this list
    private int size;   // number of elements in this list

    /**
     * Constructs a new list.
     */
    public LinkedList() {
        first = null;
        last = first;
        size = 0;
    }

    /**
     * Gets the first node of the list
     *
     * @return The first node of the list.
     */
    public Node getFirst() {
        return this.first;
    }

    /**
     * Gets the last node of the list
     *
     * @return The last node of the list.
     */
    public Node getLast() {
        return this.last;
    }

    /**
     * Gets the current size of the list
     *
     * @return The size of the list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the node located at the given index in this list.
     *
     * @param index the index of the node to retrieve, between 0 and size
     * @throws IllegalArgumentException if index is negative or greater than the
     * list's size
     * @return the node at the given index
     */
    public Node getNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(
                    "index must be between 0 and size");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Creates a new Node object that points to the given memory block, and
     * inserts the node at the given index in this list.
     * <p>
     * If the given index is 0, the new node becomes the first node in this
     * list.
     * <p>
     * If the given index equals the list's size, the new node becomes the last
     * node in this list.
     * <p>
     * The method implementation is optimized, as follows: if the given index is
     * either 0 or the list's size, the addition time is O(1).
     *
     * @param block the memory block to be inserted into the list
     * @param index the index before which the memory block should be inserted
     * @throws IllegalArgumentException if index is negative or greater than the
     * list's size
     */
    public void add(int index, MemoryBlock block) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }

        Node newNode = new Node(block);

        if (index == 0) {
            addFirst(block);
            if (size == 1) {
                last = first;
            }
        } else if (index == size) {
            addLast(block);
        } else {
            Node previous = getNode(index - 1);
            newNode.next = previous.next;
            previous.next = newNode;
            size++;
        }
    }

    /**
     * Creates a new node that points to the given memory block, and adds it to
     * the end of this list (the node will become the list's last element).
     *
     * @param block the given memory block
     */
    public void addLast(MemoryBlock block) {
        Node newNode = new Node(block);
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Creates a new node that points to the given memory block, and adds it to
     * the beginning of this list (the node will become the list's first
     * element).
     *
     * @param block the given memory block
     */
    public void addFirst(MemoryBlock block) {
        Node newNode = new Node(block);
        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    /**
     * Gets the memory block located at the given index in this list.
     *
     * @param index the index of the retrieved memory block
     * @return the memory block at the given index
     * @throws IllegalArgumentException if index is negative or greater than or
     * equal to size
     */
    public MemoryBlock getBlock(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }
        if (size == 0) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }
        return getNode(index).block;
    }

    /**
     * Gets the index of the node pointing to the given memory block.
     *
     * @param block the given memory block
     * @return the index of the block, or -1 if the block is not in this list
     */
    public int indexOf(MemoryBlock block) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.block.equals(block)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    /**
     * Removes the given node from this list.
     *
     * @param node the node that will be removed from this list
     */
    public void remove(Node node) {
        if (node == null || size == 0) {
            throw new NullPointerException();
        }

        if (node == first) {
            first = first.next;
            size--;
            if (size == 0) {
                last = null;
            }
            if (size == 1) {
                last = first;
            }
        } else if (node == last) {
            last=getNode(size-2);
            getNode(size - 2).next = null;
            size--;
        } else {
            System.out.println(toString());
            MemoryBlock block = node.block;
            int index = indexOf(block);
            if (index<0){
                throw new IllegalArgumentException("index must be between 0 and size");
            }
            getNode(index-1).next = node.next;
            size--;
            System.out.println(toString());
            // Node current = first.next;
            // while (current.next != null) {
            //     if (current == node) {
            //         current.next = node.next;
            //         size--;
            //         System.out.println(toString());
            //         return;
            //     }
            //     current = current.next;
            // }
        }
    }

    /**
     * Removes from this list the node which is located at the given index.
     *
     * @param index the location of the node that has to be removed.
     * @throws IllegalArgumentException if index is negative or greater than or
     * equal to size
     */
    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }
        Node node = getNode(index);
        remove(node);
        // if (index == 0) {
        //     first = first.next;
        //     if (size == 1) {
        //         last = null;
        //     }
        // } else {
        //     Node previuosNode = getNode(index - 1);
        //     Node nodeToRemove = previuosNode.next;
        //     previuosNode.next = nodeToRemove.next;

        //     if (nodeToRemove == last) {
        //         last = previuosNode;
        //     }
        // }
        // size--;
    }

    /**
     * Removes from this list the node pointing to the given memory block.
     *
     * @param block the memory block that should be removed from the list
     * @throws IllegalArgumentException if the given memory block is not in this
     * list
     */
    public void remove(MemoryBlock block) {
        int nodeIndex = indexOf(block);
        if (nodeIndex < 0) {
            throw new IllegalArgumentException("index must be between 0 and size");
        }
        Node node = getNode(nodeIndex);
        remove(node);
    }

    /**
     * Returns an iterator over this list, starting with the first element.
     */
    public ListIterator iterator() {
        return new ListIterator(first);
    }

    /**
     * A textual representation of this list, for debugging.
     */
    public String toString() {
        ListIterator itr = new ListIterator(first);
        StringBuilder strBuilder = new StringBuilder();
        while (itr.hasNext()) {
            Node current = itr.current;
            strBuilder.append("(")
                    .append(current.block.baseAddress)
                    .append(" , ")
                    .append(current.block.length)
                    .append(")");
            itr.next(); // Advance the iterator to the next node
            if (itr.hasNext()) { // Add a space only if there are more nodes
                strBuilder.append(" ");
            }
        }
        return strBuilder.toString();
    }
}
