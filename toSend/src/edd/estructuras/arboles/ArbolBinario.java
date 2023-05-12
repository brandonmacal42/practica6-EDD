package edd.estructuras.arboles;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edd.estructuras.lineales.ArrayList;

public class ArbolBinario<C extends Comparable> implements Iterable<C> {

    protected Node<C> root;

    protected int size;

    public ArbolBinario() {
        root = null;
        size = 0;
    }

    public Node<C> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<C> getIteratorPreOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }

    public Iterator<C> getIteratorPostOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }

    public Iterator<C> getIteratorInOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }

    public Iterator<C> iterator() {
        return new InOrder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<C> it = iterator();

        sb.append("[");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(" ");
        }
        sb.append("]");

        return sb.toString();
    }

    public void add(C c) {
        if (root == null) {
            root = new Node<>();
            root.elem = c;
        } else {
            root.add(c);
        }

        size++;
    }

    public boolean remove(C c) {
        Node<C> node, aux;

        node = root;

        while (node != null) {
            if (node.elem.compareTo(c) == 0) {
                if (node.left != null) {
                    aux = node.left.max();
                    node.elem = aux.elem;
                    remove(aux);
                } else if (node.right != null) {
                    aux = node.right.min();
                    node.elem = aux.elem;
                    remove(aux);
                } else {
                    remove(node);
                }

                size--;

                return true;
            } else if (node.elem.compareTo(c) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
    }

    private void remove(Node<C> node) {
        Node<C> f = node.padre;
        Node<C> aux;

        if (node.isLeaf()) {
            if (f != null) {
                if (f.getChild(node) == 0) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
        } else {
            if (node.left != null) {
                aux = node.left;
                node.left.padre = f;
            } else {
                aux = node.right;
                node.right.padre = f;
            }

            if (f != null) {
                if (f.getChild(node) == 0) {
                    f.left = aux;
                } else {
                    f.right = aux;
                }
            }
        }

        node.padre = null;
        node.left = null;
        node.right = null;
    }

    public boolean contains(C c) {
        Node<C> node;

        node = root;

        while (node != null) {
            if (node.elem.compareTo(c) == 0) {
                return true;
            } else if (node.elem.compareTo(c) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
    }

    public C min() {
        if (root != null) {
            return root.min().elem;
        } else {
            return null;
        }
    }

    public C max() {
        if (root != null) {
            return root.max().elem;
        } else {
            return null;
        }
    }

    class Node<C extends Comparable> {
        Node<C> padre;
        Node<C> left;
        Node<C> right;
        C elem;

        int getHeight() {
            int l, r, h;
            l = r = -1;

            if (left != null) {
                l = getHeight();
            }

            if (right != null) {
                r = getHeight();
            }

            h = l < r ? r : l;

            h++;

            return h;
        }

        boolean isLeaf() {
            return getGrade() == 0;
        }

        int getGrade() {
            int i = 0;

            if (left != null)
                i++;
            if (right != null)
                i++;

            return i;
        }

        void add(C c) {
            if (elem.compareTo(c) > 0) {
                // Inserta a la izquierda.
                if (left == null) {
                    left = new Node<>();
                    left.elem = c;
                    left.padre = this;
                } else {
                    left.add(c);
                }
            } else {
                // Inserta a la derecha.
                if (right == null) {
                    right = new Node<>();
                    right.elem = c;
                    right.padre = this;
                } else {
                    right.add(c);
                }
            }
        }

        int getChild(Node<C> hijo) {
            if (left == hijo) {
                return 0;
            } else if (right == hijo) {
                return 1;
            } else {
                return -1;
            }
        }

        Node<C> min() {
            Node<C> node;

            node = this;

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        Node<C> max() {
            Node<C> node;

            node = this;

            while (node.right != null) {
                node = node.right;
            }

            return node;
        }

        public String toString() {
            return "<" + elem + ">";
        }
    }

    class InOrder implements Iterator<C> {
        ArrayList<Node<C>> stack;

        public InOrder() {
            this.stack = new ArrayList<Node<C>>();
            if (ArbolBinario.this.root != null) {
                this.stack.add(0, ArbolBinario.this.root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public C next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Node<C> node = this.stack.remove(0);
            if (node.isLeaf()) {
                return node.elem;
            }
            if (node.right != null) {
                this.stack.add(0, node.right);
            }
            final Node<C> aux = new Node<C>();
            aux.elem = node.elem;
            this.stack.add(0, aux);
            if (node.left != null) {
                this.stack.add(0, node.left);
            }
            return this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Metodo sin implementar.");
        }
    }
}
